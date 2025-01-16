package org.vision.bridge.service.documentParser;

import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class MSWordParserTest {

    @Test
    void parseDOCX() throws IOException {

        MSWordParser p = new MSWordParser();
        String filePath = "C:\\Users\\euije\\Downloads\\12조 최종 보고서.docx";
        String s = p.parseDOCX(filePath);
        System.out.println(s);
    }
}