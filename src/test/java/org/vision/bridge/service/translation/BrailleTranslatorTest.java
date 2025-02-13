package org.vision.bridge.service.translation;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.vision.bridge.service.brf.BRFParser;

class BrailleTranslatorTest {



    // 한국어, 숫자, 영어(약어 제외한 알파벳만), 특수문자 일부
    // 지원하는 특수문자: . , " ' + - * ÷ = > < ? ! · : / ( ) { } [ ]
    // 따옴표, 쌍따옴표는 키보드에서 입력 가능한 ", ' 만을 지원한다. 그 외의 기울어진 따옴표 등은 지원하지 않으며, 입력으로 들어올경우 ' "로 간주한다.

    @Test
    void translateTEST() {
        Translator2Braille t = new Translator2Braille();
        BRFParser brfParser = new BRFParser();
        String translate, x;


        // 제1항
        t("거리", "@s\"o");
        t("너비", "cs~o");
        t("두더지", "imis.o");
        t("리코더", "\"ofuis");
        t("미소", "eo,u");
        t("보리", "~u\"o");
        t("셔츠", ",:;{");
        t("저고리", ".s@u\"o");
        t("추수", ";m,m");
        t("커피", "fsdo");
        t("터무니", "hsemco");
        t("피리", "do\"o");
        t("호수", "ju,m");

        // 제1항 다만1
        t("아버지", "<~s.o");
        t("야구", ">@m");
        t("어머니", "sesco");
        t("여우", ":m");
        t("오이", "uo");
        t("요리", "+\"o");
        t("우유", "m%");
        t("유리", "%\"o");
        t("으스스", "{,{,{");
        t("이모", "oeu");
        t("피아노", "do<cu");
        t("도우미", "iumeo");
        t("러시아", "\"s,o<");


        // 제2항 다만2
        t("꾸러미", ",@m\"seo");
        t("두꺼비", "im,@s~o");
        t("뚜껑", ",im,@s7");
        t("허리띠", "js\"o,io");
        t("뻐꾸기", ",~s,@m@o");
        t("고삐", "@u,~o");
        t("쓰기", ",,{@o");
        t("아저씨", "<.s,,o");
        t("쭈르르", ",.m\"{\"{");
        t("버찌", "~s,.o");


        // 제3항
        t("국보", "@ma~u");
        t("윤리", "%3\"o");
        t("싣고", ",o9@u");
        t("놀이", "cu1o");
        t("솜씨", ",u5,,o");
        t("넙치", "csb;o");
        t("놋그릇", "cu'@{\"{'");
        t("향기", "j>7@o");
        t("엊저녁", "sk.sc:a");
        t("윷놀이", "%2cu1o");
        t("부엌", "~ms6");
        t("겉보리", "@s8~u\"o");
        t("앞집", "<4.ob");
        t("히읗", "jo{0");

        // 제4항
        t("겪다", "@:aai");
        t("묶음", "emaa[5");
        t("있다", "o/i");
        t("보았다", "^u</i");

        // 제5항
        t("품삯", "dm5la'");
        t("앉다", "<3ki");
        t("않다", "<30i");
        t("읽다", "o1ai");
        t("옮기다", "u15@oi");
        t("얇다", ">1bi");
        t("외곬", "y@u1'");
        t("핥다", "j18i");
        t("옳다", "u10i");
        t("없다", "sb'i");

        // 제6항
        t("아리랑", "<\"o\"<7");
        t("고양이", "@u>7o");
        t("엄지", "s5.o");
        t("무역", "em:a");
        t("호랑이", "ju\"<7o");
        t("무용", "em+7");
        t("국수", "@ma,m");
        t("법률", "~sb\"%1");
        t("특기", "h{a@o");
        t("코끼리", "fu,@o\"o");

        // 제7항
        t("매미", "ereo");
        t("얘기", ">r@o");
        t("헤엄", "jns5");
        t("지혜", ".oj/");
        t("광주리", "@v7.m\"o");
        t("쾌활", "fvrjv1");
        t("피뢰침", "do\"y;o5");
        t("권리", "@p3\"o");
        t("우렁쉥이", "m\"s7,pr7o");
        t("쉼터", ",mr5hs");
        t("무늬", "emcw");

        // 제8항
        t("파열음에는 ㄱ, ㄷ, ㅂ 등이 있다.", "d<|{5ncz`=a\"`=9\"`=b`i{7o`o/i4");
        t("삼각형 ㄱㄴㄷ", "l5$aj}`=a=3=9");
        t("외래어의 받침을 표기할 때에는 'ㄱ, ㄴ, ㄹ, ㅁ, ㅂ, ㅅ, ㅇ'만을 사용한다.", "y\"rsw`~9;o5!`d+@oj1`,irncz`,8=a\"`=3\"`=1\"`=5\"`=b\"`='\"`=7,8e3!`l+7j3i4");
        t("'계, 례, 몌, 폐, 혜'의 'ㅖ'는 'ㅔ'로 소리 나는 경우가 있더라도 'ㅖ'로 적는다.", ",8@/\"`\"/\"`e/\"`d/\"`j/,8w`,8=/,8cz`,8=n,8\"u`,u\"o`ccz`@}m$`o/is\"<iu`,8=/,8\"u`.?czi4");

        // 제8항 붙임
//제10항 위배        translate = t.translate("낫 놓고 ㄱ자도 모른다.");
//        Assertions.assertEquals(translate, brfParser.translateBRF2Braille("c'`cu0@u`=a.iu`eu\"zi4".replaceAll("`", " ")));
//        translate = t.translate("ㅂ은 여섯 번째 자음이다.");
//        Assertions.assertEquals(translate, brfParser.translateBRF2Braille("=bz`:,s'`~),.r`.<{5oi4".replaceAll("`", " ")));
//        translate = t.translate("컴퓨터 자판의 모음 배열에서 ㅗ, ㅓ, ㅏ, ㅣ는 나란히 있다.");
//        Assertions.assertEquals(translate, brfParser.translateBRF2Braille("fs5d%hs`.d3w`eu{5`~r|n,s`=u\"`=s\"`=<\"`=ocz`c\"<3jo`o/i4".replaceAll("`", " ")));
        t("업무상 횡령 혐의로 ㄱ 대학교 ㄴ 교수가 구속되었다.", "sbeml7`jy7\"}`j:5w\"u`=a`irja@+ =3`@+,m$`@m,xiys/i4");

        // 제9항
        t("ㄱ. 유아기", "=a4`%<@o");
        t("ㄴ. 아동기", "=34`<i=@o");
        t("ㄷ. 청년기", "=94`;]c*@o");
        t("ㄹ. 장년기", "=14`.7c*@o");
        t("ㅁ. 노년기", "=54`cuc*@o");

        // 제10항
        t("Roma [ㄹㄹ로마]", "0,roma4`82_1_1\"ue;0");
        //약자 제외
        t("[까ㄹㄹ로]", "82,$_1_1\"u;0");
        t("요즘 교재에서는 발음을 [봉주ㄹ흐]라고 표기한다.", "+.{5`@+.rn,scz`~1{5! 82~=.m_1j{;0\"<@u`d+@oj3i4");
        t("[ㅅ떠디이]로, ice는 [아이ㅅ]와 같이 발음한다.", "82_',isioo;0\"u\"`0ice4cz`82<o_';0v`$8o`~1{5j3i4");

        // 제11항
        t("아예", "<-/");
        t("도예", "iu-/");
        t("뭐예요", "ep-/+");
        t("서예", ",s-/");

        // 제12항
        t("야애", ">-r");
        t("파워앰프", "d<p-r5d{");
        t("소화액", ",ujv-ra");
        t("수액", ",m-ra");

        // 제13항
        t("가지", "$.o");
        t("나비", "c~o");
        t("다리미", "i\"oeo");
        t("라디오", "\"<iou");
        t("고구마", "@u@me");
        t("바느질", "~c{.o1");
        t("사위", "lmr");
        t("아궁이", "<@m7o");
        t("도자기", "iu.@o");
        t("기차", "@o;<");
        t("카메라", "fen\"<");
        t("실타래", ",o1h\"r");
        t("파도", "diu");
        t("하루", "j\"m");

        // 제13항 붙임
        t("강산", "$7l3");
        t("낮잠", "ck.5");
        t("단란", "i3\"<3");
        t("만찬", "e3;<3");
        t("안방", "<3~7");
        t("바캉스", "~f7,{");
        t("갈비탕", "$1~oh7");
        t("판소리", "d3,u\"o");
        t("합창", "jb;<7");
        t("까마귀", ",$e@mr");
        t("깜깜하다", ",$5,$5ji");
        t("따님", ",ico5");
        t("딱따구리", ",ia,i@m\"o");
        t("오빠", "u,~");
        t("빵집", ",~7.ob");
        t("싸구려", ",l@m\":");
        t("찹쌀", ";<b,l1");
        t("짜장", ",..7");
        t("짱구", ",.7@m");

        // 제14항
        t("나이", "c<o");
        t("다음", "i<[5");
        t("마우스", "e<m,{");
        t("바위", "^<mr");
        t("자아", ".<<");
        t("카이로", "f<o\"u");
        t("넥타이", "cnah<o");
        t("파이프", "d<od{");
        t("하얀", "j<>3");

        // 제14항 붙임
        t("땅을 팠다.", ",i7!`d</i4");

        // 제15항
        t("억새", "?,r");
        t("추억", ";m?");
        t("언어", ")s");
        t("격언", "@:a)");
        t("얼룩", "t\"ma");
        t("하얼빈", "j<t~q");
        t("연필", "*do1");
        t("자연", ".<*");
        t("열매", "|er");
        t("가열", "$|");
        t("영어", "}s");
        t("환영", "jv3}");
        t("옥수수", "x,m,m");
        t("가옥", "$x");
        t("온도", "(iu");
        t("오리온", "u\"o(");
        t("옹고집", "=@u.ob");
        t("새옹지마", ",r=.oe");
        t("운동장", "gi=.7");
        t("행운", "jr7g");
        t("울타리", "&h\"o");
        t("겨울", "@:&");
        t("은하수", "zj,m");
        t("양은", ">7z");
        t("을지로", "!.o\"u");
        t("가을", "$!");
        t("인내", "qcr");
        t("거인", "@sq");
        t("것이다", "_soi");
        t("이것", "o_s");

        // 제15항 붙임
        t("덕망", "i?e7");
        t("기적", "@o.?");
        t("꺾다", ",@?ai");
        t("넋", "c?'");
        t("건전지", "@).).o");
        t("개천절", "@r;).t");
        t("얹다", ")ki");
        t("벌레", "~t\"n");
        t("옷걸이", "u'@to");
        t("얽다", "tai");
        t("젊다", ".t5i");
        t("넓다", "ctbi");
        t("변화", "~*jv");
        t("수련", ",m\"*");
        t("별자리", "~|.\"o");
        t("헌혈", "j)j|");
        t("엷다", "|bi");
        t("평화", "d}jv");
        t("안녕", "<3c}");
        t("복덕방", "~xi?~7");
        t("가곡", "$@x");
        t("볶다", "~xai");
        t("논두렁", "c(im\"s7");
        t("용돈", "+7i(");
        t("동그라미", "i=@{\"<eo");
        t("탁구공", "ha@m@=");
        t("순두부", ",gim~m");
        t("숭례문", ",m7\"/eg");
        t("불고기", "~&@u@o");
        t("일출", "o1;&");
        t("붉다", "^&ai");
        t("굶다", "@&5i");
        t("훑다", "j&8i");
        t("근로", "@z\"u");
        t("마흔", "ejz");
        t("끊다", ",@z0i");
        t("글씨", "@!,,o");
        t("구슬", "@m,!");
        t("긁다", "@!ai");
        t("읊다", "!4i");
        t("끓다", ",@!0i");
        t("진실", ".q,o1");
        t("어린이", "s\"qo");
        t("하겄다", "j@s/i");

        // 제16항
        t("까치", ",$;o");
        t("깡충깡충", ",$7;m7,$7;m7");
        t("싸리나무", ",l\"ocem");
        t("쌍둥이", ",l7im7o");
        t("힘껏", "jo5,_s");
        t("마음껏", "e<{5,_s");

        // 제16항 붙임
        t("불을 껐다.", "~&!`,@s/i4");

        // 제17항
        t("성가", ",}$");
        t("말썽", "e1,,]");
        t("정성", ".},}");
        t("어정쩡", "s.},.}");
        t("청년", ";}c*");
        // 문서가 약자를 쓰지않음.
//        translate = t.translate("얄라셩");
//        Assertions.assertEquals(translate, brfParser.translateBRF2Braille(">1\"<,:7".replaceAll("`", " ")));

        // 제18항
        t("비가 왔다. 그래서 소풍 계획은 취소되었다.", "~o$ v/i4 as ,udm7 @/jyaz ;mr,uiys/i4");
        t("아내는 조용히 그러나 단호하게 말했다.", "<crcz`.u+7jo`ac`i3juj@n`e1jr/i4");
        t("만약 결과가 그러면 어떻게 할 거니?", "e3>a`@|@v$`a3`s,is0@n`j1`@sco8");
        t("그러므로 오늘 저녁에 와야 한다.", "a5`uc!`.sc:an`v>`j3i4");
        t("내 잘못이 크다. 그런데 누구를 원망하겠나.", "cr`.1eu'o`f{i4`an`cm@m\"!`p3e7j@n/c4");
        t("그림을 그리고 있다. ", "@[\"o5!`au`o/i4");
        t("그리하여 그들은 친구 사이가 되었다.", "a:`@{i!z`;q@m`lo$`iys/i4");

        // 제18항 붙임
        t("그래서인지", "asq.o");
        t("그러면서", "a3,s");
        t("그리하여도", "a:iu");
        t("그러나저러나", "ac.s\"sc");
        t("그런데도", "aniu");
        t("왜 그러나요?", "vr`ac+8");
        t("그림을 그리고서 밥을 먹었다. ", "@[\"o5!`au,s`^b!`e?s/i4");

        // 제18항 다만
        t("오그리고", "u@{\"o@u");
        t("쭈그리고", ",.m@{\"o@u");
        t("우그리고", "m@{\"o@u");
        t("찡그리고", ",.o7@{\"o@u");

        // 영어
        // 제28항
        t("book", "0book4");
        t("happy", "0happy4");
        t("moon", "0moon4");
        t("purple", "0purple4");
        t("tea", "0tea4");
        t("welcome", "0welcome4");

        // 제28항 붙임
        t("New York", "0,new`,york4");
        t("McDonald", "0,mc,donald4");
        t("IoT", "0,io,t4");
        t("NEW YORK", "0,,new`,,york4");
        t("iOS", "0i,,os4");
        t("WELCOME TO KOREA", "0,,,welcome`to`korea,'4");

        // 제29항
        t("그는 Canada로 여행을 떠났다.", "@{cz`0,canada4\"u`:jr7!`,isc/i4");
        t("그녀는 Los Angeles의 한인 타운에 살고 있다.", "@{c:cz`0,los`,angeles4w`j3q`h<gn`l1@u`o/i4");

        // 제40항
        t("10", "#aj");
        t("99", "#ii");
        t("375", "#cge");

        // 제41항
        t("9,375명", "#i1cge`e}");
        t("5,700,000원", "#e1gjj1jjjp3");
        t("창세기 12,1-9", ";<7,n@o`#ab1a9#i");

        // 제43항
        t("0.48", "#j4dh");
        t("1,000", "#a1jjj");
        t("1,234,567", "#a1bcd1efg");

        // 제43항 다만
        t("02)799-1000", "#jb,0#gii9#ajjj");
        t("820718-2036794", "#hbjgah9#bjcfgid");
        t("100-01-1234-567", "#ajj9#ja9#abcd9#efg");
        t("02-2669-9775~6", "#jb9#bffi9#igge@9#f");
        t("5:3", "#e\"1#c");
        t("10/1~10/9", "#aj_/#a@9#aj_/#i");

        // 제44항
        t("1가", "#a$");
        t("2권", "#b@p3");
        t("3반", "#c^3");
        t("4선", "#d,)");
        t("5월", "#ep1");
        t("6일", "#fo1");
        t("7자루", "#g.\"m");
        t("8꾸러미", "#h,@m\"seo");
        t("5 개", "#e`@r");
        t("8 상자", "#h`l7.");

        // 제44항 다만
        t("1년", "#a`c*");
        t("2도", "#b`iu");
        t("3명", "#c`e]");
        t("4칸", "#d`f3");
        t("5톤", "#e`h(");
        t("6평", "#f`d]");
        t("7항", "#g`j7");
        t("5운6기", "#e`g#f@o");

        // 제45항
        t("5+7=12", "#e5#g33#ab");
        t("9-3=6", "#i9#c33#f");
        t("4×8=32", "#d*#h33#cb");
        t("12÷3=4", "#ab//#c33#d");
        t("7>5", "#g55#e");
        t("6<9", "#f99#i");

        // 제46항
        t("나루 + 배 = 나룻배", "c\"m`5`^r`33`c\"m'^r");
        t("5개-3개=2개", "#e@r`9`#c@r`33`#b@r");
        t("원의 면적은 반지름×반지름×3.14이다.", "p3w`e*.?z`~3.o\"{5`*`~3.o\"{5`* #c4adoi4");
        t("지구는 해왕성보다 작고 금성보다 크다(해왕성>지구>금성).", ".o@mcz`jrv7,]~ui`.a@u`@{5,]~ui f{i8'jrv7,]`55`.o@m`55`@{5,],04");

        // 제48항
        t("원주율은 약 3.14이다.", "p3.m%1z`>a`#c4adoi4");

        // 제49항
        t("젊은이는 나라의 기둥입니다.", ".t5zocz`c\"<w`@oim7obcoi4");
        t("이번에 가시면 언제 돌아오세요?", "o^)n`$,oe*`).n`iu1<u,n+8");
        t("이거 정말 큰일이 났구나!", "o@s`.]e1`fzo1o`c/@mc6");
        t("근면, 검소, 협동은 우리 겨레의 미덕이다.", "@ze*\"`@s5,u\"`j:bi=z`m\"o`@:\"nw`eoi?oi4");
        t("우리는 그 일의 참·거짓을 따질 겨를도 없었다. ", "m\"ocz`@[`o1w`;<5\"2@s.o'!`,i.o1 @:\"!iu`sb's/i4");
        t("문방사우: 종이, 붓, 먹, 벼루", "eg^7lm\"1`.=o\"`^m'\"`e?\"`^:\"m");
        t("남반구/북반구", "c5^3@m_/^ma^3@m");
        t("산에 / 산에 / 피는 꽃은 / 저만치 혼자서 피어 있네", "l3n`_/`l3n`_/`docz`,@u2z`_/`.se3;o`j(.,s`dos`o/cn");
        t("니체(독일의 철학자)의 말을 빌리면 다음과 같다.", "co;n8'ixo1w`;tja.,0w`e1!`^o1\"oe*`i<[5@v`$8i4");
        t("국가의 성립 요소 {영토, 국민, 주권}", "@ma$w`,]\"ob`+,u`81]hu\"`@maeq\"`.m@p3\"0");
        t("어린이날이 새로 제정되었을 당시에는 어린이들에게 경어를 쓰라고 하였다.[윤석중 전집(1988), 70쪽 참조]", "s\"qoc1o`,r\"u`.n.]iys/!`i7,oncz s\"qoi!n@n`@]s\"!`,,[\"<@u`j<:/i482%3,?.m7`.).ob8'#aihh,0\"`#gj,.x ;<5.u;0");
//연산기호인지 아닌지 구별할수없음        t("드디어 서울-호치민의 항로가 열렸다.", "i[ios`,s&-ju;oeqw`j7\"u$`|\":/i4");
        t("9월 15일~9월 25일", "#ip1`#aeo1@9#ip1`#beo1");

        // 제60항
        t("* 야애: 들에 낀 안개", "\"9`>-r\"1`i!n`,@q`<3@r");
    }

