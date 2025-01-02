package org.vision.bridge.service.translation;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.vision.bridge.service.brf.BRFParser;

import static org.junit.jupiter.api.Assertions.*;

class BrailleTranslatorTest {

    @Test
    void translate() {
        System.out.println(BrailleTranslator.translate("품삯 앉다 않다 읽다 옮기다"));
        Assertions.assertEquals(BrailleTranslator.translate("품삯 앉다 않다 읽다 옮기다")
                , "⠙⠍⠢⠇⠁⠄ ⠣⠒⠅⠊ ⠣⠒⠴⠊ ⠕⠂⠁⠊ ⠥⠂⠢⠈⠕⠊");
        System.out.println(BrailleTranslator.translate("삼각형 ㄱㄴㄷ"));
        Assertions.assertEquals(BrailleTranslator.translate("삼각형 ㄱㄴㄷ")
                , "⠇⠢⠫⠁⠚⠻ ⠿⠁⠿⠒⠿⠔");
        System.out.println(BrailleTranslator.translate("ㄹㄹ로마"));
        Assertions.assertEquals(BrailleTranslator.translate("ㄹㄹ로마")
                , "⠸⠂⠸⠂⠐⠥⠑");
        System.out.println(BrailleTranslator.translate("겪다 넋 얹다 벌레 옷걸이"));
        Assertions.assertEquals(BrailleTranslator.translate("겪다 넋 얹다 벌레 옷걸이")
                , "⠈⠱⠁⠁⠊ ⠉⠹⠄ ⠾⠅⠊ ⠘⠞⠐⠝ ⠥⠄⠈⠞⠕");
        System.out.println(BrailleTranslator.translate("까치 힘껏 갓"));
        Assertions.assertEquals(BrailleTranslator.translate("까치 힘껏 갓")
                , "⠠⠫⠰⠕ ⠚⠕⠢⠠⠸⠎ ⠫⠄");
        System.out.println(BrailleTranslator.translate("팠다"));
        Assertions.assertEquals(BrailleTranslator.translate("팠다")
                , "⠙⠣⠌⠊");
        System.out.println(BrailleTranslator.translate("그리고서 그리하여도 그러나요"));
        Assertions.assertEquals(BrailleTranslator.translate("그리고서 그리하여도 그러나요")
                , "⠁⠥⠠⠎ ⠁⠱⠊⠥ ⠁⠉⠬");
        System.out.println(BrailleTranslator.translate("아예 야애 파워앰프 수액"));
        Assertions.assertEquals(BrailleTranslator.translate("아예 야애 파워앰프 수액")
                , "⠣⠤⠌ ⠜⠤⠗ ⠙⠣⠏⠤⠗⠢⠙⠪ ⠠⠍⠤⠗⠁");
        System.out.println(BrailleTranslator.translate("그녀는 Los Angeles의 한인 타운에 살고 있다"));
        Assertions.assertEquals(BrailleTranslator.translate("아예 야애 파워앰프 수액")
                , "⠣⠤⠌ ⠜⠤⠗ ⠙⠣⠏⠤⠗⠢⠙⠪ ⠠⠍⠤⠗⠁");
    }


    @Test
    void translateE() {
        System.out.println(BrailleTranslator.translate("그녀는 Los Angeles의 한인 타운에 살고 있다"));
        Assertions.assertEquals(BrailleTranslator.translate("그녀는 Los Angeles의 한인 타운에 살고 있다")
                , "⠈⠪⠉⠱⠉⠵ ⠴⠠⠇⠕⠎ ⠠⠁⠝⠛⠑⠇⠑⠎⠲⠺ ⠚⠒⠟ ⠓⠣⠛⠝ ⠇⠂⠈⠥ ⠕⠌⠊");
        System.out.println(BrailleTranslator.translate("New York"));
        Assertions.assertEquals(BrailleTranslator.translate("New York")
                , "⠴⠠⠝⠑⠺ ⠠⠽⠕⠗⠅⠲");
        System.out.println(BrailleTranslator.translate("NEW YORK"));
        Assertions.assertEquals(BrailleTranslator.translate("NEW YORK")
                , "⠴⠠⠠⠝⠑⠺ ⠠⠠⠽⠕⠗⠅⠲");
        System.out.println(BrailleTranslator.translate("iOS"));
        Assertions.assertEquals(BrailleTranslator.translate("iOS")
                , "⠴⠊⠠⠠⠕⠎⠲");
        System.out.println(BrailleTranslator.translate("마WELCOME TO KOREA마"));
        Assertions.assertEquals(BrailleTranslator.translate("WELCOME TO KOREA")
                , "⠴⠠⠠⠠⠺⠑⠇⠉⠕⠍⠑ ⠞⠕ ⠅⠕⠗⠑⠁⠠⠄⠲");
        Assertions.assertEquals(BrailleTranslator.translate("마WELCOME TO KOREA마")
                , "⠑⠴⠠⠠⠺⠑⠇⠉⠕⠍⠑ ⠠⠠⠞⠕ ⠠⠠⠅⠕⠗⠑⠁⠲⠑");
        System.out.println(BrailleTranslator.translate("(asd)"));
//        System.out.println(BrailleTranslator.translate("Table of Contents"));
    }


