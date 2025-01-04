package org.vision.bridge.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@Service
public class FlaskApiService {

    @Value("${flask.api.url}")
    private String flaskUrl;

    public String sendTranslateRequest(String sourceText) {
        RestTemplate restTemplate = new RestTemplate();

        // HTTP 요청 헤더 설정
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        // 요청 바디 데이터 설정
        Map<String, String> requestBody = new HashMap<>();
        requestBody.put("sourceText", sourceText);

        // HTTP 요청 생성
        HttpEntity<Map<String, String>> entity = new HttpEntity<>(requestBody, headers);

        // Flask 서버로 POST 요청 전송
        ResponseEntity<Map> response = restTemplate.postForEntity(flaskUrl, entity, Map.class);

        // 응답 결과 처리
        if (response.getStatusCode().is2xxSuccessful()) {
            Map<String, String> responseBody = response.getBody();
            return responseBody != null ? responseBody.get("translatedText") : "응답이 없습니다.";
        }

        return "Flask 서버와 통신 중 오류 발생.";
    }
}
