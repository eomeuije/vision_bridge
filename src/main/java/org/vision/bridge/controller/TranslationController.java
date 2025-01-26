package org.vision.bridge.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.vision.bridge.service.translation.Translator2Braille;
import org.vision.bridge.service.translation.Translator2Text;

import java.util.Map;

@RestController
@RequestMapping("/translate")
public class TranslationController {

    @Autowired
    private Translator2Braille translator2Braille; // 텍스트 → 점자 변환 서비스

    @Autowired
    private Translator2Text translator2Text; // 점자 → 텍스트 변환 서비스

    // 텍스트 → 점자 변환
    @PostMapping("/toBraille")
    public String translateToBraille(@RequestBody Map<String, String> request) {
        String sourceText = request.get("sourceText");
        if (sourceText == null || sourceText.trim().isEmpty()) {
            return "입력 텍스트가 비어 있습니다.";
        }
        String[] split = sourceText.split("\n");
        StringBuilder result = new StringBuilder();
        for (String s : split) {
            if (s.isEmpty()) continue;
            result.append(translator2Braille.translate(s)).append("\n");
        }
        return result.toString();
    }

    // 점자 → 텍스트 변환
    @PostMapping("/toText")
    public String translateToText(@RequestBody Map<String, String> request) {
        String brailleText = request.get("brailleText");
        if (brailleText == null || brailleText.trim().isEmpty()) {
            return "입력 점자 텍스트가 비어 있습니다.";
        }
        String[] split = brailleText.split("\n");
        StringBuilder result = new StringBuilder();
        for (String s : split) {
            if (s.isEmpty()) continue;
            result.append(translator2Text.translate(s)).append("\n");
        }
        return result.toString();
    }
}
