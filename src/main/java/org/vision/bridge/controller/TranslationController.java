package org.vision.bridge.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.vision.bridge.service.FlaskApiService;

import java.util.Map;

@RestController
@RequestMapping("/api")
public class TranslationController {

    @Autowired
    private FlaskApiService flaskApiService;

    @PostMapping("/translate")
    public String translate(@RequestBody Map<String, String> request) {
        String sourceText = request.get("sourceText"); // 요청에서 입력 텍스트 가져오기
        return flaskApiService.sendTranslateRequest(sourceText);
    }
}
