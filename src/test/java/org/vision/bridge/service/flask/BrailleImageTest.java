package org.vision.bridge.service.flask;

import org.json.JSONException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.json.JSONObject;
import org.vision.bridge.service.translation.Translator2Text;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class BrailleImageTest {

    @Autowired
    BrailleImage brailleImage;
    @Test
    public void imageTest() throws Exception{
        it("braille/4번_test.png", "4번출입구");
        it("braille/교보빌딩_test.png", "교보빌딩");
        it("braille/위원회_test.png", "방송통신위원회");
        it("braille/출입구_test.png", "출입구주변안내");
        it("braille/제어실_test.png", "제어실");
        it("braille/화서_test.jpg", "화서방면 8-파");
        it("braille/남화_test.png", "남자화장실");
        it("braille/여샤장_test.png", "여자샤워실-장애인-");
        it("braille/엘베_test.png", "엘리베이터");
        it("braille/다목적홀_test.png", "다목적홀");
        it("braille/화장실_test.png", "화장실");
    }
    @Test
    public void itf() throws Exception{
        it("braille/image.png", "여자샤워실-장애인-");
    }

    public void it(String filePath, String co) throws JSONException, IOException {
        Translator2Text tt = new Translator2Text();
        String t, translate;
        t = at(filePath);
        translate = tt.translate(t.trim());
        System.out.println(translate);
        Assertions.assertEquals(co, translate.trim());
    }

    public String at(String filePath) throws IOException, JSONException {
        File file = new ClassPathResource(filePath).getFile();
        MultipartFile multipartFile = convertFileToMultipartFile(file);
        String braille = brailleImage.sendImageToFlask(multipartFile);
        JSONObject jsonObject = new JSONObject(braille);
        return jsonObject.getString("braille_text");
    }

    public static MultipartFile convertFileToMultipartFile(File file) throws IOException {
        FileInputStream inputStream = new FileInputStream(file);
        return new MockMultipartFile(
                file.getName(),           // 원본 파일명
                file.getName(),           // 파일명
                "application/octet-stream", // 파일 타입 (일반 바이너리 데이터)
                inputStream               // 파일 데이터
        );
    }
}