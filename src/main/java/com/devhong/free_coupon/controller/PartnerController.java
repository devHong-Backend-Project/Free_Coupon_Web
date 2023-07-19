package com.devhong.free_coupon.controller;

import com.devhong.free_coupon.dto.TemplateDto;
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
    @PostMapping("/template/create")
    public ResponseEntity<?> addTemplate(@RequestBody @Valid TemplateDto.Request request,
                                         @RequestHeader("Authorization") String token) {
        CouponTemplate couponTemplate = partnerService.addTemplate(request, token);

        log.info("partner_id " + couponTemplate.getId().toString() + " : Add Template");
        return ResponseEntity.ok(new TemplateDto.Response("success", ResponseMsg.ADD_TEMPLATE_SUCCESS.getMessage()));
    }


    /*
        쿠폰 템플릿 수정하기
     */
    @PostMapping("/template/update/{template_id}")
    public ResponseEntity<?> updateTemplate(@RequestBody @Valid TemplateDto.Request request,
                                            @PathVariable Long template_id){
        CouponTemplate couponTemplate = partnerService.updateTemplate(template_id, request);

        log.info("partner_id " + couponTemplate.getId().toString() + " : Update Template");
        return ResponseEntity.ok(new TemplateDto.Response("success", ResponseMsg.UPDATE_TEMPLATE_SUCCESS.getMessage()));
    }


    /*
        쿠폰 템플릿 삭제하기
     */
    @PostMapping("/template/delete/{template_id}")
    public ResponseEntity<?> deleteTemplate(@PathVariable Long template_id) {
        Partner partner = partnerService.deleteTemplate(template_id);

        log.info("partner_id " + partner.getId().toString() + " : Delete Template");
        return ResponseEntity.ok(new TemplateDto.Response("success", ResponseMsg.DELETE_TEMPLATE_SUCCESS.getMessage()));
    }
}
