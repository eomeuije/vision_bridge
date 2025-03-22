from flask import Flask, request, jsonify, make_response
import cv2
import numpy as np
import json
import os
import math
from dotenv import load_dotenv
from collections import Counter

# 특정 파일 로드
load_dotenv(os.path.join(os.path.dirname(__file__), "flask.env"))
load_dotenv("flask.env")

# Flask 앱 초기화
app = Flask(__name__)

# 점자 매핑 파일 로드
braille_map_path = os.getenv("BRAILLE_MAP_PATH", "./braille_map.json")
with open(braille_map_path, "r", encoding='utf8') as file:
    braille_map = json.load(file)

def resize_min_size(image, min_size=300):
    height, width = image.shape[:2]

    # 높이 또는 너비가 min_size 미만이면 비율 유지하며 조정
    if height < min_size or width < min_size:
        scale = max(min_size / height, min_size / width)  # 더 작은 쪽을 기준으로 스케일 결정
        new_width = int(width * scale)
        new_height = int(height * scale)

        image = cv2.resize(image, (new_width, new_height), interpolation=cv2.INTER_LINEAR)

    return image

def preprocess_image(image):
    """Flask에서 받은 이미지(NumPy 배열)를 처리"""
    image = resize_min_size(image)
    blurred = cv2.GaussianBlur(image, (5, 5), 0)

    contrast_image = cv2.convertScaleAbs(blurred, alpha=1.5, beta=0)
    mean_value = cv2.mean(contrast_image)[0]

    if mean_value < 127:
        blurred = cv2.bitwise_not(blurred)

    return blurred

def get_binary_image(image, threshold_value=None):
    """이미지를 불러와 블러를 적용한 후 Otsu 임계값 기반 이진화"""
    # Otsu 임계값 적용
    if threshold_value is None:
        _, binary = cv2.threshold(image, 0, 255, cv2.THRESH_BINARY_INV + cv2.THRESH_OTSU)
    else:
        _, binary = cv2.threshold(image, threshold_value, 255, cv2.THRESH_BINARY_INV)

    return binary

def get_distance(x1, y1, x2, y2):
    return math.sqrt((x2 - x1)**2 + (y2 - y1)**2)

def detect_braille_dots(binary, min_radius):
    binary = cv2.Canny(binary, 100, 100)
    """점자 점(타원형 객체) 검출"""
    contours, _ = cv2.findContours(binary, cv2.RETR_EXTERNAL, cv2.CHAIN_APPROX_SIMPLE)
    detected_dots = []

    for contour in contours:
        if len(contour) >= 5:  # fitEllipse는 최소 5개 이상의 점 필요
            ellipse = cv2.fitEllipse(contour)
            (x, y), (major_axis, minor_axis), angle = ellipse

            if minor_axis > major_axis:
                temp = minor_axis
                minor_axis = major_axis
                major_axis = temp
            # 너무 작거나 한쪽으로만 긴 타원을 제거
            if minor_axis > 4 and (major_axis + minor_axis) / min_radius > 10 and major_axis / minor_axis / 4 < 2.5:
                detected_dots.append((int(x), int(y), (major_axis + minor_axis) / 4))

    return detected_dots

def filter_large_dots(dots):
    """
    원의 반지름의 중간값을 구한 뒤, 2배 이상 큰 반지름을 가진 점들을 제거하는 함수

    :param dots: [(x, y, r), (x, y, r), ...] 형태의 점 리스트 (r = 반지름)
    :return: 필터링된 점 리스트
    """
    if not dots:
        return []

    # 반지름 리스트 추출
    radii = [r for (_, _, r) in dots]

    # 중간값 계산
    median_radius = np.median(radii)

    # 2배 이상 큰 점 제거
    filtered_dots = [(x, y) for (x, y, r) in dots if r <= 2 * median_radius]

    return filtered_dots

