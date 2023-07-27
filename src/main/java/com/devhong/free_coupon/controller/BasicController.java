package com.devhong.free_coupon.controller;

import com.devhong.free_coupon.dto.BaseResponseDto;
import com.devhong.free_coupon.dto.CouponDto;
import com.devhong.free_coupon.service.BasicService;
import com.devhong.free_coupon.type.ResponseMsg;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
public class BasicController {

    private final BasicService basicService;

    @GetMapping("/coupon-list/category")
    public ResponseEntity<?> getCouponListByCategory(@RequestBody @Valid CouponDto.CategoryRequest categoryRequest) {
        List<CouponDto.CouponInfo> couponInfoList = basicService.getCouponListByCategory(categoryRequest);

        return ResponseEntity.ok(new BaseResponseDto.DataResponse("success", ResponseMsg.GET_COUPON_LIST_BY_CATEGORY.getMessage(), couponInfoList));
    }

    @GetMapping("/coupon-list/search")
    public ResponseEntity<?> searchCouponName(@RequestParam("coupon_name") String couponName) {
        List<CouponDto.CouponInfo> couponInfoList = basicService.searchCouponName(couponName);

        return ResponseEntity.ok(new BaseResponseDto.DataResponse("success", ResponseMsg.GET_COUPON_LIST_BY_SEARCH.getMessage(), couponInfoList));
    }
}
