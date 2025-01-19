from flask import Flask, request
import json
from PIL import Image
from ultralytics import YOLO
from convert import convert_to_braille_unicode, parse_xywh_and_class

# Flask 앱 초기화
app = Flask(__name__)

# YOLO 모델 로드
model_path = "./yolov8m.pt"  # 로컬 YOLO 모델 경로
model = YOLO(model_path)

# 점자 매핑 파일 로드
braille_map_path = "./braille_map.json"
with open(braille_map_path, "r") as file:
    braille_map = json.load(file)

@app.route('/convertBrailleImg', methods=['POST'])
def process_image():
    if 'file' not in request.files:
        return json.dumps({"error": "파일이 업로드되지 않았습니다."}, ensure_ascii=False), 400

    try:
        # 이미지 파일 로드
        file = request.files['file']
        image = Image.open(file)

        # YOLO 모델로 점자 패턴 탐지
        results = model.predict(image, conf=0.25, iou=0.45)
        boxes = results[0].boxes

        # 탐지된 점자 패턴을 좌표별로 정렬
        parsed_boxes = parse_xywh_and_class(boxes)

        # 점자 코드 변환
        braille_text = []
        for line in parsed_boxes:
            line_text = ""
            for box in line:
                class_id = int(box[-1])  # 클래스 ID
                class_label = model.names[class_id]  # YOLO 모델 클래스 이름
                unicode_char = convert_to_braille_unicode(class_label, braille_map_path)
                line_text += unicode_char
            braille_text.append(line_text)

        # 사람이 읽을 수 있는 점자 텍스트 반환
        readable_braille = {"braille_text": braille_text}
        return json.dumps(readable_braille, ensure_ascii=False), 200

    except Exception as e:
        return json.dumps({"error": f"서버 오류: {str(e)}"}, ensure_ascii=False), 500

if __name__ == '__main__':
    app.run(host='0.0.0.0', port=5001)