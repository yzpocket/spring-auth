package com.citefred.authpractice.controller;

import com.citefred.authpractice.service.KakaoAuthService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth/kakao")
public class KakaoAuthController {

    private final KakaoAuthService kakaoAuthService;

    public KakaoAuthController(KakaoAuthService kakaoAuthService) {
        this.kakaoAuthService = kakaoAuthService;
    }

    @GetMapping("/login")
    public String kakaoLogin() {
        return "redirect:" + kakaoAuthService.getAuthorizationUrl();
    }

    @GetMapping("/callback")
    public ResponseEntity<String> kakaoCallback(@RequestParam("code") String code) {
        String accessToken = kakaoAuthService.getAccessToken(code);
        // 여기서 accessToken을 사용하여 사용자 정보를 가져오거나, 로그인 처리를 진행할 수 있음
        return ResponseEntity.ok("Kakao login callback success");
    }
}
