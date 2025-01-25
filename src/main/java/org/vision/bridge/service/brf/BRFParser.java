package org.vision.bridge.service.brf;

import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class BRFParser {
    private static final Map<Character, Character> charToBRFMap = new HashMap<>();
    static {
        charToBRFMap.put(' ', ' ');
        charToBRFMap.put('⠁', 'a');
        charToBRFMap.put('⠂', '1');
        charToBRFMap.put('⠃', 'b');
        charToBRFMap.put('⠄', '\'');
        charToBRFMap.put('⠅', 'k');
        charToBRFMap.put('⠆', '2');
        charToBRFMap.put('⠇', 'l');
        charToBRFMap.put('⠈', '@'); // 또는 (`)
        charToBRFMap.put('⠉', 'c');
        charToBRFMap.put('⠊', 'i');
        charToBRFMap.put('⠋', 'f');
        charToBRFMap.put('⠌', '/');
        charToBRFMap.put('⠍', 'm');
        charToBRFMap.put('⠎', 's');
        charToBRFMap.put('⠏', 'p');
        charToBRFMap.put('⠐', '"');
        charToBRFMap.put('⠑', 'e');
        charToBRFMap.put('⠒', '3');
        charToBRFMap.put('⠓', 'h');
        charToBRFMap.put('⠔', '9');
        charToBRFMap.put('⠕', 'o');
        charToBRFMap.put('⠖', '6');
        charToBRFMap.put('⠗', 'r');
        charToBRFMap.put('⠘', '^');
        charToBRFMap.put('⠙', 'd');
        charToBRFMap.put('⠚', 'j');
        charToBRFMap.put('⠛', 'g');
        charToBRFMap.put('⠜', '>');
        charToBRFMap.put('⠝', 'n');
        charToBRFMap.put('⠞', 't');
        charToBRFMap.put('⠟', 'q');
        charToBRFMap.put('⠠', ',');
        charToBRFMap.put('⠡', '*');
        charToBRFMap.put('⠢', '5');
        charToBRFMap.put('⠣', '<');
        charToBRFMap.put('⠤', '-');
        charToBRFMap.put('⠥', 'u');
        charToBRFMap.put('⠦', '8');
        charToBRFMap.put('⠧', 'v');
        charToBRFMap.put('⠨', '.');
        charToBRFMap.put('⠩', '%');
        charToBRFMap.put('⠪', '{');
        charToBRFMap.put('⠫', '$');
        charToBRFMap.put('⠬', '+');
        charToBRFMap.put('⠭', 'x');
        charToBRFMap.put('⠮', '!');
        charToBRFMap.put('⠯', '&');
        charToBRFMap.put('⠰', ';');
        charToBRFMap.put('⠱', ':');
        charToBRFMap.put('⠲', '4');
        charToBRFMap.put('⠳', '\\'); // 또는 |
        charToBRFMap.put('⠴', '0');
        charToBRFMap.put('⠵', 'z');
        charToBRFMap.put('⠶', '7');
        charToBRFMap.put('⠷', '(');
        charToBRFMap.put('⠸', '_');
        charToBRFMap.put('⠹', '?');
        charToBRFMap.put('⠺', 'w');
        charToBRFMap.put('⠻', '}');
        charToBRFMap.put('⠼', '#');
        charToBRFMap.put('⠽', 'y');
        charToBRFMap.put('⠾', ')');
        charToBRFMap.put('⠿', '=');
    }

    private static final Map<Character, Character> BRFToCharMap = new HashMap<>();
    static {
        BRFToCharMap.put(' ', ' ');
        BRFToCharMap.put('a', '⠁');
        BRFToCharMap.put('1', '⠂');
        BRFToCharMap.put('b', '⠃');
        BRFToCharMap.put('\'', '⠄');
        BRFToCharMap.put('k', '⠅');
        BRFToCharMap.put('2', '⠆');
        BRFToCharMap.put('l', '⠇');
        BRFToCharMap.put('@', '⠈');
        BRFToCharMap.put('`', '⠈');
        BRFToCharMap.put('c', '⠉');
        BRFToCharMap.put('i', '⠊');
        BRFToCharMap.put('f', '⠋');
        BRFToCharMap.put('/', '⠌');
        BRFToCharMap.put('m', '⠍');
        BRFToCharMap.put('s', '⠎');
        BRFToCharMap.put('p', '⠏');
        BRFToCharMap.put('"', '⠐');
        BRFToCharMap.put('e', '⠑');
        BRFToCharMap.put('3', '⠒');
        BRFToCharMap.put('h', '⠓');
        BRFToCharMap.put('9', '⠔');
        BRFToCharMap.put('o', '⠕');
        BRFToCharMap.put('6', '⠖');
        BRFToCharMap.put('r', '⠗');
        BRFToCharMap.put('^', '⠘');
        BRFToCharMap.put('~', '⠘');
        BRFToCharMap.put('d', '⠙');
        BRFToCharMap.put('j', '⠚');
        BRFToCharMap.put('g', '⠛');
        BRFToCharMap.put('>', '⠜');
        BRFToCharMap.put('n', '⠝');
        BRFToCharMap.put('t', '⠞');
        BRFToCharMap.put('q', '⠟');
        BRFToCharMap.put(',', '⠠');
        BRFToCharMap.put('*', '⠡');
        BRFToCharMap.put('5', '⠢');
        BRFToCharMap.put('<', '⠣');
        BRFToCharMap.put('-', '⠤');
        BRFToCharMap.put('u', '⠥');
        BRFToCharMap.put('8', '⠦');
        BRFToCharMap.put('v', '⠧');
        BRFToCharMap.put('.', '⠨');
        BRFToCharMap.put('%', '⠩');
        BRFToCharMap.put('[', '⠪');
        BRFToCharMap.put('{', '⠪');
        BRFToCharMap.put('$', '⠫');
        BRFToCharMap.put('+', '⠬');
        BRFToCharMap.put('x', '⠭');
        BRFToCharMap.put('!', '⠮');
        BRFToCharMap.put('&', '⠯');
        BRFToCharMap.put(';', '⠰');
        BRFToCharMap.put(':', '⠱');
        BRFToCharMap.put('4', '⠲');
        BRFToCharMap.put('\\', '⠳');
        BRFToCharMap.put('|', '⠳');
        BRFToCharMap.put('0', '⠴');
        BRFToCharMap.put('z', '⠵');
        BRFToCharMap.put('7', '⠶');
        BRFToCharMap.put('(', '⠷');
        BRFToCharMap.put('_', '⠸');
        BRFToCharMap.put('?', '⠹');
        BRFToCharMap.put('w', '⠺');
        BRFToCharMap.put(']', '⠻');
        BRFToCharMap.put('}', '⠻');
        BRFToCharMap.put('#', '⠼');
        BRFToCharMap.put('y', '⠽');
        BRFToCharMap.put(')', '⠾');
        BRFToCharMap.put('=', '⠿');

    }

    public char getBRF(char braille) {
        return charToBRFMap.get(braille);
    }

    public char getBraille(char brf) {
        if (brf >= 'A' && brf <= 'Z') {
            brf = Character.toLowerCase(brf);
        }
        return BRFToCharMap.get(brf);
    }

    public String translateBRF2Braille(String brf) {
        StringBuilder braille = new StringBuilder();
        for (char c : brf.toCharArray()) {
            try {
                char braille1 = getBraille(c);
                braille.append(braille1);
            } catch (NullPointerException e) {
                braille.append(c);
            }
        }
        return braille.toString();
    }

    public String translateBraille2BRF(String braille) {
        StringBuilder brf = new StringBuilder();
        for (char c : braille.toCharArray()) {
            try {
                char brf1 = getBRF(c);
                brf.append(brf1);
            } catch (NullPointerException e) {
                brf.append(c);
            }
        }
        return brf.toString();
    }
}