    @Test
    void translateN() {
        System.out.println(BrailleTranslator.translate("8꾸러미"));
        Assertions.assertEquals(BrailleTranslator.translate("8꾸러미")
                , "⠼⠓⠠⠈⠍⠐⠎⠑⠕");
        System.out.println(BrailleTranslator.translate("375"));
        Assertions.assertEquals(BrailleTranslator.translate("375")
                , "⠼⠉⠛⠑");
        System.out.println(BrailleTranslator.translate("5,700,000원"));
        Assertions.assertEquals(BrailleTranslator.translate("5,700,000원")
                , "⠼⠑⠂⠛⠚⠚⠂⠚⠚⠚⠏⠒");
        System.out.println(BrailleTranslator.translate("8 상자"));
        Assertions.assertEquals(BrailleTranslator.translate("8 상자")
                , "⠼⠓ ⠇⠶⠨");
        System.out.println(BrailleTranslator.translate("4칸 6평 5운6기"));
        Assertions.assertEquals(BrailleTranslator.translate("4칸 6평 5운6기")
                , "⠼⠙ ⠋⠒ ⠼⠋ ⠙⠻ ⠼⠑ ⠛⠼⠋⠈⠕");
    }

    @Test
    void translateS() {
        String translate = BrailleTranslator.translate("젊은이는 나라의 기둥입니다. ");
        BRFParser brfParser = new BRFParser();
        String x = brfParser.translateBraille2BRF(translate);
        System.out.println(translate);
        Assertions.assertEquals(x, ".t5zocz c\"<w @oim7obcoi4");

        translate = BrailleTranslator.translate("문방사우: 종이, 붓, 먹, 벼루");
        x = brfParser.translateBraille2BRF(translate);
        System.out.println(translate);
        Assertions.assertEquals(x, "eg^7lm\"1 .=o\" ^m'\" e?\"`^:\"m".replaceAll("`", " "));

        translate = BrailleTranslator.translate("“어디 나하고 한번…….” 하고 민수가 나섰다. ");
        x = brfParser.translateBraille2BRF(translate);
        System.out.println(BrailleTranslator.translate("“어디 나하고 한번…….” 하고 민수가 나섰다. "));
        Assertions.assertEquals(x, "8sio`cj@u`j3^),,,,,,40`j@u`eq,m$`c,s/i4".replaceAll("`", " "));

        translate = BrailleTranslator.translate("니체(독일의 철학자)의 말을 빌리면 다음과 같다.");
        x = brfParser.translateBraille2BRF(translate);
        System.out.println(translate);
        Assertions.assertEquals(x, "co;n8'ixo1w`;tja.,0w`e1!`^o1\"oe*`i<{5@v`$8i4".replaceAll("`", " "));

        translate = BrailleTranslator.translate("어린이날이 새로 제정되었을 당시에는 어린이들에게 경어를 쓰라고 하였다.[윤석중 전집(1988), 70쪽 참조]");
        x = brfParser.translateBraille2BRF(translate);
        System.out.println(translate);
        Assertions.assertEquals(x, "s\"qoc1o`,r\"u`.n.}iys/!`i7,oncz s\"qoi!n@n`@}s\"!`,,{\"<@u`j<:/i482%3,?.m7`.).ob8'#aihh,0\"`#gj,.x ;<5.u;0".replaceAll("`", " "));
    }
}