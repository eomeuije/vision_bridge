package org.vision.bridge.service.translation;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.vision.bridge.service.brf.BRFParser;

import static org.junit.jupiter.api.Assertions.*;

class Translator2TextTest {

    @Test
    void translate() {
        Translator2Text t = new Translator2Text();
        System.out.println(t.translate("⠙⠍⠢⠇⠁⠄ ⠣⠒⠅⠊ ⠣⠒⠴⠊ ⠕⠂⠁⠊ ⠥⠂⠢⠈⠕⠊"));
        Assertions.assertEquals(t.translate("⠙⠍⠢⠇⠁⠄ ⠣⠒⠅⠊ ⠣⠒⠴⠊ ⠕⠂⠁⠊ ⠥⠂⠢⠈⠕⠊").trim()
                , "품삯 앉다 않다 읽다 옮기다");

        System.out.println(t.translate("⠉⠣⠕ ⠊⠣⠪⠢ ⠑⠣⠍⠠⠪"));
        Assertions.assertEquals(t.translate("⠉⠣⠕ ⠊⠣⠪⠢ ⠑⠣⠍⠠⠪").trim()
                , "나이 다음 마우스");

        System.out.println(t.translate("⠠⠊⠶⠮ ⠙⠣⠌⠊"));
        Assertions.assertEquals(t.translate("⠠⠊⠶⠮ ⠙⠣⠌⠊").trim()
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
                "볶다 논두렁 용돈 \n" +
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
            String translate = t.translate(braille);
            System.out.println(translate);
            Assertions.assertEquals(translate.trim()
                    , validSplit[i].trim());
        }
    }

    @Test
    void translate2() {
        Translator2Text t = new Translator2Text();
        BRFParser p = new BRFParser();
        System.out.println(t.translate(p.translateBRF2Braille(",$7;m7,$7;m7")));
        Assertions.assertEquals(t.translate(p.translateBRF2Braille(",$7;m7,$7;m7")).trim()
                , "깡충깡충");
        System.out.println(t.translate(p.translateBRF2Braille(",l7im7o")));
        Assertions.assertEquals(t.translate(p.translateBRF2Braille(",l7im7o")).trim()
                , "쌍둥이");
        System.out.println(t.translate(p.translateBRF2Braille("e<{5,_s")));
        Assertions.assertEquals(t.translate(p.translateBRF2Braille("e<{5,_s")).trim()
                , "마음껏");
        System.out.println(t.translate(p.translateBRF2Braille("~&! ,@s/i4")));
        Assertions.assertEquals(t.translate(p.translateBRF2Braille("~&! ,@s/i4")).trim()
                , "불을 껐다.");
        System.out.println(t.translate(p.translateBRF2Braille("<crcz .u+7jo ac i3juj@n e1jr/i4")));
        Assertions.assertEquals(t.translate(p.translateBRF2Braille("<crcz .u+7jo ac i3juj@n e1jr/i4")).trim()
                , "아내는 조용히 그러나 단호하게 말했다.");
        System.out.println(t.translate("⠁⠥⠠⠎ ⠁⠱⠊⠥ ⠁⠉⠬"));
        Assertions.assertEquals(t.translate("⠁⠥⠠⠎ ⠁⠱⠊⠥ ⠁⠉⠬").trim()
                , "그리고서 그리하여도 그러나요");
        System.out.println(t.translate(p.translateBRF2Braille(",.o7@{\"o@u")));
        Assertions.assertEquals(t.translate(p.translateBRF2Braille("e<{5,_s")).trim()
                , "마음껏");
    }
    @Test
    void translateE() {
        Translator2Text t = new Translator2Text();
        BRFParser p = new BRFParser();
        System.out.println(t.translate(p.translateBRF2Braille("0,,,welcome to korea,'")));
        System.out.println(t.translate(p.translateBRF2Braille("d<p-r5d{")));
        Assertions.assertEquals(t.translate(p.translateBRF2Braille("d<p-r5d{")).trim()
                , "파워앰프");
        System.out.println(t.translate(p.translateBRF2Braille("0i,,os")));
        System.out.println(t.translate(p.translateBRF2Braille("@{cz 0,canada4\"u :jr7! ,isc/i4")));
        Assertions.assertEquals(t.translate(p.translateBRF2Braille("0,,,welcome to korea,'")).trim()
                , "WELCOME TO KOREA");
        Assertions.assertEquals(t.translate(p.translateBRF2Braille("0i,,os")).trim()
                , "iOS");
        Assertions.assertEquals(t.translate(p.translateBRF2Braille("@{cz 0,canada4\"u :jr7! ,isc/i4")).trim()
                , "그는 Canada로 여행을 떠났다.");
        System.out.println(t.translate(p.translateBRF2Braille("0,roma4`_1_1\"ue".replaceAll("`", " "))));
        Assertions.assertEquals(t.translate(p.translateBRF2Braille("0,roma4`_1_1\"ue".replaceAll("`", " "))).trim()
                , "Roma ㄹㄹ로마");
        System.out.println(t.translate(p.translateBRF2Braille("=a4 %<@o")));
        Assertions.assertEquals(t.translate(p.translateBRF2Braille("=a4 %<@o")).trim()
                , "ㄱ. 유아기");
        System.out.println(t.translate(p.translateBRF2Braille("0study4cz`_',isioo\"u`0ice4cz`<o_'v`$8o`~1{5j3i4".replaceAll("`", " "))));
        Assertions.assertEquals(t.translate(p.translateBRF2Braille("0study4cz`_',isioo\"u`0ice4cz`<o_'v`$8o`~1{5j3i4".replaceAll("`", " "))).trim()
                , "study는 ㅅ떠디이로 ice는 아이ㅅ와 같이 발음한다.");
    }

