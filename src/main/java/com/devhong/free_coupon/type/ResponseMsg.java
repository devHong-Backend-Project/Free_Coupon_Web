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
    UPDATE_TEMPLATE_SUCCESS("템플릿을 수정하였습니다.", HttpStatus.OK),
    DELETE_TEMPLATE_SUCCESS("템플릿을 삭제하였습니다.", HttpStatus.OK),
    GET_TEMPLATE_LIST("템플릿 목록 조회", HttpStatus.OK),
    REGISTER_COUPON_SUCCESS("쿠폰을 등록하였습니다.", HttpStatus.OK),
    PARTICIPATE_FEED_SUCCESS("응모하였습니다", HttpStatus.OK),
    USE_COUPON_SUCCESS("쿠폰이 사용처리 되었습니다.", HttpStatus.OK),
    GET_COUPON_LIST_BY_CATEGORY("카테고리별 쿠폰 목록 조회", HttpStatus.OK),
    GET_COUPON_LIST_BY_SEARCH("쿠폰 이름 검색", HttpStatus.OK);

    private final String message;
    private final HttpStatus httpStatus;
}
