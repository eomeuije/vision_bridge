from flask import Flask, request, jsonify

app = Flask(__name__)

@app.route('/translate', methods=['POST'])
def translate():
    data = request.json
    source_text = data.get("sourceText")

    # 점자 번역 로직을 호출 (우선 테스트용으로 만듦)
    translated_text = source_text[::-1]
    return jsonify({"translatedText": translated_text})

if __name__ == '__main__':
    app.run(host='0.0.0.0', port=5001)
