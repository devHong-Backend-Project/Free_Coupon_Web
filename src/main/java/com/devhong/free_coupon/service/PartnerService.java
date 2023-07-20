package com.devhong.free_coupon.service;

import com.devhong.free_coupon.dto.TemplateDto;
import com.devhong.free_coupon.exception.CustomErrorCode;
import com.devhong.free_coupon.exception.CustomException;
import com.devhong.free_coupon.model.CouponFeed;
import com.devhong.free_coupon.model.CouponTemplate;
import com.devhong.free_coupon.model.Partner;
import com.devhong.free_coupon.repository.CouponFeedRepository;
import com.devhong.free_coupon.repository.CouponTemplateRepository;
import com.devhong.free_coupon.repository.PartnerRepository;
import com.devhong.free_coupon.security.TokenProvider;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class PartnerService {

    private final CouponTemplateRepository couponTemplateRepository;
    private final PartnerRepository partnerRepository;
    private final CouponFeedRepository couponFeedRepository;
    private final TokenProvider tokenProvider;

    public CouponTemplate addTemplate(TemplateDto.Request request, String header) {
        Long userId = getUserIdFromHeader(header);
        Partner partner = partnerRepository.findById(userId)
                .orElseThrow(()-> new CustomException(CustomErrorCode.USER_NOT_FOUND));

        return couponTemplateRepository.save(request.toEntity(partner));
    }

    private String getNameFromHeader(String header) {
        return tokenProvider.getUserName(tokenProvider.resolveTokenFromHeader(header));
    }

    @Transactional
    public CouponTemplate updateTemplate(String header, Long templateId, TemplateDto.Request request) {
        CouponTemplate couponTemplate = couponTemplateRepository.findById(templateId)
                .orElseThrow(()->new CustomException(CustomErrorCode.TEMPLATE_NOT_FOUND));

        validateTemplate(header, couponTemplate);

        couponTemplate.updateEntity(request);

        return couponTemplate;
    }

    @Transactional
    public Partner deleteTemplate(String header, Long templateId) {
        CouponTemplate couponTemplate = couponTemplateRepository.findById(templateId)
                .orElseThrow(()->new CustomException(CustomErrorCode.TEMPLATE_NOT_FOUND));

        validateTemplate(header, couponTemplate);

        couponTemplateRepository.deleteById(templateId);

        return couponTemplate.getPartner();
    }

    // 유저가 생성한 템플릿이 맞는지 확인
    private void validateTemplate(String header, CouponTemplate couponTemplate) {
        Long userId = getUserIdFromHeader(header);

        if (!couponTemplate.getPartner().getId().equals(userId)){
            throw new CustomException(CustomErrorCode.USER_TEMPLATE_NOT_MATCH);
        }
    }

    public List<TemplateDto.TemplateResponse> getTemplates(String header) {
        Long userId = getUserIdFromHeader(header);
        Partner partner = partnerRepository.findById(userId)
                .orElseThrow(()-> new CustomException(CustomErrorCode.USER_NOT_FOUND));

        List<CouponTemplate> templates = partnerRepository.findTemplatesByPartnerId(partner.getId());

        return templates.stream().map(TemplateDto.TemplateResponse::fromEntity).collect(Collectors.toList());
    }

    public CouponFeed registerCoupon(String header, Long templateId, Long amount) {
        CouponTemplate couponTemplate = couponTemplateRepository.findById(templateId)
                .orElseThrow(()->new CustomException(CustomErrorCode.TEMPLATE_NOT_FOUND));

        validateTemplate(header, couponTemplate);

        return couponFeedRepository.save(couponTemplate.toFeedEntity(amount));
    }

    public Long getUserIdFromHeader(String header) {
        return tokenProvider.getUserId(tokenProvider.resolveTokenFromHeader(header));
    }
}
