package org.vision.bridge.service.translation;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.vision.bridge.service.brf.BRFParser;

class BrailleTranslatorTest {

    @Test
    void translate() {
        System.out.println(Translator2Braille.translate("앉아서"));
        System.out.println(Translator2Braille.translate("꺾다"));
        System.out.println(Translator2Braille.translate("품삯 앉다 않다 읽다 옮기다"));
        Assertions.assertEquals(Translator2Braille.translate("품삯 앉다 않다 읽다 옮기다")
                , "⠙⠍⠢⠇⠁⠄ ⠣⠒⠅⠊ ⠣⠒⠴⠊ ⠕⠂⠁⠊ ⠥⠂⠢⠈⠕⠊");
        System.out.println(Translator2Braille.translate("나이 다음 마우스"));
        Assertions.assertEquals(Translator2Braille.translate("나이 다음 마우스")
                , "⠉⠣⠕ ⠊⠣⠪⠢ ⠑⠣⠍⠠⠪");
        System.out.println(Translator2Braille.translate("땅을 팠다"));
        Assertions.assertEquals(Translator2Braille.translate("땅을 팠다")
                , "⠠⠊⠶⠮ ⠙⠣⠌⠊");
        System.out.println(Translator2Braille.translate("삼각형 ㄱㄴㄷ"));
        Assertions.assertEquals(Translator2Braille.translate("삼각형 ㄱㄴㄷ")
                , "⠇⠢⠫⠁⠚⠻ ⠿⠁⠿⠒⠿⠔");
        System.out.println(Translator2Braille.translate("ㄹㄹ로마"));
        Assertions.assertEquals(Translator2Braille.translate("ㄹㄹ로마")
                , "⠸⠂⠸⠂⠐⠥⠑");
        System.out.println(Translator2Braille.translate("겪다 넋 얹다 벌레 옷걸이"));
        Assertions.assertEquals(Translator2Braille.translate("겪다 넋 얹다 벌레 옷걸이")
                , "⠈⠱⠁⠁⠊ ⠉⠹⠄ ⠾⠅⠊ ⠘⠞⠐⠝ ⠥⠄⠈⠞⠕");
        System.out.println(Translator2Braille.translate("까치 힘껏 갓"));
        Assertions.assertEquals(Translator2Braille.translate("까치 힘껏 갓")
                , "⠠⠫⠰⠕ ⠚⠕⠢⠠⠸⠎ ⠫⠄");
        System.out.println(Translator2Braille.translate("팠다"));
        Assertions.assertEquals(Translator2Braille.translate("팠다")
                , "⠙⠣⠌⠊");
        System.out.println(Translator2Braille.translate("그리고서 그리하여도 그러나요"));
        Assertions.assertEquals(Translator2Braille.translate("그리고서 그리하여도 그러나요")
                , "⠁⠥⠠⠎ ⠁⠱⠊⠥ ⠁⠉⠬");
        System.out.println(Translator2Braille.translate("아예 야애 파워앰프 수액"));
        Assertions.assertEquals(Translator2Braille.translate("아예 야애 파워앰프 수액")
                , "⠣⠤⠌ ⠜⠤⠗ ⠙⠣⠏⠤⠗⠢⠙⠪ ⠠⠍⠤⠗⠁");
        System.out.println(Translator2Braille.translate("그녀는 Los Angeles의 한인 타운에 살고 있다"));
        Assertions.assertEquals(Translator2Braille.translate("아예 야애 파워앰프 수액")
                , "⠣⠤⠌ ⠜⠤⠗ ⠙⠣⠏⠤⠗⠢⠙⠪ ⠠⠍⠤⠗⠁");
    }


