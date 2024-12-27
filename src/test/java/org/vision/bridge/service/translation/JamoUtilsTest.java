package org.vision.bridge.service.translation;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class JamoUtilsTest {

    @Test
    void split() {
        System.out.println(JamoUtils.split("ㅚ"));
    }

    @Test
    void combine() {
        System.out.println(JamoUtils.combine("ㅎ", "ㅢ", ""));
    }
}