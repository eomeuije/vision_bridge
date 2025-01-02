package org.vision.bridge.service.translation;

import org.junit.jupiter.api.Test;
import org.vision.bridge.service.utils.JamoUtils;

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