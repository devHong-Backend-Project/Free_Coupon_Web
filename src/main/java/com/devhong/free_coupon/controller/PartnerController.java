package com.devhong.free_coupon.controller;

import com.devhong.free_coupon.dto.BaseResponseDto;
import com.devhong.free_coupon.dto.QrCouponDto;
import com.devhong.free_coupon.dto.TemplateDto;
import com.devhong.free_coupon.model.CouponFeed;
import com.devhong.free_coupon.model.CouponTemplate;
import com.devhong.free_coupon.model.Partner;
import com.devhong.free_coupon.service.PartnerService;
import com.devhong.free_coupon.type.ResponseMsg;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/partner")
@PreAuthorize("hasRole('PARTNER')")
public class PartnerController {

    private final PartnerService partnerService;

    /*
        쿠폰 템플릿 추가하기
     */
    @PostMapping("/template")
    public ResponseEntity<?> addTemplate(@RequestBody @Valid TemplateDto.Request request,
                                         @RequestHeader("Authorization") String jwtHeader) {
        CouponTemplate couponTemplate = partnerService.addTemplate(request, jwtHeader);

        log.info(String.format("partner_id_%d add template",
                couponTemplate.getPartner().getId()));
        return ResponseEntity.ok(new BaseResponseDto.BaseResponse("success", ResponseMsg.ADD_TEMPLATE_SUCCESS.getMessage()));
    }


    /*
        쿠폰 템플릿 수정하기
        - 클라이언트로부터 template_id 와 수정할 template 정보를 입력받아 업데이트 수행
     */
    @PutMapping("/template/{template_id}")
    public ResponseEntity<?> updateTemplate(@RequestBody @Valid TemplateDto.Request request,
                                            @PathVariable Long template_id,
                                            @RequestHeader("Authorization") String jwtHeader){
        CouponTemplate couponTemplate = partnerService.updateTemplate(jwtHeader, template_id, request);

        log.info(String.format("partner_id_%d update template",
                couponTemplate.getPartner().getId()));
        return ResponseEntity.ok(new BaseResponseDto.BaseResponse("success", ResponseMsg.UPDATE_TEMPLATE_SUCCESS.getMessage()));
    }


    /*
        쿠폰 템플릿 삭제하기
     */
    @DeleteMapping("/template/{template_id}")
    public ResponseEntity<?> deleteTemplate(@PathVariable Long template_id,
                                            @RequestHeader("Authorization") String jwtHeader) {
        Partner partner = partnerService.deleteTemplate(jwtHeader, template_id);

        log.info(String.format("partner_id_%d delete template",
                partner.getId()));
        return ResponseEntity.ok(new BaseResponseDto.BaseResponse("success", ResponseMsg.DELETE_TEMPLATE_SUCCESS.getMessage()));
    }


    /*
        쿠폰 템플릿 목록 보기
        - 헤더에 있는 jwt 토큰을 가져와서 해당 유저가 만든 쿠폰 템플릿 목록을 보여준다.
     */
    @GetMapping("/template/list")
    public ResponseEntity<?> getTemplates(@RequestHeader("Authorization") String jwtHeader) {
        List<TemplateDto.TemplateResponse> templates = partnerService.getTemplates(jwtHeader);
        return ResponseEntity.ok(new BaseResponseDto.DataResponse("success", ResponseMsg.GET_TEMPLATE_LIST.getMessage(), templates));
    }


    /*
        쿠폰 등록하기
     */
    @PostMapping("/register-coupon/{template_id}")
    public ResponseEntity<?> registerCoupon(@PathVariable Long template_id ,@RequestParam("amount") Integer amount, @RequestHeader("Authorization") String jwtHeader) {
        CouponFeed couponFeed = partnerService.registerCoupon(jwtHeader, template_id, amount);
        log.info(String.format("partner_id_%d register coupon(feed_id_%d)",
                couponFeed.getPartnerId(), couponFeed.getId()));
        return ResponseEntity.ok(new BaseResponseDto.BaseResponse("success", ResponseMsg.REGISTER_COUPON_SUCCESS.getMessage()));
    }

    /*
        Qr쿠폰 사용처리하기
        - Qr코드를 스캔하면 Qr코드안에 삽입했던 url로 접속-> checkQrCoupon api 실행
        - 자신이 발급한 쿠폰이 맞는지, 이미 사용한 쿠폰인지 확인, 유효기간 확인
     */
    @PutMapping("/use-coupon/{uuid}/{partner_id}")
    public ResponseEntity<?> useQrCoupon(@PathVariable String uuid, @PathVariable Long partner_id, @RequestHeader("Authorization") String jwtHeader) {
        QrCouponDto.QrCouponInfo qrCouponInfo = partnerService.useQrCoupon(jwtHeader, uuid, partner_id);

        return ResponseEntity.ok(new BaseResponseDto.DataResponse("success", ResponseMsg.USE_COUPON_SUCCESS.getMessage(), new ArrayList<>(Arrays.asList(qrCouponInfo))));
    }

}
