package org.vision.bridge.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.vision.bridge.service.brf.BRFParser;
import org.vision.bridge.service.documentParser.HWPParser;
import org.vision.bridge.service.documentParser.MSWordParser;
import org.vision.bridge.service.documentParser.PDFParser;
import org.vision.bridge.service.documentParser.PlainTextParser;
import org.vision.bridge.service.utils.BrailleUtils;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.util.Map;


@Controller
@RequestMapping("/document")
public class DocumentController {

    @Autowired
    public PDFParser pdfParser;
    @Autowired
    public PlainTextParser plainTextParser;
    @Autowired
    public HWPParser hwpParser;
    @Autowired
    public BRFParser brfParser;
    @Autowired
    public MSWordParser msWordParser;

    @PostMapping("/text")
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


    @PostMapping("/braille")
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
        String originalFilename = file.getOriginalFilename();

        if (mimeType != null) {
            switch (mimeType) {
                case "application/pdf" -> result = pdfParser.parse(file.getInputStream());
                case "text/plain", "application/octet-stream" -> {
                    assert originalFilename != null;
                    if (originalFilename.toLowerCase().endsWith(".hwp")) {
                        result = hwpParser.parseHWP(file.getInputStream());
                    } else {
                        result = plainTextParser.parse(file.getInputStream());
                        if (originalFilename.toLowerCase().endsWith(".brf")) {
                            result = brfParser.translateBRF2Braille(result);
                        }
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

    @PostMapping("/brf-download")
    public ResponseEntity<?> parseToBraille(@RequestBody Map<String, String> request) {
        try {
            String sourceText = request.get("sourceText");
            String fileName = "VISION_BRIDGE";
            String extension = ".brf";
            File tempFile = File.createTempFile(fileName, extension);
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile))) {
                writer.write(brfParser.translateBraille2BRF(sourceText));
            }

            InputStreamResource resource = new InputStreamResource(new FileInputStream(tempFile));

            return ResponseEntity.ok()
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=" + fileName + extension)
                    .contentType(MediaType.APPLICATION_OCTET_STREAM)
                    .contentLength(tempFile.length())
                    .body(resource);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("BRF 생성에 실패 했습니다.");
        }
    }
}
