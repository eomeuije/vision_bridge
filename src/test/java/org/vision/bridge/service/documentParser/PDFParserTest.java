package org.vision.bridge.service.documentParser;

import org.junit.jupiter.api.Test;

import java.io.IOException;

class PDFParserTest {

    @Test
    void parseToText() throws IOException {
        PDFParser p = new PDFParser();
        String filePath = "C:\\Users\\euije\\Downloads\\[개정]+한국+점자+규정+전문.pdf";
        String s = p.parse(filePath);
        System.out.println(s);
    }
}