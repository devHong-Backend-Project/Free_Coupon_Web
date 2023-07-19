package com.devhong.free_coupon.type;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ResponseMsg {
    ADD_TEMPLATE_SUCCESS("쿠폰 템플릿을 추가하였습니다.", HttpStatus.OK),
    SIGNIN_SUCCESS("로그인 성공", HttpStatus.OK),
    SIGNUP_SUCCESS("회원가입이 완료되었습니다.", HttpStatus.OK),
    UPDATE_TEMPLATE_SUCCESS("템플릿을 수정하였습니다.", HttpStatus.OK);

    private final String message;
    private final HttpStatus httpStatus;
}
