package org.vision.bridge.service.translation;

import org.vision.bridge.service.utils.EnglishUtils;
import org.vision.bridge.service.utils.JamoUtils;

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
        int wordType = -1;
        boolean isUpperOverThree = false, isQuote = false;
        for (int i = 0; i < words.length; i++) {
            char[] chars = words[i].toCharArray();
            StringBuilder word = new StringBuilder();
            for (int j = 0; j < chars.length; j++) {
                int type = getCharType(chars[j]);
                if (type == NUMBER && wordType == -1) { // 제 11절 40항
                    braille.append("⠼");
                }
                if (j + 1 < chars.length && wordType == NUMBER && (chars[j] == '.' || chars[j] == ',') && NUMBER == getCharType(chars[j + 1])) {
                    type = NUMBER;
                }
                if (wordType != -1 && type != wordType) {
                    braille.append(_translate(word, wordType));
                    if (type == NUMBER) { // 제 11절 40항
                        braille.append("⠼");
                    }
                    if (type == ENGLISH) {
                        braille.append("⠴");
                        int countUpperWord = countUpperWord(words, i);
                        if (countUpperWord >= 3) {
                            braille.append("⠠⠠⠠");
                            for (int o = countUpperWord; o > 0; o--) {
                                words[i + o] = words[i + o].toLowerCase();
                            }
                            chars = String.valueOf(chars).toLowerCase().toCharArray();
                            isUpperOverThree = true;
                        }
                    }
                    if (wordType == ENGLISH) {
                        if (isUpperOverThree) {
                            braille.append("⠠⠄");
                            isUpperOverThree = false;
                        }
                        if (!isQuote // 제 34항
                            && (chars[j] != ',' && chars[j] != ':' && chars[j] != ';' && chars[j] != '―') // 제 10절 33항
                            && (chars[j] != '.' && chars[j] != '?' && chars[j] != '!' && chars[j] != '…')  // 제 10절 33항 다만
                            && (type != NUMBER) // 제 35항
                        )
                            braille.append("⠲");
                    }
                    if (wordType == NUMBER && type == KOREAN) { // 제 44항 다만
                        String target = String.valueOf(chars[j]);
                        char cho = JamoUtils.getCho(target);
                        String removedCho = JamoUtils.removeCho(target);
                        if (cho  == 'ㄴ' || cho == 'ㄷ' || cho == 'ㅁ' || cho == 'ㅋ' || cho == 'ㅌ' || cho == 'ㅍ' || cho == 'ㅎ' || removedCho.equals("운")) {
                            braille.append(" ");
                        }
                    }
                    word = new StringBuilder();
                    word.append(chars[j]);
                } else {
                    if (type != wordType && type == ENGLISH) {
                        braille.append("⠴");
                        int countUpperWord = countUpperWord(words, i);
                        if (countUpperWord >= 3) {
                            braille.append("⠠⠠⠠");
                            for (int o = countUpperWord - 1; o >= 0; o--) {
                                words[i + o] = words[i + o].toLowerCase();
                            }
                            chars = String.valueOf(chars).toLowerCase().toCharArray();
                            isUpperOverThree = true;
                        }
                    }
                    word.append(chars[j]);
                }
                if (type == SPECIAL_CHAR) {
                    if (chars[j] == '"' || chars[j] == '\'') {
                        isQuote = !isQuote;
                    } else if (chars[j] == '(' || chars[j] == '{' || chars[j] == '[') {
                        isQuote = true;
                    } else if (chars[j] == ')' || chars[j] == '}' || chars[j] == ']') {
                        isQuote = false;
                    }
                }
                wordType = type;
            }
            braille.append(_translate(word, wordType));
            if (i + 1 != words.length)
                braille.append(" ");
        }
        if (wordType == ENGLISH) {
            if (isUpperOverThree) {
                braille.append("⠠⠄");
            }
            braille.append("⠲");
        }
        return braille.toString();
    }

    private static String _translate(StringBuilder word, int type) {
        String braille;
        if (type == KOREAN) {
            braille = koreanTranslate(word.toString());
        } else if (type == ENGLISH) {
            braille = englishTranslate(word.toString());
        } else if (type == NUMBER) {
            braille = numberTranslate(word.toString());
        } else {
            braille = specialCharTranslate(word.toString());
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
                String jong = JamoUtils.removeJong(next);
                if ( (jong.equals("예")
                    || (jong.equals("애") ) &&
                        (splitOne.get(1).equals("ㅑ")
                    || splitOne.get(1).equals("ㅘ")
                    || splitOne.get(1).equals("ㅜ")
                    || splitOne.get(1).equals("ㅝ")) )
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

    private static final Map<Character, String> englishMap = new HashMap<>();
    static {
        englishMap.put('a', "⠁"); // 제 10절 28항
        englishMap.put('b', "⠃");
        englishMap.put('c', "⠉");
        englishMap.put('d', "⠙");
        englishMap.put('e', "⠑");
        englishMap.put('f', "⠋");
        englishMap.put('g', "⠛");
        englishMap.put('h', "⠓");
        englishMap.put('i', "⠊");
        englishMap.put('j', "⠚");
        englishMap.put('k', "⠅");
        englishMap.put('l', "⠇");
        englishMap.put('m', "⠍");
        englishMap.put('n', "⠝");
        englishMap.put('o', "⠕");
        englishMap.put('p', "⠏");
        englishMap.put('q', "⠟");
        englishMap.put('r', "⠗");
        englishMap.put('s', "⠎");
        englishMap.put('t', "⠞");
        englishMap.put('u', "⠥");
        englishMap.put('v', "⠧");
        englishMap.put('w', "⠺");
        englishMap.put('x', "⠭");
        englishMap.put('y', "⠽");
        englishMap.put('z', "⠵");
        englishMap.put('A', "⠠⠁");
        englishMap.put('B', "⠠⠃");
        englishMap.put('C', "⠠⠉");
        englishMap.put('D', "⠠⠙");
        englishMap.put('E', "⠠⠑");
        englishMap.put('F', "⠠⠋");
        englishMap.put('G', "⠠⠛");
        englishMap.put('H', "⠠⠓");
        englishMap.put('I', "⠠⠊");
        englishMap.put('J', "⠠⠚");
        englishMap.put('K', "⠠⠅");
        englishMap.put('L', "⠠⠇");
        englishMap.put('M', "⠠⠍");
        englishMap.put('N', "⠠⠝");
        englishMap.put('O', "⠠⠕");
        englishMap.put('P', "⠠⠏");
        englishMap.put('Q', "⠠⠟");
        englishMap.put('R', "⠠⠗");
        englishMap.put('S', "⠠⠎");
        englishMap.put('T', "⠠⠞");
        englishMap.put('U', "⠠⠥");
        englishMap.put('V', "⠠⠧");
        englishMap.put('W', "⠠⠺");
        englishMap.put('X', "⠠⠭");
        englishMap.put('Y', "⠠⠽");
        englishMap.put('Z', "⠠⠵");
    }

    // 약자 규정 https://test.kbuwel.or.kr/Braille/Boards/ExamFiles/Details/31?Page=5
    private static String englishTranslate(String word) {
        StringBuilder stringBuilder = new StringBuilder();
        char[] charArray = word.toCharArray();
        for (int i = 0; i < charArray.length; i++) {
            for (int j = i; j < charArray.length && charArray[j] >= 'A' && charArray[j] <= 'Z'; j++) {
                if (j - i == 1) {
                    stringBuilder.append("⠠⠠");
                    charArray[j] = Character.toLowerCase(charArray[j]);
                    charArray[j - 1] = Character.toLowerCase(charArray[j - 1]);
                } else if (j - i > 1){
                    charArray[j] = Character.toLowerCase(charArray[j]);
                }
            }
            stringBuilder.append(englishMap.get(charArray[i]));
        }
        return stringBuilder.toString();
    }

    private static int countUpperWord(String[] words, int startIndex) {
        int i = startIndex;
        for (; i < words.length; i++) {
            if (!EnglishUtils.isAllUpperCase(words[i])) {
                return i;
            }
        }
        return i;
    }

    private static final Map<Character, String> numberMap = new HashMap<>();
    static {
        numberMap.put('1', "⠁"); // 제 11절 40항
        numberMap.put('2', "⠃");
        numberMap.put('3', "⠉");
        numberMap.put('4', "⠙");
        numberMap.put('5', "⠑");
        numberMap.put('6', "⠋");
        numberMap.put('7', "⠛");
        numberMap.put('8', "⠓");
        numberMap.put('9', "⠊");
        numberMap.put('0', "⠚");
        numberMap.put(',', "⠂");
        numberMap.put('.', "⠲");
    }

    private static String numberTranslate(String word) {
        StringBuilder stringBuilder = new StringBuilder();
        char[] charArray = word.toCharArray();
        for (int i = 0; i < charArray.length; i++) {
            stringBuilder.append(numberMap.get(charArray[i]));
        }
        return stringBuilder.toString();
    }

    // 특수문자는
    private static final Map<Character, String> specialCharMap = new HashMap<>();
    static {
        specialCharMap.put('+', "⠢"); // 제 44항
        specialCharMap.put('-', "⠔");
        specialCharMap.put('×', "⠡");
        specialCharMap.put('÷', "⠌⠌");
        specialCharMap.put('=', "⠒⠒");
        specialCharMap.put('>', "⠢⠢");
        specialCharMap.put('<', "⠔⠔");
        specialCharMap.put('.', "⠲"); // 제 13절 49항
        specialCharMap.put('?', "⠦");
        specialCharMap.put('!', "⠖");
        specialCharMap.put(',', "⠐");
        specialCharMap.put('·', "⠐⠆");
        specialCharMap.put(':', "⠐⠂");
        specialCharMap.put(';', "⠰⠆");
        specialCharMap.put('/', "⠸⠌");
        specialCharMap.put('…', "⠠⠠⠠");
        specialCharMap.put('“', "⠦");
        specialCharMap.put('"', "⠦");
        specialCharMap.put('”', "⠴");
        specialCharMap.put('‘', "⠠⠦");
        specialCharMap.put('\'', "⠠⠦");
        specialCharMap.put('’', "⠴⠄");
        specialCharMap.put('(', "⠦⠄");
        specialCharMap.put(')', "⠠⠴");
        specialCharMap.put('{', "⠦⠂");
        specialCharMap.put('}', "⠐⠴");
        specialCharMap.put('[', "⠦⠆");
        specialCharMap.put(']', "⠰⠴");
        specialCharMap.put('『', "⠰⠦");
        specialCharMap.put('』', "⠴⠆");
        specialCharMap.put('「', "⠐⠦");
        specialCharMap.put('」', "⠴⠂");
        specialCharMap.put('《', "⠰⠶");
        specialCharMap.put('》', "⠶⠆");
        specialCharMap.put('〈', "⠐⠶");
        specialCharMap.put('〉', "⠶⠂");
        specialCharMap.put('―', "⠤⠤");
        specialCharMap.put('∼', "⠈⠔");
        specialCharMap.put('̊', "⠠⠤");
        specialCharMap.put('_', "⠤⠄");
        specialCharMap.put('○', "⠸⠴⠇");
        specialCharMap.put('△', "⠸⠬⠇");
        specialCharMap.put('□', "⠸⠶⠇");
    }

    private static String specialCharTranslate(String word) {
        StringBuilder stringBuilder = new StringBuilder();
        char[] charArray = word.toCharArray();
        for (int i = 0; i < charArray.length; i++) {
            stringBuilder.append(specialCharMap.getOrDefault(charArray[i], ""));
        }
        return stringBuilder.toString();
    }
}