    void t(String p, String a) {
        Translator2Braille t = new Translator2Braille();
        BRFParser brfParser = new BRFParser();
        String translate, x;

        translate = t.translate(p);
        Assertions.assertEquals(translate, brfParser.translateBRF2Braille(a.replaceAll("`", " ")));
    }
    @Test
    void 테스트를테스트() {
        Translator2Braille t = new Translator2Braille();
        BRFParser brfParser = new BRFParser();
        String translate, x;
        translate = t.translate("계획은 취소되었다.");
        Assertions.assertEquals(translate, brfParser.translateBRF2Braille("@/jyaz ;mr,uiys/i4".replaceAll("`", " ")));

    }
    @Test
    void translate() {
        Translator2Braille t = new Translator2Braille();
        System.out.println(t.translate("앉아서"));
        System.out.println(t.translate("꺾다"));
        System.out.println(t.translate("품삯 앉다 않다 읽다 옮기다"));
        Assertions.assertEquals(t.translate("품삯 앉다 않다 읽다 옮기다")
                , "⠙⠍⠢⠇⠁⠄ ⠣⠒⠅⠊ ⠣⠒⠴⠊ ⠕⠂⠁⠊ ⠥⠂⠢⠈⠕⠊");
        System.out.println(t.translate("나이 다음 마우스"));
        Assertions.assertEquals(t.translate("나이 다음 마우스")
                , "⠉⠣⠕ ⠊⠣⠪⠢ ⠑⠣⠍⠠⠪");
        System.out.println(t.translate("땅을 팠다"));
        Assertions.assertEquals(t.translate("땅을 팠다")
                , "⠠⠊⠶⠮ ⠙⠣⠌⠊");
        System.out.println(t.translate("삼각형 ㄱㄴㄷ"));
        Assertions.assertEquals(t.translate("삼각형 ㄱㄴㄷ")
                , "⠇⠢⠫⠁⠚⠻ ⠿⠁⠿⠒⠿⠔");
        System.out.println(t.translate("ㄹㄹ로마"));
        Assertions.assertEquals(t.translate("ㄹㄹ로마")
                , "⠸⠂⠸⠂⠐⠥⠑");
        System.out.println(t.translate("겪다 넋 얹다 벌레 옷걸이"));
        Assertions.assertEquals(t.translate("겪다 넋 얹다 벌레 옷걸이")
                , "⠈⠱⠁⠁⠊ ⠉⠹⠄ ⠾⠅⠊ ⠘⠞⠐⠝ ⠥⠄⠈⠞⠕");
        System.out.println(t.translate("까치 힘껏 갓"));
        Assertions.assertEquals(t.translate("까치 힘껏 갓")
                , "⠠⠫⠰⠕ ⠚⠕⠢⠠⠸⠎ ⠫⠄");
        System.out.println(t.translate("팠다"));
        Assertions.assertEquals(t.translate("팠다")
                , "⠙⠣⠌⠊");
        System.out.println(t.translate("그리고서 그리하여도 그러나요"));
        Assertions.assertEquals(t.translate("그리고서 그리하여도 그러나요")
                , "⠁⠥⠠⠎ ⠁⠱⠊⠥ ⠁⠉⠬");
        System.out.println(t.translate("아예 야애 파워앰프 수액"));
        Assertions.assertEquals(t.translate("아예 야애 파워앰프 수액")
                , "⠣⠤⠌ ⠜⠤⠗ ⠙⠣⠏⠤⠗⠢⠙⠪ ⠠⠍⠤⠗⠁");
        System.out.println(t.translate("그녀는 Los Angeles의 한인 타운에 살고 있다"));
        Assertions.assertEquals(t.translate("아예 야애 파워앰프 수액")
                , "⠣⠤⠌ ⠜⠤⠗ ⠙⠣⠏⠤⠗⠢⠙⠪ ⠠⠍⠤⠗⠁");
    }


