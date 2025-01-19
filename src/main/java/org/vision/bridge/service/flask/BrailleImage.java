package org.vision.bridge.service.flask;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.core.io.InputStreamResource;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.io.IOException;

@Service
public class BrailleImage {

    @Value("${flask.api.url}")
    private String flaskUrl;

    private final RestTemplate restTemplate;

    public BrailleImage(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
    }

    public String sendImageToFlask(MultipartFile file) {
        if (file.isEmpty()) {
            throw new IllegalArgumentException("업로드된 파일이 비어 있습니다.");
        }

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.MULTIPART_FORM_DATA);

        try {
            MultiValueMap<String, Object> body = new LinkedMultiValueMap<>();
            body.add("file", new InputStreamResource(file.getInputStream()) {
                @Override
                public String getFilename() {
                    return file.getOriginalFilename();
                }
            });

            HttpEntity<MultiValueMap<String, Object>> requestEntity = new HttpEntity<>(body, headers);

            System.out.println("Spring: Flask로 요청 전송 - " + flaskUrl);

            ResponseEntity<String> response = restTemplate.postForEntity(flaskUrl, requestEntity, String.class);

            System.out.println("Spring: Flask 응답 상태 - " + response.getStatusCode());
            System.out.println("Spring: Flask 응답 본문 - " + response.getBody());

            if (response.getStatusCode().is2xxSuccessful()) {
                return response.getBody();
            } else {
                throw new RuntimeException("Flask 서버 호출 실패: " + response.getStatusCode());
            }
        } catch (IOException e) {
            throw new RuntimeException("파일 처리 중 오류 발생: " + e.getMessage(), e);
        }
    }
}
