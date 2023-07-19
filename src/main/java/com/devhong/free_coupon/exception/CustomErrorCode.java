package com.devhong.free_coupon.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

/*
    커스텀 exception
 */
@Getter
@AllArgsConstructor
public enum CustomErrorCode {

    USER_ALREADY_EXISTS(HttpStatus.BAD_REQUEST ,"이미 회원가입이 되어있는 아이디입니다."),
    USER_NOT_FOUND(HttpStatus.BAD_REQUEST, "존재하지 않는 회원입니다."),
    PASSWORD_NOT_MATCH(HttpStatus.BAD_REQUEST, "비밀번호가 일치하지 않습니다."),
    ACCESS_DENIED(HttpStatus.FORBIDDEN, "요청 접근 권한이 없습니다."),
    MOBILE_NUMBER_ALREADY_EXISTS(HttpStatus.BAD_REQUEST, "이미 등록된 휴대폰번호입니다."),
    BUSINESS_NUMBER_ALREADY_EXISTS(HttpStatus.BAD_REQUEST, "이미 등록된 사업자번호입니다."),
    CATEGORY_NOT_FOUND(HttpStatus.BAD_REQUEST, "존재하지않는 카테고리 이름입니다.");

    private final HttpStatus httpStatus;
    private final String errorMessage;

}