    @Test
    void translateN() {
        Translator2Text t = new Translator2Text();
        BRFParser p = new BRFParser();
        System.out.println(t.translate(p.translateBRF2Braille("#i1cge e}")));
        Assertions.assertEquals(t.translate(p.translateBRF2Braille("#i1cge e}")).trim()
                , "9,375 명");
        System.out.println(t.translate(p.translateBRF2Braille("#a1jjjp3_/@r")));
        Assertions.assertEquals(t.translate(p.translateBRF2Braille("#a1jjjp3_/@r")).trim()
                , "1,000원/개");
        System.out.println(t.translate(p.translateBRF2Braille(";<aj3 l\"<5 _/ <aj3 l\"<5")));
        Assertions.assertEquals(t.translate(p.translateBRF2Braille(";<aj3 l\"<5 _/ <aj3 l\"<5")).trim()
                , "착한 사람 / 악한 사람");
        System.out.println(t.translate(p.translateBRF2Braille("0,ms4cz eoj(\"2@oj(w @m~|o sb'cz :,]w .(;o7oi4")));
        Assertions.assertEquals(t.translate(p.translateBRF2Braille("0,ms4cz eoj(\"2@oj(w @m~|o sb'cz :,]w .(;o7oi4")).trim()
                , "Ms는 미혼·기혼의 구별이 없는 여성의 존칭이다.");
        System.out.println(t.translate(p.translateBRF2Braille("0,summary4@9*,{b eg.n")));
        Assertions.assertEquals(t.translate(p.translateBRF2Braille("0,summary4@9*,{b eg.n")).trim()
                , "Summary∼연습 문제");
        System.out.println(t.translate(p.translateBRF2Braille(";8jgeq.][502z #aiig c*n %cn,[ fu ,n@/ @o\"x %l3[\"u .o.]iys/i4")));
        Assertions.assertEquals(t.translate(p.translateBRF2Braille("0,summary4@9*,{b eg.n")).trim()
                , "Summary∼연습 문제");
    }


