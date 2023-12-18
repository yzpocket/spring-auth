package com.citefred.authpractice.service;

import com.citefred.authpractice.config.KakaoProperties;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class KakaoAuthService {

    private final RestTemplate restTemplate;
    private final KakaoProperties kakaoProperties;

    public KakaoAuthService(RestTemplate restTemplate, KakaoProperties kakaoProperties) {
        this.restTemplate = restTemplate;
        this.kakaoProperties = kakaoProperties;
    }

    public String getAuthorizationUrl() {
        // 카카오 인증 URL을 생성하는 로직
        String kakaoAuthUrl = "https://kauth.kakao.com/oauth/authorize?" +
                "client_id=" + kakaoProperties.getClientId() +
                "&redirect_uri=" + kakaoProperties.getRedirectUri() +
                "&response_type=code";

        return kakaoAuthUrl;
    }

    public String getAccessToken(String code) {
        // Authorization Code를 이용하여 Access Token을 요청하는 로직
        String kakaoTokenUrl = "https://kauth.kakao.com/oauth/token?" +
                "grant_type=authorization_code" +
                "&client_id=" + kakaoProperties.getClientId() +
                "&client_secret=" + kakaoProperties.getClientSecret() +
                "&redirect_uri=" + kakaoProperties.getRedirectUri() +
                "&code=" + code;

        // restTemplate을 사용하여 Kakao 서버에 토큰 요청
        // 결과로 받은 JSON에서 access_token 추출
        String response = restTemplate.postForObject(kakaoTokenUrl, null, String.class);
        // 여기서 JSON 파싱 등을 통해 access_token을 추출하여 반환

        return "받아온_Access_Token";
    }
}
