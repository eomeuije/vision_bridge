package org.vision.bridge.service.translation;

import org.vision.bridge.service.utils.JamoUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Translator2Text {

    private static final Map<String, String> choMap = new HashMap<>();
    static {
        choMap.put("⠈", "ㄱ"); // 제 1절 1항
        choMap.put("⠉", "ㄴ");
        choMap.put("⠊", "ㄷ");
        choMap.put("⠐", "ㄹ");
        choMap.put("⠑", "ㅁ");
        choMap.put("⠘", "ㅂ");
        choMap.put("⠠", "ㅅ");
        choMap.put("⠛", "ㅇ");
        choMap.put("⠨", "ㅈ");
        choMap.put("⠰", "ㅊ");
        choMap.put("⠋", "ㅋ");
        choMap.put("⠓", "ㅌ");
        choMap.put("⠙", "ㅍ");
        choMap.put("⠚", "ㅎ");
        choMap.put("⠠⠈", "ㄲ"); // 제 1절 2항
        choMap.put("⠠⠊", "ㄸ");
        choMap.put("⠠⠘", "ㅃ");
        choMap.put("⠠⠠", "ㅆ");
        choMap.put("⠠⠨", "ㅉ");
    }

    private static final Map<String, String> jungMap = new HashMap<>();
    static {
        jungMap.put("⠣", "ㅏ"); // 제 3절 6항
        jungMap.put("⠜", "ㅑ");
        jungMap.put("⠎", "ㅓ");
        jungMap.put("⠱", "ㅕ");
        jungMap.put("⠥", "ㅗ");
        jungMap.put("⠬", "ㅛ");
        jungMap.put("⠍", "ㅜ");
        jungMap.put("⠩", "ㅠ");
        jungMap.put("⠪", "ㅡ");
        jungMap.put("⠕", "ㅣ");
        jungMap.put("⠗", "ㅐ");
        jungMap.put("⠤⠗", "ㅐ");
        jungMap.put("⠜⠗", "ㅒ"); // 제 3절 7항
        jungMap.put("⠝", "ㅔ");
        jungMap.put("⠌", "ㅖ");
        jungMap.put("⠤⠌", "ㅖ");
        jungMap.put("⠧", "ㅘ");
        jungMap.put("⠧⠗", "ㅙ");
        jungMap.put("⠽", "ㅚ");
        jungMap.put("⠏", "ㅝ");
        jungMap.put("⠏⠗", "ㅞ");
        jungMap.put("⠍⠗", "ㅟ");
        jungMap.put("⠺", "ㅢ");
    }


    private static final Map<String, String> jongMap = new HashMap<>();
    static {
        jongMap.put("⠁", "ᆨ"); // 제 2절 3항
        jongMap.put("⠒", "ᆫ");
        jongMap.put("⠔", "ᆮ");
        jongMap.put("⠂", "ᆯ");
        jongMap.put("⠢", "ᆷ");
        jongMap.put("⠃", "ᆸ");
        jongMap.put("⠄", "ᆺ");
        jongMap.put("⠶", "ᆼ");
        jongMap.put("⠅", "ᆽ");
        jongMap.put("⠆", "ᆾ");
        jongMap.put("⠖", "ᆿ");
        jongMap.put("⠦", "ᇀ");
        jongMap.put("⠲", "ᇁ");
        jongMap.put("⠴", "ᇂ");
        jongMap.put("⠁⠁", "ᆩ"); // 제 2절 4항
        jongMap.put("⠌", "ᆻ");
        jongMap.put("⠁⠄", "ᆪ"); // 제 2절 5항
        jongMap.put("⠒⠅", "ᆬ");
        jongMap.put("⠒⠴", "ᆭ");
        jongMap.put("⠂⠁", "ᆰ");
        jongMap.put("⠂⠢", "ᆱ");
        jongMap.put("⠂⠃", "ᆲ");
        jongMap.put("⠂⠄", "ᆳ");
        jongMap.put("⠂⠦", "ᆴ");
        jongMap.put("⠂⠲", "ᆵ");
        jongMap.put("⠂⠴", "ᆶ");
        jongMap.put("⠃⠄", "ᆹ");
    }

    private static final Map<String, String> abbAMap = new HashMap<>();
    static {
        abbAMap.put("⠫", "가"); // 제 6절 13항
        abbAMap.put("⠠⠫", "까");
        abbAMap.put("⠉", "나");
        abbAMap.put("⠊", "다");
        abbAMap.put("⠠⠊", "따");
        abbAMap.put("⠑", "마");
        abbAMap.put("⠘", "바");
        abbAMap.put("⠠⠘", "빠");
        abbAMap.put("⠇", "사");
        abbAMap.put("⠠⠇", "싸");
        abbAMap.put("⠨", "자");
        abbAMap.put("⠠⠨", "짜");
        abbAMap.put("⠋", "카");
        abbAMap.put("⠓", "타");
        abbAMap.put("⠙", "파");
        abbAMap.put("⠚", "하");
    }

    private static final Map<String, String> abbBMap = new HashMap<>();
    static {
        abbBMap.put("⠹", "억"); // 제 6절 15항
        abbBMap.put("⠹⠁", "얶"); // 제 6절 15항
        abbBMap.put("⠾", "언");
        abbBMap.put("⠞", "얼");
        abbBMap.put("⠡", "연");
        abbBMap.put("⠳", "열");
        abbBMap.put("⠻", "영");
        abbBMap.put("⠭", "옥");
        abbBMap.put("⠭⠁", "옦");
        abbBMap.put("⠷", "온");
        abbBMap.put("⠿", "옹");
        abbBMap.put("⠛", "운");
        abbBMap.put("⠯", "울");
        abbBMap.put("⠵", "은");
        abbBMap.put("⠮", "을");
        abbBMap.put("⠟", "인");
        abbBMap.put("⠸⠎", "것");
        abbBMap.put("⠸⠎⠄", "겄");
        abbBMap.put("⠠⠸⠎", "껏");
        abbBMap.put("⠠⠸⠎⠄", "껐");
        abbBMap.put("⠠⠻", "성"); // 제 6절 17항
        abbBMap.put("⠠⠠⠻", "썽");
        abbBMap.put("⠨⠻", "정");
        abbBMap.put("⠠⠨⠻", "쩡");
        abbBMap.put("⠆⠻", "청");
        abbBMap.put("⠹⠄", "얷"); // 제 6절 15항 붙임
        abbBMap.put("⠾⠅", "얹");
        abbBMap.put("⠾⠴", "얺");
        abbBMap.put("⠞⠁", "얽");
        abbBMap.put("⠞⠢", "얾");
        abbBMap.put("⠞⠃", "얿");
        abbBMap.put("⠞⠄", "엀");
        abbBMap.put("⠞⠦", "엁");
        abbBMap.put("⠞⠲", "엂");
        abbBMap.put("⠞⠴", "엃");
        abbBMap.put("⠡⠅", "엱");
        abbBMap.put("⠡⠴", "엲");
        abbBMap.put("⠳⠁", "엵");
        abbBMap.put("⠳⠢", "엶");
        abbBMap.put("⠳⠃", "엷");
        abbBMap.put("⠳⠄", "엸");
        abbBMap.put("⠳⠦", "엹");
        abbBMap.put("⠳⠲", "엺");
        abbBMap.put("⠳⠴", "엻");
        abbBMap.put("⠭⠄", "옧");
        abbBMap.put("⠷⠅", "옩");
        abbBMap.put("⠷⠴", "옪");
        abbBMap.put("⠛⠴", "욶");
        abbBMap.put("⠛⠅", "욵");
        abbBMap.put("⠯⠁", "욹");
        abbBMap.put("⠯⠢", "욺");
        abbBMap.put("⠯⠃", "욻");
        abbBMap.put("⠯⠄", "욼");
        abbBMap.put("⠯⠦", "욽");
        abbBMap.put("⠯⠲", "욾");
        abbBMap.put("⠯⠴", "욿");
        abbBMap.put("⠵⠅", "읁");
        abbBMap.put("⠵⠴", "읂");
        abbBMap.put("⠮⠁", "읅");
        abbBMap.put("⠮⠢", "읆");
        abbBMap.put("⠮⠃", "읇");
        abbBMap.put("⠮⠄", "읈");
        abbBMap.put("⠮⠦", "읉");
        abbBMap.put("⠮⠴", "읋");
        abbBMap.put("⠮⠲", "읊");
        abbBMap.put("⠟⠅", "읹");
        abbBMap.put("⠟⠴", "읺");
    }

    private static final Map<String, String> abbWordMap = new HashMap<>(); // 제 6절 약자/약어 맵

    static {
        abbWordMap.put("⠁⠎", "그래서"); // 제 7절 18항
        abbWordMap.put("⠁⠉", "그러나");
        abbWordMap.put("⠁⠒", "그러면");
        abbWordMap.put("⠁⠢", "그러므로");
        abbWordMap.put("⠁⠝", "그런데");
        abbWordMap.put("⠁⠥", "그리고");
        abbWordMap.put("⠁⠱", "그리하여");
    }

    private static final Map<String, String> choOnlyMap = new HashMap<>(); // 제 6절 약자/약어 맵
    static {
        choOnlyMap.put("⠿⠁", "ㄱ");
        choOnlyMap.put("⠿⠒", "ㄴ");
        choOnlyMap.put("⠿⠔", "ㄷ");
        choOnlyMap.put("⠿⠂", "ㄹ");
        choOnlyMap.put("⠿⠢", "ㅁ");
        choOnlyMap.put("⠿⠃", "ㅂ");
        choOnlyMap.put("⠿⠄", "ㅅ");
        choOnlyMap.put("⠿⠶", "ㅇ");
        choOnlyMap.put("⠿⠅", "ㅈ");
        choOnlyMap.put("⠿⠆", "ㅊ");
        choOnlyMap.put("⠿⠖", "ㅋ");
        choOnlyMap.put("⠿⠦", "ㅌ");
        choOnlyMap.put("⠿⠲", "ㅍ");
        choOnlyMap.put("⠿⠴", "ㅎ");
        choOnlyMap.put("⠿⠁⠁", "ㄲ");
        choOnlyMap.put("⠿⠌", "ㅆ");
        choOnlyMap.put("⠿⠁⠄", "ㄳ");
        choOnlyMap.put("⠿⠒⠅", "ㄵ");
        choOnlyMap.put("⠿⠒⠴", "ㄶ");
        choOnlyMap.put("⠿⠂⠁", "ㄺ");
        choOnlyMap.put("⠿⠂⠢", "ㄻ");
        choOnlyMap.put("⠿⠂⠃", "ㄼ");
        choOnlyMap.put("⠿⠂⠄", "ㄽ");
        choOnlyMap.put("⠿⠂⠦", "ㄾ");
        choOnlyMap.put("⠿⠂⠲", "ㄿ");
        choOnlyMap.put("⠿⠂⠴", "ㅀ");
        choOnlyMap.put("⠿⠃⠄", "ㅄ");
        choOnlyMap.put("⠸⠁", "ㄱ");
        choOnlyMap.put("⠸⠒", "ㄴ");
        choOnlyMap.put("⠸⠔", "ㄷ");
        choOnlyMap.put("⠸⠂", "ㄹ");
        choOnlyMap.put("⠸⠢", "ㅁ");
        choOnlyMap.put("⠸⠃", "ㅂ");
        choOnlyMap.put("⠸⠄", "ㅅ");
        choOnlyMap.put("⠸⠶", "ㅇ");
        choOnlyMap.put("⠸⠅", "ㅈ");
        choOnlyMap.put("⠸⠆", "ㅊ");
        choOnlyMap.put("⠸⠖", "ㅋ");
        choOnlyMap.put("⠸⠦", "ㅌ");
        choOnlyMap.put("⠸⠲", "ㅍ");
        choOnlyMap.put("⠸⠴", "ㅎ");
        choOnlyMap.put("⠸⠁⠁", "ㄲ");
        choOnlyMap.put("⠸⠁⠄", "ㄳ");
        choOnlyMap.put("⠸⠒⠅", "ㄵ");
        choOnlyMap.put("⠸⠒⠴", "ㄶ");
        choOnlyMap.put("⠸⠂⠁", "ㄺ");
        choOnlyMap.put("⠸⠂⠢", "ㄻ");
        choOnlyMap.put("⠸⠂⠃", "ㄼ");
        choOnlyMap.put("⠸⠂⠄", "ㄽ");
        choOnlyMap.put("⠸⠂⠦", "ㄾ");
        choOnlyMap.put("⠸⠂⠲", "ㄿ");
        choOnlyMap.put("⠸⠂⠴", "ㅀ");
        choOnlyMap.put("⠸⠃⠄", "ㅄ");
    }

    private static final Map<Character, String> numberMap = new HashMap<>();
    static {
        numberMap.put('⠁', "1"); // 제 11절 40항
        numberMap.put('⠃', "2");
        numberMap.put('⠉', "3");
        numberMap.put('⠙', "4");
        numberMap.put('⠑', "5");
        numberMap.put('⠋', "6");
        numberMap.put('⠛', "7");
        numberMap.put('⠓', "8");
        numberMap.put('⠊', "9");
        numberMap.put('⠚', "0");
        numberMap.put('⠂', ",");
        numberMap.put('⠲', ".");
    }


    private static final Map<String, String> specialCharMap = new HashMap<>();
    static {
        specialCharMap.put("⠢", "+"); // 제 44항
        specialCharMap.put("⠔", "-");
        specialCharMap.put("⠡", "×");
        specialCharMap.put("⠌⠌", "÷");
        specialCharMap.put("⠒⠒", "=");
        specialCharMap.put("⠢⠢", ">");
        specialCharMap.put("⠔⠔", "<");
        specialCharMap.put("⠲", "."); // 제 13절 49항
        specialCharMap.put("⠦", "?");
        specialCharMap.put("⠖", "!");
        specialCharMap.put("⠐", ",");
        specialCharMap.put("⠐⠆", "·");
        specialCharMap.put("⠐⠂", ":");
        specialCharMap.put("⠰⠆", ";");
        specialCharMap.put("⠸⠌", "/");
        specialCharMap.put("⠠⠠⠠", "…");
        specialCharMap.put("⠠⠦","'");
        specialCharMap.put("⠴⠄", "'");
        specialCharMap.put("⠦⠄", "(");
        specialCharMap.put("⠠⠴", ")");
        specialCharMap.put("⠦⠂", "{");
        specialCharMap.put("⠐⠴", "}");
        specialCharMap.put("⠦⠆", "[");
        specialCharMap.put("⠰⠴", "]");
        specialCharMap.put("⠰⠦", "『");
        specialCharMap.put("⠴⠆", "』");
        specialCharMap.put("⠐⠦", "「");
        specialCharMap.put("⠴⠂", "」");
        specialCharMap.put("⠰⠶", "《");
        specialCharMap.put("⠶⠆", "》");
        specialCharMap.put("⠐⠶", "〈");
        specialCharMap.put("⠶⠂", "〉");
        specialCharMap.put("⠤⠤", "―");
        specialCharMap.put("⠈⠔", "∼");
        specialCharMap.put("⠠⠤", "̊");
        specialCharMap.put("⠤⠄", "_");
        specialCharMap.put("⠸⠴⠇", "○");
        specialCharMap.put("⠸⠬⠇", "△");
        specialCharMap.put("⠸⠭⠇", "×");
        specialCharMap.put("⠸⠶⠇", "□");
    }


    private static final List<Map<String, String>> mapList = new ArrayList<>();
    static {
        mapList.add(choMap);
        mapList.add(jungMap);
        mapList.add(abbAMap);
        mapList.add(abbBMap);
        mapList.add(abbWordMap);
        mapList.add(choOnlyMap);
        mapList.add(specialCharMap);
    }

    private static final Map<Character, String> englishMap = new HashMap<>();

    static {
        englishMap.put('⠁', "a"); // 제 10절 28항
        englishMap.put('⠃', "b");
        englishMap.put('⠉', "c");
        englishMap.put('⠙', "d");
        englishMap.put('⠑', "e");
        englishMap.put('⠋', "f");
        englishMap.put('⠛', "g");
        englishMap.put('⠓', "h");
        englishMap.put('⠊', "i");
        englishMap.put('⠚', "j");
        englishMap.put('⠅', "k");
        englishMap.put('⠇', "l");
        englishMap.put('⠍', "m");
        englishMap.put('⠝', "n");
        englishMap.put('⠕', "o");
        englishMap.put('⠏', "p");
        englishMap.put('⠟', "q");
        englishMap.put('⠗', "r");
        englishMap.put('⠎', "s");
        englishMap.put('⠞', "t");
        englishMap.put('⠥', "u");
        englishMap.put('⠧', "v");
        englishMap.put('⠺', "w");
        englishMap.put('⠭', "x");
        englishMap.put('⠽', "y");
        englishMap.put('⠵', "z");
    }

    public static String translate(String braille) {
        StringBuilder text = new StringBuilder();
        String[] words = braille.split(" ");
        boolean english = false;
        boolean caps = false;
        boolean caps1word = false;
        boolean caps2word = false;
        for (int i = 0; i < words.length; i++) {
            String word = words[i];
            boolean number = false; // 숫자는 단어가 뛰워쓸때 모드 초기화
            for (int j = 0; j < word.length(); j++) {
                if (word.charAt(j) == '⠼') {
                    number = true;
                    continue;
                }
                if (english) {
                    if (word.charAt(j) == '⠲') {
                        english = false;
                        continue;
                    }
                    if (word.charAt(j) == '⠠' && word.length() - j >= 3 && word.charAt(j + 1) == '⠠' && word.charAt(j + 2) == '⠠') {
                        caps = true;
                        j += 2;
                        continue;
                    } else if (word.charAt(j) == '⠠' && word.length() - j >= 2 && word.charAt(j + 1) == '⠄') {
                        caps = false;
                        j += 1;
                    } else if (word.charAt(j) == '⠠' && word.length() - j >= 2 && word.charAt(j + 1) == '⠠') {
                        caps1word = true;
                        caps2word = true;
                        j += 1;
                        continue;
                    } else if (word.charAt(j) == '⠠' && word.length() - j >= 2) {
                        j++;
                        String upperCase = englishMap.get(word.charAt(j)).toUpperCase();
                        text.append(upperCase);
                        continue;
                    } else {
                        String en = englishMap.get(word.charAt(j));
                        if (caps) {
                            en = en.toUpperCase();
                        } else if (caps2word) {
                            en = en.toUpperCase();
                            if (word.length() - 1 == j) {
                                caps2word = false;
                            }
                        } else if (caps1word) {
                            en = en.toUpperCase();
                            if (word.length() - 1 == j) {
                                caps1word = false;
                            }
                        }
                        text.append(en);
                        continue;
                    }
                }
                if (number) {
                    String num = numberMap.get(word.charAt(j));
                    if (num != null) {
                        text.append(num);
                        continue;
                    }
                    else { number = false;}
                }
                int end = Math.min(word.length() - j - 1, 4);
                Map<String, String> existMap = getExistMap(word.substring(j, j + end + 1));
                while (end >= 0 && existMap == null) {
                    end--;
                    existMap = getExistMap(word.substring(j, j + end + 1));
                }
                String temp = "";
                if (existMap == abbWordMap) {
                    String abb = abbWordMap.get(word.substring(j, j + end + 1));
                    text.append(abb);
                    j += end;
                }
                if (existMap == choMap) {
                    char c = word.charAt(j + end);
                    int jungEndIndex;
                    if (j + end + 1 < word.length() && (c == '⠉' || c == '⠊' || c == '⠑' || c == '⠘' || c == '⠠' || c == '⠨' || c == '⠋' || c == '⠓' || c == '⠙')
                        && word.charAt(j + end + 1) == '⠌') {
                        jungEndIndex = end;
                    } else {
                        jungEndIndex = end + 1 + getJungEndIndex(word.substring(j + end + 1));
                    }
                    int jongEndIndex = jungEndIndex + 1 + getJongEndIndex(word.substring(j + jungEndIndex + 1));
                    String korean = translateKoreanByIndex(word.substring(j), end, jungEndIndex, jongEndIndex);
                    if (JamoUtils.isOnlyJamo(korean)) {
                        if (jongEndIndex - end >= 1) { // 약자 + 종성인 경우
                            String abb = abbAMap.getOrDefault(word.substring(j, j + end + 1), "");
                            List<String> strings = JamoUtils.splitOne(abb);
                            String s = word.substring(j + jongEndIndex, j + jongEndIndex + 1);
                            korean = JamoUtils.combine(strings.get(0), strings.get(1), jongMap.getOrDefault(s, ""));
                            j += jongEndIndex;
                        } else if (jungEndIndex - end < 1) {
                            int abbEndIndex = end + 1 + getAbbBEndIndex(word.substring(j + end + 1));
                            if (abbEndIndex > 0) { // abbB 약자
                                String abb = abbBMap.get(word.substring(j + end + 1, j + abbEndIndex + 1));
                                List<String> strings = JamoUtils.splitOne(abb);
                                korean = JamoUtils.combine(choMap.getOrDefault(word.substring(j, j + end + 1), ""), strings.get(1), strings.get(2));
                                j += abbEndIndex;
                            } else { // abbA 약자
                                korean = abbAMap.getOrDefault(word.substring(j, j + end + 1), "");
                                if (korean.isEmpty()) {
                                    korean = ",";
                                }
                                j += jongEndIndex;
                            }
                        }
                    } else {
                        j += jongEndIndex;
                    }
                    temp = korean;
                } else if (existMap == jungMap) {
                    String jung = jungMap.getOrDefault(word.substring(j, j + end + 1), "");
                    int jongEndIndex = end + 1 + getJongEndIndex(word.substring(j + end + 1));
                    temp = JamoUtils.combine("ㅇ", jung, jongMap.getOrDefault(word.substring(j + end + 1, j + jongEndIndex + 1), ""));
                    j += jongEndIndex;
                } else if (existMap == abbAMap) {
                    String abb = abbAMap.getOrDefault(word.substring(j, j + end + 1), "");
                    int jongEndIndex = end + 1 + getJongEndIndex(word.substring(j + end + 1));
                    List<String> splitOne = JamoUtils.splitOne(abb);
                    temp = JamoUtils.combine(splitOne.get(0), splitOne.get(1), jongMap.getOrDefault(word.substring(j + end + 1, j + jongEndIndex + 1), ""));
                    j += jongEndIndex;
                } else if (existMap == abbBMap) {
                    temp = abbBMap.get(word.substring(j, j + end + 1));
                    j += end;
                } else if (existMap == choOnlyMap) {
                    temp = choOnlyMap.get(word.substring(j, j + end + 1));
                    j += end;
                } else if (existMap == specialCharMap) {
                    temp = specialCharMap.get(word.substring(j, j + end + 1));
                    j += end;
                }
                if (temp != null) {
                    temp = temp.replaceAll("닾", "다.");
                }
                if (word.charAt(j) == '⠴' && existMap == null) {
                    english = true;
                    continue;
                }
                text.append(temp);
            }
            text.append(" ");
        }
        return text.toString();
    }

    private static String translateKoreanByIndex(String target, int choIndex, int jungIndex, int jongIndex) {
        String cho = target.substring(0, choIndex + 1);
        String jung = target.substring(choIndex + 1, jungIndex + 1);
        String jong = target.substring(jungIndex + 1, jongIndex + 1);
        return JamoUtils.combine(choMap.getOrDefault(cho, ""), jungMap.getOrDefault(jung, ""), jongMap.getOrDefault(jong, ""));
    }

    private static int getChoEndIndex(String s) {
        if (s.length() == 0) {
            return -1;
        } else if (choMap.containsKey(s)) {
            return s.length() - 1;
        } else {
            return getChoEndIndex(s.substring(0, s.length()-1));
        }
    }

    private static int getJungEndIndex(String s) {
        if (s.length() == 0) {
            return -1;
        } else if (jungMap.containsKey(s)) {
            return s.length() - 1;
        } else {
            return getJungEndIndex(s.substring(0, s.length()-1));
        }
    }

    private static int getJongEndIndex(String s) {
        if (s.length() == 0) {
            return -1;
        } else if (jongMap.containsKey(s)) {
            return s.length() - 1;
        } else {
            return getJongEndIndex(s.substring(0, s.length()-1));
        }
    }

    private static int getAbbBEndIndex(String s) {
        if (s.length() == 0) {
            return -1;
        } else if (abbBMap.containsKey(s)) {
            return s.length() - 1;
        } else {
            return getAbbBEndIndex(s.substring(0, s.length()-1));
        }
    }

    private static Map<String, String> getExistMap(String key) {
        for (Map<String, String> brailleMap : mapList) {
            if (brailleMap.containsKey(key)) {
                return brailleMap;
            }
        }
        return null;
    }
}