def merge_dots(previous_dots, new_dots, tolerance=0.1):
    """기존 타원의 내부에 새로운 타원이 존재하면 기존 타원을 삭제하고 새로운 타원을 유지"""
    merged_dots = previous_dots.copy()  # 기존 점 리스트 복사
    to_remove = []  # 삭제할 기존 타원 목록

    for new_x, new_y, new_r in new_dots:
        # is_contained = False  # 새로운 타원이 기존 타원 내부에 있는지 여부

        for old_x, old_y, old_r in previous_dots:
            distance = np.sqrt((new_x - old_x) ** 2 + (new_y - old_y) ** 2)

            # 기존 타원의 반지름 내에 새로운 타원의 중심이 있으면 기존 타원 삭제
            if distance < old_r:
                to_remove.append((old_x, old_y, old_r))

        # 새로운 타원이 기존 타원 안에 포함되지 않으면 추가
        merged_dots.append((new_x, new_y, new_r))

    # 삭제할 타원 제거
    merged_dots = [dot for dot in merged_dots if dot not in to_remove]

    return merged_dots

def filter_isolated_dots(dots, min_distance=10):
    """점들 간의 거리가 최소값 이상인 점들만 남기기"""
    filtered_dots = []
    for i, (x1, y1) in enumerate(dots):
        add_dot = True
        for x2, y2 in filtered_dots:
            if get_distance(x1, y1, x2, y2) < min_distance:
                add_dot = False
                break
        if add_dot:
            filtered_dots.append((x1, y1))
    return filtered_dots

def process_braille_image(image, loop=8, threshold=5, start=0):
    """Otsu 값에서 시작해 5씩 증가하며 8번 반복하여 점자 검출"""
    image = preprocess_image(image)

    # base_threshold = cv2.threshold(image, 0, 255, cv2.THRESH_BINARY_INV + cv2.THRESH_OTSU)[1].mean() + start
    base_threshold = cv2.threshold(image, 0, 255, cv2.THRESH_BINARY_INV + cv2.THRESH_OTSU)[0] + start
    # if base_threshold > 127:
    #     base_threshold = 255 - base_threshold
    # base_threshold = 120
    detected_dots = []

    for i in range(loop):
        threshold_value = int(base_threshold) - (i * threshold)
        if threshold_value <= 0:
            break
        binary = get_binary_image(image, threshold_value)
        new_dots = detect_braille_dots(binary, loop / ((loop - i) / 1.5))

        # 검출된 점 합치기
        detected_dots = merge_dots(detected_dots, new_dots)
        # draw_detected_dots_img(binary, detected_dots)

    detected_dots = filter_large_dots(detected_dots)
    # detected_dots = [(x, y) for (x, y, r) in detected_dots]
    # 동떨어진 점 필터링
    detected_dots = filter_isolated_dots(detected_dots, min_distance=1)
    return detected_dots



def degrees_to_radians(deg):
    """도를 라디안으로 변환"""
    return deg * (math.pi / 180)

def radians_to_degrees(rad):
    """라디안을 도(degree)로 변환"""
    return rad * (180 / math.pi)

def calculate_slope(p1, p2):
    """두 점의 기울기 계산 (x축이 평행한 경우 무한대 처리)"""
    x1, y1 = p1
    x2, y2 = p2

    if x2 == x1:
        return 90.0  # 수직선 (기울기 90도)

    return math.degrees(math.atan2(y2 - y1, x2 - x1))  # 기울기를 도(degree)로 반환

def find_dominant_slope(dots, tolerance=1):
    """
    모든 점들의 조합에 대해 기울기를 계산하고, 최빈 기울기를 찾는 함수
    tolerance: 최빈 기울기와 ±1도 이내를 같은 기울기로 취급
    """
    slopes = []

    # 모든 점 쌍의 기울기 계산
    dot_count = len(dots)
    for i in range(dot_count):
        for j in range(i + 1, dot_count):
            slope = calculate_slope(dots[i], dots[j])
            slopes.append(slope)

    # 최빈 기울기 찾기 (±1도 내 그룹화)
    slope_counter = Counter()
    for slope in slopes:
        found = False
        for key in list(slope_counter.keys()):
            if abs(slope - key) <= tolerance:
                slope_counter[key] += 1
                found = True
                break
        if not found:
            slope_counter[slope] = 1

    # 최빈 기울기 결정
    dominant_slope = max(slope_counter, key=slope_counter.get) if slope_counter else None
    rotated_slope = (dominant_slope + 90) % 180 if dominant_slope is not None else None

    return dominant_slope, rotated_slope