    @Test
    void translateS() {
        Translator2Text t = new Translator2Text();
        BRFParser p = new BRFParser();
        System.out.println(t.translate(p.translateBRF2Braille(",o@/\"".replaceAll("`", " "))));
        System.out.println(t.translate("⠣⠒⠴⠊"));
        System.out.println(t.translate(p.translateBRF2Braille(";8jgeq.][502z #aiig c*n %cn,[fu ,n@/ @o\"x %l3[\"u .o.]iys/i4")));
        Assertions.assertEquals(t.translate(p.translateBRF2Braille(";8jgeq.][502z #aiig c*n %cn,[fu ,n@/ @o\"x %l3[\"u .o.]iys/i4")).trim()
                , "『훈민정음』은 1997 년에 유네스코 세계 기록 유산으로 지정되었다.");
        System.out.println(t.translate(p.translateBRF2Braille("0,,,welcome to korea,'")));
        Assertions.assertEquals(t.translate(p.translateBRF2Braille("0,,,welcome to korea,'")).trim()
                , "WELCOME TO KOREA");
        System.out.println(t.translate(p.translateBRF2Braille("0,summary4@9*,{b eg.n")));
        Assertions.assertEquals(t.translate(p.translateBRF2Braille("0,summary4@9*,{b eg.n")).trim()
                , "Summary∼연습 문제");
        System.out.println(t.translate(p.translateBRF2Braille("ccz`ju.mesco\"!`imr.os/i4`imhm5j3`.o$b\"`,o@/\"`,(,m@)\"`444`o/!`_sz`.yi`o/s/i4".replaceAll("`", " "))));
        Assertions.assertEquals(t.translate(p.translateBRF2Braille("ccz`ju.mesco\"!`imr.os/i4`imhm5j3`.o$b\"`,o@/\"`,(,m@)\"`444`o/!`_sz`.yi`o/s/i4".replaceAll("`", " "))).trim()
                , "나는 호주머니를 뒤지었다. 두툼한 지갑, 시계, 손수건, ... 있을 것은 죄다 있었다.");
        System.out.println(t.translate(p.translateBRF2Braille("ccz`,8o1o`I`h!\":/c`^u@g40'`j@u,r7$aj<:/i4".replaceAll("`", " "))));
        Assertions.assertEquals(t.translate(p.translateBRF2Braille("ccz`,8o1o`I`h!\":/c`^u@g40'`j@u,r7$aj<:/i4".replaceAll("`", " "))).trim()
                , "나는 '일이 다 틀렸나 보군.' 하고생각하였다.");
        System.out.println(t.translate(p.translateBRF2Braille("s\"qoc1o`,r\"u`.n.]iys/!`i7,onczs\"qoi!n@n`@]s\"!`,,[\"<@u`j<:/i4``82%3,?.m7`.).ob8'#aihh,0\"`#gj,.x;<5.u;0".replaceAll("`", " "))));
        Assertions.assertEquals(t.translate(p.translateBRF2Braille("s\"qoc1o`,r\"u`.n.]iys/!`i7,onczs\"qoi!n@n`@]s\"!`,,[\"<@u`j<:/i4``82%3,?.m7`.).ob8'#aihh,0\"`#gj,.x;<5.u;0".replaceAll("`", " "))).trim()
                , "어린이날이 새로 제정되었을 당시에는어린이들에게 경어를 쓰라고 하였다.  [윤석중 전집(1988), 70쪽참조]");
        System.out.println(t.translate(p.translateBRF2Braille("@{`e1!`i{9cz`,g$3`_xl\"<3`e1o``ex@mes7,$.o`;oeo1s/i4".replaceAll("`", " "))));
        Assertions.assertEquals(t.translate(p.translateBRF2Braille("@{`e1!`i{9cz`,g$3`_xl\"<3`e1o``ex@mes7,$.o`;oeo1s/i4".replaceAll("`", " "))).trim()
                , "그 말을 듣는 순간 ×란 말이  목구멍까지 치밀었다.");
    }
    @Test
    void translateS1() {
        Translator2Text t = new Translator2Text();
        BRFParser p = new BRFParser();
        System.out.println(t.translate(
                "⠠⠊⠐⠥"
                ));
    }
}