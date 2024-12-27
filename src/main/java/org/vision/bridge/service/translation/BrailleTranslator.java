package org.vision.bridge.service.translation;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BrailleTranslator {

    private static final int KOREAN = 1;
    private static final int ENGLISH = 2;
    private static final int NUMBER = 3;
    private static final int SPECIAL_CHAR = 4;

    private static final Map<String, String> koreanMap = new HashMap<>();

    static {
        koreanMap.put("ㄱ", "⠈"); // 제 1절 1항
        koreanMap.put("ㄴ", "⠉");
        koreanMap.put("ㄷ", "⠊");
        koreanMap.put("ㄹ", "⠐");
        koreanMap.put("ㅁ", "⠑");
        koreanMap.put("ㅂ", "⠘");
        koreanMap.put("ㅅ", "⠠");
        koreanMap.put("ㅇ", "⠛");
        koreanMap.put("ㅈ", "⠨");
        koreanMap.put("ㅊ", "⠰");
        koreanMap.put("ㅋ", "⠋");
        koreanMap.put("ㅌ", "⠓");
        koreanMap.put("ㅍ", "⠙");
        koreanMap.put("ㅎ", "⠚");
        koreanMap.put("ㄲ", "⠠⠈"); // 제 1절 2항
        koreanMap.put("ㄸ", "⠠⠊");
        koreanMap.put("ㅃ", "⠠⠘");
        koreanMap.put("ㅆ", "⠠⠠");
        koreanMap.put("ㅉ", "⠠⠨");
        koreanMap.put("ᆨ", "⠁"); // 제 2절 3항
        koreanMap.put("ᆫ", "⠒");
        koreanMap.put("ᆮ", "⠔");
        koreanMap.put("ᆯ", "⠂");
        koreanMap.put("ᆷ", "⠢");
        koreanMap.put("ᆸ", "⠃");
        koreanMap.put("ᆺ", "⠄");
        koreanMap.put("ᆼ", "⠶");
        koreanMap.put("ᆽ", "⠅");
        koreanMap.put("ᆾ", "⠆");
        koreanMap.put("ᆿ", "⠖");
        koreanMap.put("ᇀ", "⠦");
        koreanMap.put("ᇁ", "⠲");
        koreanMap.put("ᇂ", "⠴");
        koreanMap.put("ᆩ", "⠁⠁"); // 제 2절 4항
        koreanMap.put("ᆻ", "⠌");
        koreanMap.put("ᆪ", "⠁⠄"); // 제 2절 5항
        koreanMap.put("ᆬ", "⠒⠅");
        koreanMap.put("ᆭ", "⠒⠴");
        koreanMap.put("ᆰ", "⠂⠁");
        koreanMap.put("ᆱ", "⠂⠢");
        koreanMap.put("ᆲ", "⠂⠃");
        koreanMap.put("ᆳ", "⠂⠄");
        koreanMap.put("ᆴ", "⠂⠦");
        koreanMap.put("ᆵ", "⠂⠲");
        koreanMap.put("ᆶ", "⠂⠴");
        koreanMap.put("ᆹ", "⠃⠄");
        koreanMap.put("ㅏ", "⠣"); // 제 3절 6항
        koreanMap.put("ㅑ", "⠜");
        koreanMap.put("ㅓ", "⠎");
        koreanMap.put("ㅕ", "⠱");
        koreanMap.put("ㅗ", "⠥");
        koreanMap.put("ㅛ", "⠬");
        koreanMap.put("ㅜ", "⠍");
        koreanMap.put("ㅠ", "⠩");
        koreanMap.put("ㅡ", "⠪");
        koreanMap.put("ㅣ", "⠕");
        koreanMap.put("ㅐ", "⠗");
        koreanMap.put("ㅒ", "⠜⠗"); // 제 3절 7항
        koreanMap.put("ㅔ", "⠝");
        koreanMap.put("ㅖ", "⠌");
        koreanMap.put("ㅘ", "⠧");
        koreanMap.put("ㅙ", "⠧⠗");
        koreanMap.put("ㅚ", "⠽");
        koreanMap.put("ㅝ", "⠏");
        koreanMap.put("ㅞ", "⠏⠗");
        koreanMap.put("ㅟ", "⠍⠗");
        koreanMap.put("ㅢ", "⠺");
    }

    private static final Map<String, String> englishMap = new HashMap<>();
    private static final Map<Number, String> numberMap = new HashMap<>();
    private static final Map<String, String> specialCharMap = new HashMap<>();

    public static int getCharType(char c) {
        int type;
        if (isKorean(c)) {
            type = KOREAN;
        } else if (Character.isAlphabetic(c)) {
            type = ENGLISH;
        } else if (Character.isDigit(c)) {
            type = NUMBER;
        } else {
            type = SPECIAL_CHAR;
        }
        return type;
    }

    public static boolean isKorean(char ch) {
        return (ch >= '가' && ch <= '힣') || (ch >= 'ㄱ' && ch <= 'ㅎ') || (ch >= 'ㅏ' && ch <= 'ㅣ');
    }

    public static String translate(String text) {
        StringBuilder braille = new StringBuilder();
        String[] words = text.split(" ");
        for (int i = 0; i < words.length; i++) {
            char[] chars = words[i].toCharArray();
            StringBuilder word = new StringBuilder();
            int wordType = -1;
            for (int j = 0; j < chars.length; j++) {
                int type = getCharType(chars[j]);
                if (wordType != -1 && type != wordType) {
                    braille.append(_translate(word, type));
                    word = new StringBuilder();
                } else {
                    word.append(chars[j]);
                }
                wordType = type;
            }
            braille.append(_translate(word, wordType));
            braille.append(" ");
        }
        return braille.toString();
    }

    private static String _translate(StringBuilder word, int type) {
        String braille;
        if (type == KOREAN) {
            braille = koreanTranslate(word.toString());
        } else {
            braille = "";
        }
        return braille;
    }

    private static final Map<String, String> abbMap = new HashMap<>(); // 제 6절 약자/약어 맵

    static {
        abbMap.put("가", "⠫"); // 제 6절 13항
        abbMap.put("까", "⠠⠫"); // 제 6절 16항
        abbMap.put("나", "⠉");
        abbMap.put("다", "⠊");
        abbMap.put("마", "⠑");
        abbMap.put("바", "⠘");
        abbMap.put("사", "⠇");
        abbMap.put("싸", "⠠⠇");
        abbMap.put("자", "⠨");
        abbMap.put("카", "⠋");
        abbMap.put("타", "⠓");
        abbMap.put("파", "⠙");
        abbMap.put("하", "⠚");
        abbMap.put("억", "⠹"); // 제 6절 15항
        abbMap.put("언", "⠾");
        abbMap.put("얼", "⠞");
        abbMap.put("연", "⠡");
        abbMap.put("열", "⠳");
        abbMap.put("영", "⠻");
        abbMap.put("옥", "⠭");
        abbMap.put("온", "⠷");
        abbMap.put("옹", "⠿");
        abbMap.put("운", "⠛");
        abbMap.put("울", "⠯");
        abbMap.put("은", "⠵");
        abbMap.put("을", "⠮");
        abbMap.put("인", "⠟");
        abbMap.put("것", "⠸⠎");
        abbMap.put("껏", "⠠⠸⠎");
        abbMap.put("성", "⠠⠻"); // 제 6절 17항
        abbMap.put("썽", "⠠⠠⠻");
        abbMap.put("정", "⠨⠻");
        abbMap.put("쩡", "⠠⠨⠻");
        abbMap.put("청", "⠆⠻");
        abbMap.put("얷", "⠹⠄"); // 제 6절 15항 붙임
        abbMap.put("얹", "⠾⠅");
        abbMap.put("얺", "⠾⠴");
        abbMap.put("얽", "⠞⠁");
        abbMap.put("얾", "⠞⠢");
        abbMap.put("얿", "⠞⠃");
        abbMap.put("엀", "⠞⠄");
        abbMap.put("엁", "⠞⠦");
        abbMap.put("엂", "⠞⠲");
        abbMap.put("엃", "⠞⠴");
        abbMap.put("엱", "⠡⠅");
        abbMap.put("엲", "⠡⠴");
        abbMap.put("엵", "⠳⠁");
        abbMap.put("엶", "⠳⠢");
        abbMap.put("엷", "⠳⠃");
        abbMap.put("엸", "⠳⠄");
        abbMap.put("엹", "⠳⠦");
        abbMap.put("엺", "⠳⠲");
        abbMap.put("엻", "⠳⠴");
        abbMap.put("옧", "⠭⠄");
        abbMap.put("옩", "⠷⠅");
        abbMap.put("옪", "⠷⠴");
        abbMap.put("욶", "⠛⠴");
        abbMap.put("욵", "⠛⠅");
        abbMap.put("욹", "⠯⠁");
        abbMap.put("욺", "⠯⠢");
        abbMap.put("욻", "⠯⠃");
        abbMap.put("욼", "⠯⠄");
        abbMap.put("욽", "⠯⠦");
        abbMap.put("욾", "⠯⠲");
        abbMap.put("욿", "⠯⠴");
        abbMap.put("읁", "⠵⠅");
        abbMap.put("읂", "⠵⠴");
        abbMap.put("읅", "⠮⠁");
        abbMap.put("읆", "⠮⠢");
        abbMap.put("읇", "⠮⠃");
        abbMap.put("읈", "⠮⠄");
        abbMap.put("읉", "⠮⠦");
        abbMap.put("읋", "⠮⠴");
        abbMap.put("읊", "⠮⠲");
        abbMap.put("읹", "⠟⠅");
        abbMap.put("읺", "⠟⠴");
    }


    private static final Map<String, String> abbWordMap = new HashMap<>(); // 제 6절 약자/약어 맵

    static {
        abbWordMap.put("그래서", "⠁⠎"); // 제 7절 18항
        abbWordMap.put("그러나", "⠁⠉");
        abbWordMap.put("그러면", "⠁⠒");
        abbWordMap.put("그러므로", "⠁⠢");
        abbWordMap.put("그런데", "⠁⠝");
        abbWordMap.put("그리고", "⠁⠥");
        abbWordMap.put("그리하여", "⠁⠱");
    }

    private static boolean isWordChar(char ch) {
        return ch >= '가' && ch <= '힣';
    }

    private static boolean isWord(char[] chars) {
        for (char c : chars) {
            if (isWordChar(c))
                return true;
        }
        return false;
    }

    private static final String[] abbWords = {"그래서", "그러나", "그러면", "그러므로", "그런데", "그리고", "그리하여"};
    private static String koreanTranslate(String word) {
        StringBuilder stringBuilder = new StringBuilder();
        int i = 0;
        for (String aw : abbWords) {
            if (word.startsWith(aw)) {
                i = aw.length();
                stringBuilder.append(abbWordMap.get(aw));
                break;
            }
        }
        char[] charArray = word.toCharArray();
        for (; i < charArray.length; i++) {
            String umjeolOnly = getUmjeolOnly(charArray[i]);
            if (umjeolOnly != null) {
                if (isWord(charArray)) { // 제 4절 10항
                    stringBuilder.append("⠸");
                } else {
                    stringBuilder.append("⠿");
                }
                stringBuilder.append(umjeolOnly);
                continue;
            }
            String target = String.valueOf(charArray[i]);
            List<String> splitOne = JamoUtils.splitOne(target);
            int j;
            // 제 6절 14항 붙임
            String abb = target.equals("팠") ? null : abbCheck(splitOne.get(0), splitOne.get(1), "");
            if (abb != null) {
                boolean isAbb = true;
                if (splitOne.get(2).equals("")) { // 제 6절 14항
                    String combined = JamoUtils.combine(splitOne.get(0), splitOne.get(1), "");
                    if (!combined.equals("가") && !combined.equals("사")) {
                        if (i + 1 < charArray.length && JamoUtils.splitOne(String.valueOf(charArray[i + 1])).get(0).equals("ㅇ")) {
                            isAbb = false;
                        }
                    }
                }
                if (isAbb) {
                    stringBuilder.append(abb);
                    j = 2;
                } else {
                    j = 0;
                }
            } else {
                if (!splitOne.get(2).equals("")) { // 제 6절 15항
                    if (target.equals("것") || target.equals("껏") || target.equals("성") || target.equals("썽") || target.equals("정") || target.equals("쩡") || target.equals("청")) {
                        abb = abbMap.get(target);
                    } else {
                        abb = abbCheck("ㅇ", splitOne.get(1), splitOne.get(2));
                        if (abb != null && !splitOne.get(0).equals("ㅇ")) {
                            stringBuilder.append(koreanMap.get(splitOne.get(0)));
                        }
                    }
                    if (abb != null) {
                        stringBuilder.append(abb);
                        continue;
                    }
                }
                j = 0;
            }
            for (; j < splitOne.size(); j++) {
                String key = splitOne.get(j);
                if (key.equals("")) {
                    continue;
                }
                if (j == 0 && key.equals("ㅇ")) { // 제 1절 다만 1
                    continue;
                }
                stringBuilder.append(koreanMap.get(key));
            }
            if (i + 1 < charArray.length && splitOne.get(2).equals("")) { // 제 5절 11항
                String next = String.valueOf(charArray[i + 1]);
                if (JamoUtils.removeJong(next).equals("예")
                    || (JamoUtils.removeJong(next).equals("애") &&
                        splitOne.get(1).equals("ㅑ")
                    || splitOne.get(1).equals("ㅘ")
                    || splitOne.get(1).equals("ㅜ")
                    || splitOne.get(1).equals("ㅝ"))
                ) {
                    stringBuilder.append("⠤");
                }
            }
        }
        return stringBuilder.toString();
    }

    private static String abbCheck(String cho, String jung, String jong) {
        return abbMap.get(JamoUtils.combine(cho, jung, jong));
    }

    private static final Map<Character, String> choToJongMap = new HashMap<>();

    static {
        choToJongMap.put('ㄱ', "⠁"); // 제 4절 8항
        choToJongMap.put('ㄲ', "⠁⠁");
        choToJongMap.put('ㄴ', "⠒");
        choToJongMap.put('ㄷ', "⠔");
        choToJongMap.put('ㄸ', "⠠⠊");
        choToJongMap.put('ㄹ', "⠂");
        choToJongMap.put('ㅁ', "⠢");
        choToJongMap.put('ㅂ', "⠃");
        choToJongMap.put('ㅃ', "⠠⠘");
        choToJongMap.put('ㅅ', "⠄");
        choToJongMap.put('ㅆ', "⠌");
        choToJongMap.put('ㅇ', "⠶");
        choToJongMap.put('ㅈ', "⠅");
        choToJongMap.put('ㅉ', "⠠⠨");
        choToJongMap.put('ㅊ', "⠆");
        choToJongMap.put('ㅋ', "⠖");
        choToJongMap.put('ㅌ', "⠦");
        choToJongMap.put('ㅍ', "⠲");
        choToJongMap.put('ㅎ', "⠴");
    }

    private static String getUmjeolOnly(char ch) { // 제 4절 자/모음 단독
        if (ch >= 'ㄱ' && ch <= 'ㅎ') {
            return choToJongMap.get(ch);
        } else if (ch >= 'ㅏ' && ch <= 'ㅣ') {
            return koreanMap.get(String.valueOf(ch));
        } else {
            return null;
        }
    }
}