    @Test
    void translateE() {
        Translator2Braille t = new Translator2Braille();
        System.out.println(t.translate("그녀는 Los Angeles의 한인 타운에 살고 있다"));
        Assertions.assertEquals(t.translate("그녀는 Los Angeles의 한인 타운에 살고 있다")
                , "⠈⠪⠉⠱⠉⠵ ⠴⠠⠇⠕⠎ ⠠⠁⠝⠛⠑⠇⠑⠎⠲⠺ ⠚⠒⠟ ⠓⠣⠛⠝ ⠇⠂⠈⠥ ⠕⠌⠊");
        System.out.println(t.translate("New York"));
        Assertions.assertEquals(t.translate("New York")
                , "⠴⠠⠝⠑⠺ ⠠⠽⠕⠗⠅⠲");
        System.out.println(t.translate("NEW YORK"));
        Assertions.assertEquals(t.translate("NEW YORK")
                , "⠴⠠⠠⠝⠑⠺ ⠠⠠⠽⠕⠗⠅⠲");
        System.out.println(t.translate("iOS"));
        Assertions.assertEquals(t.translate("iOS")
                , "⠴⠊⠠⠠⠕⠎⠲");
        System.out.println(t.translate("마WELCOME TO KOREA마"));
        Assertions.assertEquals(t.translate("WELCOME TO KOREA")
                , "⠴⠠⠠⠠⠺⠑⠇⠉⠕⠍⠑ ⠞⠕ ⠅⠕⠗⠑⠁⠠⠄⠲");
        Assertions.assertEquals(t.translate("마WELCOME TO KOREA마")
                , "⠑⠴⠠⠠⠺⠑⠇⠉⠕⠍⠑ ⠠⠠⠞⠕ ⠠⠠⠅⠕⠗⠑⠁⠲⠑");
        System.out.println(t.translate("(asd)"));
//        System.out.println(BrailleTranslator.translate("Table of Contents"));
    }


