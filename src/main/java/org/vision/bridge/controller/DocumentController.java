package org.vision.bridge.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.vision.bridge.service.brf.BRFParser;
import org.vision.bridge.service.documentParser.HWPParser;
import org.vision.bridge.service.documentParser.MSWordParser;
import org.vision.bridge.service.documentParser.PDFParser;
import org.vision.bridge.service.documentParser.PlainTextParser;
import org.vision.bridge.service.utils.BrailleUtils;


public class DocumentController {

    @Autowired public PDFParser pdfParser;
    @Autowired public PlainTextParser plainTextParser;
    @Autowired public HWPParser hwpParser;
    @Autowired public BRFParser brfParser;
    @Autowired public MSWordParser msWordParser;

    @PostMapping("/document/text")
    public ResponseEntity<String> parseToText(@RequestParam("file") MultipartFile file) {
        try {
            String result = toText(file);
            if (result == null) {
                ResponseEntity.status(HttpStatus.BAD_REQUEST).body("지원하지 않는 문서 형식입니다.");
            }
            return ResponseEntity.ok(result);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("문서 파싱에 실패 했습니다.");
        }
    }


    @PostMapping("/document/braille")
    public ResponseEntity<String> parseToBraille(@RequestParam("file") MultipartFile file) {
        try {
            String result = toText(file);
            if (result == null) {
                ResponseEntity.status(HttpStatus.BAD_REQUEST).body("지원하지 않는 문서 형식입니다.");
            }
            String s = BrailleUtils.brailleFilter(result);
            return ResponseEntity.ok(s);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("문서 파싱에 실패 했습니다.");
        }
    }

    private String toText(MultipartFile file) throws Exception {
        String result = null;
        String mimeType = file.getContentType();

        if (mimeType != null) {
            switch (mimeType) {
                case "application/pdf" -> result = pdfParser.parse(file.getInputStream());
                case "text/plain", "application/octet-stream" -> {
                    result = plainTextParser.parse(file.getInputStream());
                    if (file.getName().toLowerCase().endsWith(".brf")) {
                        result = brfParser.translateBRF2Braille(result);
                    }
                }
                case "application/x-hwp" -> result = hwpParser.parseHWP(file.getInputStream());
                case "application/msword", "application/vnd.openxmlformats-officedocument.wordprocessingml.document" ->
                        result = msWordParser.parseDOCX(file.getInputStream());
                default -> {
                    return null;
                }
            }
        }
        return result;
    }
}
