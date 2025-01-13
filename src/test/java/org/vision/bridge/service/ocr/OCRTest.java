package org.vision.bridge.service.ocr;

import org.junit.jupiter.api.Test;
import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.File;
import java.time.Duration;
import java.time.Instant;

public class OCRTest {
    @Test
    public void test() {
        OCR ocr = new OCR(OCRLang.KOR);
        try {
            long startTime = System.nanoTime();
            String recognize = ocr.recognize("src/test/resources/ocrsample.png");
            System.out.println(recognize);
            long endTime = System.nanoTime();
            long duration = (endTime - startTime) / 1_000_000;  // 나노초 → 밀리초 변환
            System.out.println("실행 시간: " + duration + "ms");
        } catch (TesseractException e) {
            System.err.println("OCR 오류 발생: " + e.getMessage());
        }
    }
    @Test
    public void test2() {
        OCR ocr = new OCR(OCRLang.KOR);
        try {
            long startTime = System.nanoTime();
            String recognize = ocr.recognize("src/test/resources/ocrsample2.png");
            System.out.println(recognize);
            long endTime = System.nanoTime();
            long duration = (endTime - startTime) / 1_000_000;  // 나노초 → 밀리초 변환
            System.out.println("실행 시간: " + duration + "ms");
        } catch (TesseractException e) {
            System.err.println("OCR 오류 발생: " + e.getMessage());
        }
    }
}