    @Test
    void translateE() {
        System.out.println(Translator2Braille.translate("그녀는 Los Angeles의 한인 타운에 살고 있다"));
        Assertions.assertEquals(Translator2Braille.translate("그녀는 Los Angeles의 한인 타운에 살고 있다")
                , "⠈⠪⠉⠱⠉⠵ ⠴⠠⠇⠕⠎ ⠠⠁⠝⠛⠑⠇⠑⠎⠲⠺ ⠚⠒⠟ ⠓⠣⠛⠝ ⠇⠂⠈⠥ ⠕⠌⠊");
        System.out.println(Translator2Braille.translate("New York"));
        Assertions.assertEquals(Translator2Braille.translate("New York")
                , "⠴⠠⠝⠑⠺ ⠠⠽⠕⠗⠅⠲");
        System.out.println(Translator2Braille.translate("NEW YORK"));
        Assertions.assertEquals(Translator2Braille.translate("NEW YORK")
                , "⠴⠠⠠⠝⠑⠺ ⠠⠠⠽⠕⠗⠅⠲");
        System.out.println(Translator2Braille.translate("iOS"));
        Assertions.assertEquals(Translator2Braille.translate("iOS")
                , "⠴⠊⠠⠠⠕⠎⠲");
        System.out.println(Translator2Braille.translate("마WELCOME TO KOREA마"));
        Assertions.assertEquals(Translator2Braille.translate("WELCOME TO KOREA")
                , "⠴⠠⠠⠠⠺⠑⠇⠉⠕⠍⠑ ⠞⠕ ⠅⠕⠗⠑⠁⠠⠄⠲");
        Assertions.assertEquals(Translator2Braille.translate("마WELCOME TO KOREA마")
                , "⠑⠴⠠⠠⠺⠑⠇⠉⠕⠍⠑ ⠠⠠⠞⠕ ⠠⠠⠅⠕⠗⠑⠁⠲⠑");
        System.out.println(Translator2Braille.translate("(asd)"));
//        System.out.println(BrailleTranslator.translate("Table of Contents"));
    }


    @Test
    void translateN() {
        System.out.println(Translator2Braille.translate("8꾸러미"));
        Assertions.assertEquals(Translator2Braille.translate("8꾸러미")
                , "⠼⠓⠠⠈⠍⠐⠎⠑⠕");
        System.out.println(Translator2Braille.translate("375"));
        Assertions.assertEquals(Translator2Braille.translate("375")
                , "⠼⠉⠛⠑");
        System.out.println(Translator2Braille.translate("5,700,000원"));
        Assertions.assertEquals(Translator2Braille.translate("5,700,000원")
                , "⠼⠑⠂⠛⠚⠚⠂⠚⠚⠚⠏⠒");
        System.out.println(Translator2Braille.translate("8 상자"));
        Assertions.assertEquals(Translator2Braille.translate("8 상자")
                , "⠼⠓ ⠇⠶⠨");
        System.out.println(Translator2Braille.translate("4칸 6평 5운6기"));
        Assertions.assertEquals(Translator2Braille.translate("4칸 6평 5운6기")
                , "⠼⠙ ⠋⠒ ⠼⠋ ⠙⠻ ⠼⠑ ⠛⠼⠋⠈⠕");
    }

    @Test
    void translateS() {
        String translate = Translator2Braille.translate("젊은이는 나라의 기둥입니다. ");
        BRFParser brfParser = new BRFParser();
        String x = brfParser.translateBraille2BRF(translate);
        System.out.println(translate);
        Assertions.assertEquals(x, ".t5zocz c\"<w @oim7obcoi4");

        translate = Translator2Braille.translate("문방사우: 종이, 붓, 먹, 벼루");
        x = brfParser.translateBraille2BRF(translate);
        System.out.println(translate);
        Assertions.assertEquals(x, "eg^7lm\"1 .=o\" ^m'\" e?\"`^:\"m".replaceAll("`", " "));

        translate = Translator2Braille.translate("“어디 나하고 한번…….” 하고 민수가 나섰다. ");
        x = brfParser.translateBraille2BRF(translate);
        System.out.println(Translator2Braille.translate("“어디 나하고 한번…….” 하고 민수가 나섰다. "));
        Assertions.assertEquals(x, "8sio`cj@u`j3^),,,,,,40`j@u`eq,m$`c,s/i4".replaceAll("`", " "));

        translate = Translator2Braille.translate("니체(독일의 철학자)의 말을 빌리면 다음과 같다.");
        x = brfParser.translateBraille2BRF(translate);
        System.out.println(translate);
        Assertions.assertEquals(x, "co;n8'ixo1w`;tja.,0w`e1!`^o1\"oe*`i<{5@v`$8i4".replaceAll("`", " "));

        translate = Translator2Braille.translate("어린이날이 새로 제정되었을 당시에는 어린이들에게 경어를 쓰라고 하였다.[윤석중 전집(1988), 70쪽 참조]");
        x = brfParser.translateBraille2BRF(translate);
        System.out.println(translate);
        Assertions.assertEquals(x, "s\"qoc1o`,r\"u`.n.}iys/!`i7,oncz s\"qoi!n@n`@}s\"!`,,{\"<@u`j<:/i482%3,?.m7`.).ob8'#aihh,0\"`#gj,.x ;<5.u;0".replaceAll("`", " "));

    }
}