# vision_bridge
## 창원대학교 캡스톤디자인 점자번역 서비스
### 실행방법
```txt
build.gradle의 dependencies 다운로드 후 src/main 아래의 VisionBridgeApplication.java(class) main함수 실행
```
+ flask 연결
  ```
  1. VisionBridgeApplication 실행
  2. flask_server의 app.py 실행
  3. 터미널에서 작동
  ``curl -X POST -F "file=@test.png" http://127.0.0.1:5001/convertBrailleImg``
  ```
