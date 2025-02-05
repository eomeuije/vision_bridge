package org.vision.bridge.controller;

import net.sourceforge.tess4j.TesseractException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.vision.bridge.service.ocr.OCR;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Controller
public class OCRController {

    @Autowired
    public OCR ocr;

    ObjectMapper objectMapper = new ObjectMapper();

    @PostMapping("/ocr")
    public ResponseEntity<String> ocr(@RequestParam("file") MultipartFile file) {
        try {
            String mimeType = file.getContentType();
            if (mimeType == null || !mimeType.startsWith("image/")) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("이미지 파일 형식이 아닙니다.");
            }
            String result = ocr.recognize(file.getInputStream());

            Map<String, String> jsonMap = new HashMap<>();
            jsonMap.put("text", result);

            return ResponseEntity.ok(objectMapper.writeValueAsString(jsonMap));
        } catch (TesseractException | IOException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("이미지 인식에 실패 했습니다.");
        }
    }
}
