package org.vision.bridge.service.brf;

import org.junit.jupiter.api.Test;

class BRFParserTest {
    @Test
    void getBraille() {
        BRFParser brf = new BRFParser();
        char a = brf.getBRF('⠼');
        char c = brf.getBraille(a);
        System.out.println(c);
    }

}