package org.vision.bridge.service.utils;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author <a href="https://github.com/intotherealworld/jamo.git">intotherealworld/jamo.git</a>
 */
public class JamoUtils {
    private JamoUtils() {
    }

    public static final String [] CHOSUNG = {"ㄱ", "ㄲ", "ㄴ", "ㄷ", "ㄸ", "ㄹ", "ㅁ", "ㅂ", "ㅃ",
            "ㅅ","ㅆ", "ㅇ", "ㅈ", "ㅉ", "ㅊ", "ㅋ", "ㅌ", "ㅍ", "ㅎ"};
    public static final String [] JUNGSUNG = {"ㅏ", "ㅐ", "ㅑ", "ㅒ", "ㅓ", "ㅔ", "ㅕ", "ㅖ", "ㅗ", "ㅘ",
            "ㅙ", "ㅚ", "ㅛ", "ㅜ", "ㅝ", "ㅞ", "ㅟ", "ㅠ", "ㅡ", "ㅢ", "ㅣ"};
    public static final String [] JONGSUNG = {"", "ᆨ", "ᆩ", "ᆪ", "ᆫ", "ᆬ", "ᆭ", "ᆮ",
            "ᆯ", "ᆰ", "ᆱ", "ᆲ", "ᆳ", "ᆴ", "ᆵ", "ᆶ", "ᆷ", "ᆸ", "ᆹ", "ᆺ", "ᆻ", "ᆼ",
            "ᆽ", "ᆾ", "ᆿ", "ᇀ", "ᇁ", "ᇂ"};

    public static List<List<String>> split(String target) {
        return Arrays.stream(target.split(""))
                .map(JamoUtils::splitOne)
                .collect(Collectors.toList());
    }

    public static List<String> splitOne(String target) {
        int codePoint = Character.codePointAt(target, 0);
        if (codePoint >= 0xAC00 && codePoint <= 0xD79D) {
            int startValue = codePoint - 0xAC00;
            int jong = startValue % 28;
            int jung = ((startValue - jong) / 28) % 21;
            int cho = (((startValue - jong) / 28) - jung) / 21;
            return List.of(CHOSUNG[cho], JUNGSUNG[jung], JONGSUNG[jong]);
        }

        return List.of(target, "", "");
    }

    public static char getJung(String target) {
        List<String> strings = splitOne(target);
        return strings.get(1).toCharArray()[0];
    }

    public static String removeJong(String target) {
        List<String> strings = splitOne(target);
        return combine(strings.get(0), strings.get(1), "");
    }

    public static String removeCho(String target) {
        List<String> strings = splitOne(target);
        return combine("ㅇ", strings.get(1), strings.get(2));
    }

    public static char getCho(String target) {
        List<String> strings = splitOne(target);
        return strings.get(0).toCharArray()[0];
    }

    public static String combine(String cho, String jung, String jong) {
        int choIndex = -1;
        int jungIndex = -1;
        int jongIndex = -1;

        for (int i = 0; i < CHOSUNG.length; i++) {
            if (CHOSUNG[i].equals(cho)) {
                choIndex = i;
                break;
            }
        }
        for (int i = 0; i < JUNGSUNG.length; i++) {
            if (JUNGSUNG[i].equals(jung)) {
                jungIndex = i;
                break;
            }
        }
        for (int i = 0; i < JONGSUNG.length; i++) {
            if (JONGSUNG[i].equals(jong)) {
                jongIndex = i;
                break;
            }
        }
        if (choIndex == -1 && jungIndex == -1) {
            return jong;
        } else if (jungIndex == -1 && jongIndex == 0) {
            return cho;
        } else if (choIndex == -1 && jongIndex == 0){
            return jung;
        } else if (choIndex == -1 || jungIndex == -1) {
            return ""; // 초성 또는 중성이 없으면 빈 문자열 반환
        }
        int unicode = 0xAC00 + (choIndex * 21 * 28) + (jungIndex * 28) + jongIndex;
        return String.valueOf(Character.toChars(unicode));
    }

    public static boolean isOnlyJamo(String target) {
        for (char c : target.toCharArray()) {
            if (!isOnlyJamoOne(c)) {
                return false;
            }
        }
        return true;
    }

    public static boolean isOnlyJamoOne(char target) {
        for (String s : CHOSUNG) {
            if (target == s.charAt(0)) {
                return true;
            }
        }
        for (String s : JUNGSUNG) {
            if (target == s.charAt(0)) {
                return true;
            }
        }
        for (String s : JONGSUNG) {
            if (s.isEmpty()) continue;
            if (target == s.charAt(0)) {
                return true;
            }
        }
        return false;
    }
}