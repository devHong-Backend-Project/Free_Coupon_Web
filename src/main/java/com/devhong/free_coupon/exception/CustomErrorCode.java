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
    NAME_OR_MOBILE_NUMBER_ALREADY_EXISTS(HttpStatus.BAD_REQUEST, "회원이름 혹은 휴대폰 번호가 이미 등록되어있습니다."),
    NAME_OR_BUSINESS_NUMBER_ALREADY_EXISTS(HttpStatus.BAD_REQUEST, "회원이름 혹은 사업자 번호가 이미 등록되어있습니다."),
    BUSINESS_NUMBER_ALREADY_EXISTS(HttpStatus.BAD_REQUEST, "이미 등록된 사업자번호입니다."),
    CATEGORY_NOT_FOUND(HttpStatus.BAD_REQUEST, "존재하지않는 카테고리 이름입니다."),
    TEMPLATE_NOT_FOUND(HttpStatus.BAD_REQUEST, "존재하지않는 템플릿 입니다."),
    USER_TEMPLATE_NOT_MATCH(HttpStatus.BAD_REQUEST, "회원님이 생성하신 템플릿이 아닙니다."),
    NO_TICKETS_AVAILABLE(HttpStatus.BAD_REQUEST, "티켓 개수가 부족합니다."),
    FEED_NOT_FOUND(HttpStatus.BAD_REQUEST, "쿠폰 정보를 찾을 수 없습니다."),
    NOT_PARTNERS_COUPON(HttpStatus.BAD_REQUEST, "회원님이 발급한 쿠폰이 아닙니다."),
    COUPON_NOT_FOUND(HttpStatus.BAD_REQUEST, "쿠폰정보를 찾을 수 없습니다."),
    COUPON_ALREADY_USED(HttpStatus.BAD_REQUEST, "이미 사용처리된 쿠폰입니다."),
    COUPON_EXPIRED(HttpStatus.BAD_REQUEST, "유효기간이 지난 쿠폰입니다.");

    private final HttpStatus httpStatus;
    private final String errorMessage;

}