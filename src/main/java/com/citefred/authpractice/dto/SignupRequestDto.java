package com.citefred.authpractice.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SignupRequestDto {
    @NotBlank
    private String username;

    @NotBlank
    private String password;

    // 정규표현식 예시
    // 기본적으로 ^는 시작, $는 끝이다.
    // 기본적으로 +로 결합 할 수 있다.
    // 기본적으로 [] 로 그룹을 지을 수 있다.
    // 이정도만 알아도 암호같은 정규식을 보는 것이 편해진다.

    // @ 기호를 확인합니다. 기호 앞과 뒤 문자는 신경쓰지 않습니다.
    //^(.+)@(.+)$
    // @ 기호 앞에 오는 방식에 제한을 추가합니다.
    // A-Z, a-z, 0-9, ., _ 를 사용할 수 있습니다.
    //^[A-Za-z0-9+_.-]+@(.+)$
    // 이메일 형식에 허용되는 문자를 모두 사용할 수 있습니다.
    //^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$

    //email에 대한 정규식으로 커스터마이즈 유효성 검사
    @Pattern(regexp = "^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$")
    //@Email 어노테이션 방식 기본 유효성 검사
    @NotBlank
    private String email;

    private boolean admin = false;

    private String adminToken = "";
}