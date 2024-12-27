package org.vision.bridge.service.translation;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BrailleTranslatorTest {

    @Test
    void translate() {
        System.out.println(BrailleTranslator.translate("품삯 앉다 않다 읽다 옮기다"));
        Assertions.assertEquals(BrailleTranslator.translate("품삯 앉다 않다 읽다 옮기다")
                , "⠙⠍⠢⠇⠁⠄ ⠣⠒⠅⠊ ⠣⠒⠴⠊ ⠕⠂⠁⠊ ⠥⠂⠢⠈⠕⠊ ");
        System.out.println(BrailleTranslator.translate("삼각형 ㄱㄴㄷ"));
        Assertions.assertEquals(BrailleTranslator.translate("삼각형 ㄱㄴㄷ")
                , "⠇⠢⠫⠁⠚⠻ ⠿⠁⠿⠒⠿⠔ ");
        System.out.println(BrailleTranslator.translate("ㄹㄹ로마"));
        Assertions.assertEquals(BrailleTranslator.translate("ㄹㄹ로마")
                , "⠸⠂⠸⠂⠐⠥⠑ ");
        System.out.println(BrailleTranslator.translate("겪다 넋 얹다 벌레 옷걸이"));
        Assertions.assertEquals(BrailleTranslator.translate("겪다 넋 얹다 벌레 옷걸이")
                , "⠈⠱⠁⠁⠊ ⠉⠹⠄ ⠾⠅⠊ ⠘⠞⠐⠝ ⠥⠄⠈⠞⠕ ");
        System.out.println(BrailleTranslator.translate("까치 힘껏 갓"));
        Assertions.assertEquals(BrailleTranslator.translate("까치 힘껏 갓")
                , "⠠⠫⠰⠕ ⠚⠕⠢⠠⠸⠎ ⠫⠄ ");
        System.out.println(BrailleTranslator.translate("팠다"));
        Assertions.assertEquals(BrailleTranslator.translate("팠다")
                , "⠙⠣⠌⠊ ");
        System.out.println(BrailleTranslator.translate("그리고서 그리하여도 그러나요"));
        Assertions.assertEquals(BrailleTranslator.translate("그리고서 그리하여도 그러나요")
                , "⠁⠥⠠⠎ ⠁⠱⠊⠥ ⠁⠉⠬ ");
        System.out.println(BrailleTranslator.translate("아예 야애 파워앰프 수액"));
        Assertions.assertEquals(BrailleTranslator.translate("아예 야애 파워앰프 수액")
                , "⠣⠤⠌ ⠜⠤⠗ ⠙⠣⠏⠤⠗⠢⠙⠪ ⠠⠍⠤⠗⠁ ");
    }
}