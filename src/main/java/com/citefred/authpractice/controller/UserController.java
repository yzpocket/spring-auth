package com.citefred.authpractice.controller;

import com.citefred.authpractice.dto.SignupRequestDto;
import com.citefred.authpractice.service.UserService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Slf4j
@Controller
@RequestMapping("/api")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }


    @GetMapping("/user/login-page")
    public String loginPage() {
        return "login";
    }

    @GetMapping("/user/signup")
    public String signupPage() {
        return "signup";
    }
    // 회원가입 API + 유효성검사
    @PostMapping("/user/signup")
    public String signup(@Valid SignupRequestDto requestDto, BindingResult bindingResult) {
        // Validation 예외처리
        List<FieldError> fieldErrors = bindingResult.getFieldErrors(); // 유효성 검사
        if(fieldErrors.size() > 0) {
            for (FieldError fieldError : bindingResult.getFieldErrors()) {
                log.error(fieldError.getField() + " 필드 : " + fieldError.getDefaultMessage()); // 검사 실패 부분 로그로 확인하기
            }
            return "redirect:/api/user/signup"; // 검사 실패 시, 다시 회원가입 페이지로 이동
        }

        userService.signup(requestDto);
        return "redirect:/api/user/login-page";
    }
    // 로그인 API는 Jwt 로그인 구현으로 삭제됨
}