    @Test
    void translateN() {
        Translator2Braille t = new Translator2Braille();
        System.out.println(t.translate("8꾸러미"));
        Assertions.assertEquals(t.translate("8꾸러미")
                , "⠼⠓⠠⠈⠍⠐⠎⠑⠕");
        System.out.println(t.translate("375"));
        Assertions.assertEquals(t.translate("375")
                , "⠼⠉⠛⠑");
        System.out.println(t.translate("5,700,000원"));
        Assertions.assertEquals(t.translate("5,700,000원")
                , "⠼⠑⠂⠛⠚⠚⠂⠚⠚⠚⠏⠒");
        System.out.println(t.translate("8 상자"));
        Assertions.assertEquals(t.translate("8 상자")
                , "⠼⠓ ⠇⠶⠨");
        System.out.println(t.translate("4칸 6평 5운6기"));
        Assertions.assertEquals(t.translate("4칸 6평 5운6기")
                , "⠼⠙ ⠋⠒ ⠼⠋ ⠙⠻ ⠼⠑ ⠛⠼⠋⠈⠕");
    }

    @Test
    void translateS() {
        Translator2Braille t = new Translator2Braille();
        String translate = t.translate("젊은이는 나라의 기둥입니다. ");
        BRFParser brfParser = new BRFParser();
        String x = brfParser.translateBraille2BRF(translate);
        System.out.println(translate);
        Assertions.assertEquals(x, ".t5zocz c\"<w @oim7obcoi4");

        translate = t.translate("문방사우: 종이, 붓, 먹, 벼루");
        x = brfParser.translateBraille2BRF(translate);
        System.out.println(translate);
        Assertions.assertEquals(x, "eg^7lm\"1 .=o\" ^m'\" e?\"`^:\"m".replaceAll("`", " "));

        translate = t.translate("“어디 나하고 한번…….” 하고 민수가 나섰다. ");
        x = brfParser.translateBraille2BRF(translate);
        System.out.println(t.translate("“어디 나하고 한번…….” 하고 민수가 나섰다. "));
        Assertions.assertEquals(x, "8sio`cj@u`j3^),,,,,,40`j@u`eq,m$`c,s/i4".replaceAll("`", " "));

        translate = t.translate("니체(독일의 철학자)의 말을 빌리면 다음과 같다.");
        x = brfParser.translateBraille2BRF(translate);
        System.out.println(translate);
        Assertions.assertEquals(x, "co;n8'ixo1w`;tja.,0w`e1!`^o1\"oe*`i<{5@v`$8i4".replaceAll("`", " "));

        translate = t.translate("어린이날이 새로 제정되었을 당시에는 어린이들에게 경어를 쓰라고 하였다.[윤석중 전집(1988), 70쪽 참조]");
        x = brfParser.translateBraille2BRF(translate);
        System.out.println(translate);
        Assertions.assertEquals(x, "s\"qoc1o`,r\"u`.n.}iys/!`i7,oncz s\"qoi!n@n`@}s\"!`,,{\"<@u`j<:/i482%3,?.m7`.).ob8'#aihh,0\"`#gj,.x ;<5.u;0".replaceAll("`", " "));


        translate = t.translate("어린이날이 새로 제정되었을 당시에는 어린이들에게 경어를 쓰라고 하였다.[윤석중 전집(1988), 70쪽 참조]");
        x = brfParser.translateBraille2BRF(translate);
    }



    @Test
    void translateH() {
        Translator2Braille t = new Translator2Braille();
        String translate = t.translate("젊은이는 나라의 기둥입니다. ");
        BRFParser brfParser = new BRFParser();
        String x = brfParser.translateBraille2BRF(translate);

        String s = "3X4㎝학교명대학(교) 학과 학년/과정주 소e-mail전화번호휴대전화\n";
        String[] split = s.split("\n");
        for (String s1 : split) {
            System.out.println(t.translate(s1));
        }
    }
    @Test
    void translateH2() {
        Translator2Braille t = new Translator2Braille();
        String translate = t.translate("3X4 학교명대학(교)");
//        String translate = t.translate("3X4");
        BRFParser brfParser = new BRFParser();
        String x = brfParser.translateBraille2BRF(translate);

        System.out.println(translate);
    }
}