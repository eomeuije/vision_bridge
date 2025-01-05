package org.vision.bridge.service.translation;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.vision.bridge.service.brf.BRFParser;

import static org.junit.jupiter.api.Assertions.*;

class Translator2TextTest {

    @Test
    void translate() {

        System.out.println(Translator2Text.translate("⠙⠍⠢⠇⠁⠄ ⠣⠒⠅⠊ ⠣⠒⠴⠊ ⠕⠂⠁⠊ ⠥⠂⠢⠈⠕⠊"));
        Assertions.assertEquals(Translator2Text.translate("⠙⠍⠢⠇⠁⠄ ⠣⠒⠅⠊ ⠣⠒⠴⠊ ⠕⠂⠁⠊ ⠥⠂⠢⠈⠕⠊").trim()
                , "품삯 앉다 않다 읽다 옮기다");

        System.out.println(Translator2Text.translate("⠉⠣⠕ ⠊⠣⠪⠢ ⠑⠣⠍⠠⠪"));
        Assertions.assertEquals(Translator2Text.translate("⠉⠣⠕ ⠊⠣⠪⠢ ⠑⠣⠍⠠⠪").trim()
                , "나이 다음 마우스");

        System.out.println(Translator2Text.translate("⠠⠊⠶⠮ ⠙⠣⠌⠊"));
        Assertions.assertEquals(Translator2Text.translate("⠠⠊⠶⠮ ⠙⠣⠌⠊").trim()
                , "땅을 팠다");

        String test = "i?e7 @o.? ,@?ai\n" +
                "c?' @).).o @r;).t\n" +
                ")ki ~t\"n u'@to\n" +
                "tai .t5i ctbi\n" +
                "~*jv ,m\"* ~|.\"o\n" +
                "j)j| |bi d}jv\n" +
                "<3c} ~xi?~7 $@x\n" +
                "~xai c(im\"s7 +7i(\n" +
                "i=@{\"<eo ha@m@= ,gim~m\n" +
                ",m7\"/eg ~&@u@o o1;&\n" +
                "^&ai @&5i j&8i\n" +
                "@z\"u ejz ,@z0i\n" +
                "@!,,o @m,! @!ai\n" +
                "!4i ,@!0i .q,o1\n" +
                "s\"qo j@s/i";
        String valid =  "덕망 기적 꺾다 \n" +
                "넋 건전지 개천절 \n" +
                "얹다 벌레 옷걸이 \n" +
                "얽다 젊다 넓다 \n" +
                "변화 수련 별자리 \n" +
                "헌혈 엷다 평화 \n" +
                "안녕 복덕방 가곡 \n" +
                "복다 논두렁 용돈 \n" +
                "동그라미 탁구공 순두부 \n" +
                "숭례문 불고기 일출 \n" +
                "붉다 굶다 훑다 \n" +
                "근로 마흔 끊다 \n" +
                "글씨 구슬 긁다 \n" +
                "읊다 끓다 진실 \n" +
                "어린이 하겄다 ";

        String[] split = test.split("\n");
        String[] validSplit = valid.split("\n");
        BRFParser p = new BRFParser();
        for (int i = 0; i < split.length; i++) {
            String braille = p.translateBRF2Braille(split[i]);
            String translate = Translator2Text.translate(braille);
            System.out.println(translate);
            Assertions.assertEquals(translate.trim()
                    , validSplit[i].trim());
        }
    }

    @Test
    void translate2() {
        BRFParser p = new BRFParser();
        System.out.println(Translator2Text.translate("⠚⠂⠥⠗⠺⠫⠁⠂⠂⠇⠁⠁⠉⠑⠫⠝⠊⠁⠈⠁⠼'"));
        System.out.println(Translator2Text.translate(p.translateBRF2Braille(",$7;m7,$7;m7")));
        Assertions.assertEquals(Translator2Text.translate(p.translateBRF2Braille(",$7;m7,$7;m7")).trim()
                , "깡충깡충");
    }
}