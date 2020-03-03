package com.nbw.blue.springboot.error;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ResponseCode {
    SUCCESS(HttpStatus.OK, "성공"),
    UNAUTHORIZED(HttpStatus.UNAUTHORIZED, "인증이 필요합니다."),
    INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "내부 서버 에러"),

    ALREADY_EXIST_USER(HttpStatus.BAD_REQUEST, "이미 가입한 회원입니다."),
    INVALID_PARAMETER(HttpStatus.BAD_REQUEST, "파라미터가 유효하지 않습니다."),
    NOT_EXIST_USER(HttpStatus.BAD_REQUEST, "존재하지 않는 회원입니다."),


    INVALID_PASSWORD(HttpStatus.BAD_REQUEST, "비밀번호가 일치하지 않습니다."),
    INVALID_SIGN_TOKEN(HttpStatus.BAD_REQUEST, "인증토큰이 유호하지 않습니다."),
    NEED_TO_SIGN_TOKEN(HttpStatus.BAD_REQUEST, "인증토큰이 필요합니다."),

    NOT_EXIST_USER_INFO(HttpStatus.BAD_REQUEST, "회원 정보가 없습니다."),
    NOT_EXIST_PHONE(HttpStatus.BAD_REQUEST, "전화번호가 없습니다."),

    ALREADY_EXIST_PHONE_NUMBER(HttpStatus.BAD_REQUEST, "이미 가입된 전화번호입니다."),

    // 비밀번호 리셋
    NOT_MATCH_MAIL(HttpStatus.BAD_REQUEST, "가입하신 이메일과 일치하지 않습니다.");

    private HttpStatus httpStatus;
    private String message;

}