def filter_dots_by_dominant_slope(dots, tolerance=1):
    """
    모든 점들의 기울기를 계산하고,
    최빈 기울기와 ±1도 또는 90도 회전된 기울기와 ±1도 이내의 점만 남김.
    """
    if len(dots) < 2:
        return dots, None  # 점이 부족하면 필터링 불가

    dominant_slope, rotated_slope = find_dominant_slope(dots, tolerance)
    if dominant_slope is None:
        return dots, None  # 최빈 기울기가 없으면 필터링 없이 반환

    filtered_dots = set()

    # 다시 모든 점 쌍의 기울기를 계산하여 조건을 만족하는 점들만 유지
    dot_count = len(dots)
    for i in range(dot_count):
        for j in range(i + 1, dot_count):
            slope = calculate_slope(dots[i], dots[j])

            if (
                    abs(slope - dominant_slope) <= tolerance or  # 최빈 기울기 ±1도
                    abs(slope - rotated_slope) <= tolerance      # 90도 회전 기울기 ±1도
            ):
                filtered_dots.add(dots[i])
                filtered_dots.add(dots[j])

    return list(filtered_dots), dominant_slope



# 점자 인식 관련 설정
RADIUS_RATIO = 0.75  # 점의 반지름 비율
CLOSEST_DOT_RATIO = 2.3  # 점자 내 원과 원의 가장 가까운 거리 비율
BRAILLE_SPACING_RATIO = 3.2  # x점자 간격 비율
BRAILLE_NEWLINE_RATIO = 5.4  # y점자 간격 비율
BRAILLE_NEWLINE_RATIO = 5.4  # 점자 높이 비율

def get_mode(li, tolerance=5):
    modes = {}
    for i in li:
        is_new = True
        v = None
        f = None
        for value, freq in modes.items():
            if value - tolerance <= i <= value + tolerance:
                v = value
                f = freq
                is_new = False
                break

        if is_new:
            modes[i] = 1
        else:
            modes.pop(v)
            modes[(v+i)/2] = f + 1

    mode = 9999999
    sorted_keys = sorted(modes, key=lambda k: modes[k], reverse=True)
    for i, s in enumerate(sorted_keys):
        if i >= 2:
            break
        mode = min(s, mode)
    return mode

def get_pretty_shortest_dot(x1, y1, dots):
    shortest_distance = float('inf')
    shortest_dot = None
    shortest_m = 0
    for i, (x2, y2) in enumerate(dots):
        m = math.degrees(math.atan2(y2 - y1,  x2 - x1)) if x2 != x1 else 90
        if (y2 == y1 and x2 == x1) or not m % 90 <= 30:
            continue
        dis = cv2.norm((x1, y1), (x2, y2), cv2.NORM_L2)
        if dis < shortest_distance:
            shortest_distance = dis
            shortest_dot = (x2, y2)
            shortest_m = m
    return shortest_dot, shortest_distance, shortest_m

