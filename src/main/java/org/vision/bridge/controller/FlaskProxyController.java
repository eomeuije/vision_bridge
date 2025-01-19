package org.vision.bridge.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.vision.bridge.service.flask.BrailleImage;
import org.springframework.http.HttpStatus;

@RestController
@RequestMapping("/api")
public class FlaskProxyController {

    @Autowired
    private BrailleImage brailleImage;

    @PostMapping("/brailleImg")
    public ResponseEntity<String> processBrailleImage(@RequestParam("file") MultipartFile file) {
        try {
            // 서비스 호출
            String result = brailleImage.sendImageToFlask(file);
            return ResponseEntity.ok(result);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body("파일 처리 오류: " + e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Spring 서버 오류: " + e.getMessage());
        }
    }
}
