package org.vision.bridge.service.utils;

public class BrailleUtils {

    public static String brailleFilter(String text) {
        return text == null ? null : text.replaceAll("[^\\u2800-\\u28FF\\n \\t]", "");
    }
}