def find_mode_distance_and_slope(points):
    """
    점들의 리스트에서 가장 가까운 점과의 거리의 최빈값을 찾고,
    해당 최빈값 거리의 두 점 사이의 기울기를 구하는 함수.
    단, 각도가 점자 각도가 아닌것은 제외.

    :param points: [(x, y), ...] 점들의 리스트
    :return: mode_distance (최빈 거리), slopes (기울기 리스트)
    """
    if not points or len(points) < 2:
        return None, []

    x_distances, y_distances = [], []  # 거리 리스트
    # 가장 가까운 점 사이의 거리 계산
    for i, (x1, y1) in enumerate(points):
        shortest_dot, shortest_distance, shortest_m = get_pretty_shortest_dot(x1, y1, points)

        if shortest_dot != None:
            (x2, y2) = shortest_dot
            if shortest_m < -135 or -45 < shortest_m < 45 or shortest_m > 135:
                x_distances.append((shortest_distance, (x1, y1), (x2, y2), shortest_m))
            else:
                y_distances.append((shortest_distance, (x1, y1), (x2, y2), shortest_m))

    # 최빈값 거리 찾기
    allx_distances = [d[0] for d in x_distances]
    ally_distances = [d[0] for d in y_distances]

    modex_distance = get_mode(allx_distances)
    modey_distance = get_mode(ally_distances)

    # 최빈값 거리와 가장 가까운 점을 찾기
    slopesx = []
    for dist, (x1, y1), (x2, y2), m in x_distances:
        if abs(dist - modex_distance) < 2:  # 최빈값과 가까운 거리만 선택
            slopesx.append(m)
    slopesy = []
    for dist, (x1, y1), (x2, y2), m in y_distances:
        if abs(dist - modey_distance) < 2:  # 최빈값과 가까운 거리만 선택
            slopesy.append(m)
    if len(slopesx) == 0:
        slopesx = slopesy
    elif len(slopesy) == 0:
        slopesy = slopesx
    degreex = get_mode_degree(slopesx)
    degreey = get_mode_degree(slopesy)
    return modex_distance, modey_distance, degreex, degreey

def get_mode_degree(degrees):
    sum = 0
    for degree in degrees:
        d = degree % 90
        sum += d
    return sum / len(degrees)

def point_to_line_distance(x0, y0, x1, y1, degree):
    slope = math.tan(math.radians(degree))
    # 기울기 m을 사용하여 직선의 방정식 y - y1 = m(x - x1) 를 ax + by + c = 0 형태로 변환
    a = -slope
    b = 1
    c = slope * x1 - y1  # c = - (mx1 - y1)

    # 거리 공식 적용
    distance = abs(a * x0 + b * y0 + c) / math.sqrt(a**2 + b**2)
    return distance

def y_location(dots, degree, distance, tol_add=0):
    if degree > 45:
        degree %= 90
    points = dots.copy()  # 기존 점 리스트 복사
    points = sorted(points, key=lambda p: (p[1], p[0]))
    y_dict = {}
    i = 0
    (x1, y1) = min(points, key=lambda p: p[1])
    (max_x, max_y) = max(points, key=lambda p: p[1])
    tol = RADIUS_RATIO*distance/CLOSEST_DOT_RATIO + tol_add
    b_tol = BRAILLE_NEWLINE_RATIO*distance/CLOSEST_DOT_RATIO + tol_add
    while y1 <= max_y + b_tol:
        remove = []
        temp_max_x = x1
        temp_max_y = y1
        for (x2, y2) in points:
            d = point_to_line_distance(x1, y1, x2, y2, degree)
            if d <= tol:
                x1 = x2
                y1 = y2
                y_dict[(x2, y2)] = i
                remove.append((x2, y2))
        x1 = temp_max_x
        y1 = temp_max_y
        for x, y in remove:
            points.remove((x, y))
        if  len(points) > 0 and i % 3 == 2:
            (temp_x1, temp_y1) = min(points, key=lambda p: p[1])
            dis = point_to_line_distance(x1, y1, temp_x1, temp_y1, degree)
            if  dis > b_tol + tol:
                if  dis > b_tol + tol + distance:
                    if  dis > b_tol + tol + 2*distance:
                        i += 3
                    else:
                        i += 2
                else:
                    i += 1
            x1, y1 = temp_x1, temp_y1
        else:
            y1 += distance
        i += 1

    return y_dict

