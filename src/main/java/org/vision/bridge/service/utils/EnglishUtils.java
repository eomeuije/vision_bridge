package org.vision.bridge.service.utils;

public class EnglishUtils {
    public static boolean isAllUpperCase(String word) {
        if (word.isEmpty()) return false;
        for (char c : word.toCharArray()) {
            if (!Character.isUpperCase(c)) {
                return false;
            }
        }
        return true;
    }
}
