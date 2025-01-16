package org.vision.bridge.service.documentParser;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class HWPParserTest {

    @Test
    void parseHWP() throws Exception {

        HWPParser p = new HWPParser();
        String filePath = "C:\\Users\\euije\\Downloads\\71-1. 별첨(양식).hwp";
        String s = p.parseHWP(filePath);
        System.out.println(s);
    }

}