def x_location(dots, degree, distance, tol_add=0):
    if -45 < degree < 45:
        degree = degree + 90

    points = dots.copy()  # 기존 점 리스트 복사
    points = sorted(points, key=lambda p: (p[0], p[1]))
    x_dict = {}
    i = 0
    (x1, y1) = min(points, key=lambda p: p[0])
    (max_x, max_y) = max(points, key=lambda p: p[0])
    tol = RADIUS_RATIO*distance/CLOSEST_DOT_RATIO + tol_add
    b_tol = BRAILLE_SPACING_RATIO*distance/CLOSEST_DOT_RATIO + tol_add
    while x1 <= max_x + b_tol:
        remove = []
        temp_max_x = x1
        temp_max_y = y1
        if i == 0:
            xt = x1 + b_tol
            for (x2, y2) in points:
                d = point_to_line_distance(xt, y1, x2, y2, degree)
                if d <= tol / 2:
                    i += 1
                    break
        for (x2, y2) in points:
            d = point_to_line_distance(x1, y1, x2, y2, degree)
            if d <= tol:
                x1 = x2
                y1 = y2
                x_dict[(x2, y2)] = i
                remove.append((x2, y2))
        x1 = temp_max_x
        y1 = temp_max_y
        for x, y in remove:
            points.remove((x, y))
        if  len(points) > 0 and i % 2 == 1:
            (temp_x1, temp_y1) = min(points, key=lambda p: p[0])
            dis = point_to_line_distance(x1, y1, temp_x1, temp_y1, degree)
            if  dis > b_tol + tol:
                i += 1
            x1, y1 = temp_x1, temp_y1
        else:
            x1 += distance
        i += 1

    return x_dict

def convert_to_braille_unicode(str_input: str, json_value) -> str:
    if '000000' == str_input:
        return ' '
    if str_input in json_value.keys():
        str_output = json_value[str_input]
    return str_output

def get_braille(xy_dict: dict) -> str:
    tik = [(0, 0), (0, 1), (0, 2), (1, 0), (1, 1), (1,2)]
    x_diff = 2
    y_diff = 3
    max_x = max(xy_dict.keys(), key=lambda k: k[0])[0]
    max_y = max(xy_dict.keys(), key=lambda k: k[1])[1]
    xd = 0
    yd = 0
    braille = ''
    while yd <= max_y:
        b = ''
        for (t1, t2) in tik:
            if (t1 + xd, t2 + yd) in xy_dict:
                b += '1'
            else:
                b += '0'

        xd += x_diff
        if xd > max_x + x_diff:
            xd = 0
            yd += y_diff
            braille += '\n'
        braille += convert_to_braille_unicode(b, braille_map)

    return braille


@app.route('/convertBrailleImg', methods=['POST'])
def process_image():
    """Flask API: 점자 이미지 업로드 후 변환"""
    if 'file' not in request.files:
        return jsonify({"error": "파일이 업로드되지 않았습니다."}), 400

    try:
        # 이미지 파일 로드 (OpenCV 사용)
        file = request.files['file']
        npimg = np.frombuffer(file.read(), np.uint8)
        image = cv2.imdecode(npimg, cv2.IMREAD_GRAYSCALE)

        if image is None:
            return jsonify({"error": "유효한 이미지 파일이 아닙니다."}), 400

        # 점자 이미지 처리 (OpenCV 기반)
        filtered_braille = process_braille_image(image, loop=8, threshold=10)
        # filtered_braille = process_braille_image(image, loop=5, threshold=6, start=-40)
        if len(filtered_braille) > 1000:
            raise Exception('Too many Brailles')
        elif len(filtered_braille) <= 0:
            filtered_braille = process_braille_image(image, loop=8, threshold=10)


        # 점자 기울기 및 거리 계산
        x_d, y_d, x_g, y_g = find_mode_distance_and_slope(filtered_braille)

        # 점자 위치 정렬
        g = min(x_g, y_g)
        if g == 0:
            g += 0.0001
        y_dict = y_location(filtered_braille, g, y_d)
        x_dict = x_location(filtered_braille, g, x_d)
        xy_dict = {(x_dict[k], y_dict[k]): k for k in x_dict.keys() & y_dict.keys()}

        # 점자 변환 (OpenCV 기반)
        braille_text = get_braille(xy_dict)
        # 사람이 읽을 수 있는 점자 텍스트 반환
        readable_braille = {"braille_text": braille_text.strip()}
        response = make_response(jsonify(readable_braille), 200)
        return response

    except Exception as e:
        return jsonify({"error": f"서버 오류: {str(e)}"}), 500

if __name__ == '__main__':
    app.run(host='0.0.0.0', port=5001)