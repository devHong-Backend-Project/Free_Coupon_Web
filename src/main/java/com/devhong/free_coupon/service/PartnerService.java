package com.devhong.free_coupon.service;

import com.devhong.free_coupon.dto.QrCouponDto;
import com.devhong.free_coupon.dto.TemplateDto;
import com.devhong.free_coupon.exception.CustomErrorCode;
import com.devhong.free_coupon.exception.CustomException;
import com.devhong.free_coupon.model.CouponFeed;
import com.devhong.free_coupon.model.CouponTemplate;
import com.devhong.free_coupon.model.Partner;
import com.devhong.free_coupon.model.QrCoupon;
import com.devhong.free_coupon.repository.CouponFeedRepository;
import com.devhong.free_coupon.repository.CouponTemplateRepository;
import com.devhong.free_coupon.repository.PartnerRepository;
import com.devhong.free_coupon.repository.QrCouponRepository;
import com.devhong.free_coupon.security.TokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PartnerService {

    private final CouponTemplateRepository couponTemplateRepository;
    private final PartnerRepository partnerRepository;
    private final CouponFeedRepository couponFeedRepository;
    private final QrCouponRepository qrCouponRepository;
    private final TokenProvider tokenProvider;

    public CouponTemplate addTemplate(TemplateDto.Request request, String header) {
        Long userId = tokenProvider.getUserIdFromHeader(header);
        Partner partner = partnerRepository.findById(userId)
                .orElseThrow(()-> new CustomException(CustomErrorCode.USER_NOT_FOUND));

        return couponTemplateRepository.save(request.toEntity(partner));
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
        Long userId = tokenProvider.getUserIdFromHeader(header);

        if (!couponTemplate.getPartner().getId().equals(userId)){
            throw new CustomException(CustomErrorCode.USER_TEMPLATE_NOT_MATCH);
        }
    }

    public List<TemplateDto.TemplateResponse> getTemplates(String header) {
        Long userId = tokenProvider.getUserIdFromHeader(header);
        Partner partner = partnerRepository.findById(userId)
                .orElseThrow(()-> new CustomException(CustomErrorCode.USER_NOT_FOUND));

        List<CouponTemplate> templates = partnerRepository.findTemplatesByPartnerId(partner.getId());

        return templates.stream().map(TemplateDto.TemplateResponse::fromEntity).collect(Collectors.toList());
    }

    /*
        쿠폰 등록하기
        1. 템플릿 유무 체크
        2. validateTemplate : 해당 유저가 생성한 템플릿이 맞는지 확인
     */
    public CouponFeed registerCoupon(String header, Long templateId, Integer amount) {
        CouponTemplate couponTemplate = couponTemplateRepository.findById(templateId)
                .orElseThrow(()->new CustomException(CustomErrorCode.TEMPLATE_NOT_FOUND));

        validateTemplate(header, couponTemplate);

        return couponFeedRepository.save(couponTemplate.toFeedEntity(amount));
    }

    public QrCouponDto.QrCouponInfo checkQrCoupon(String header, String uuid, Long partnerId) {
        QrCoupon qrCoupon = validateQrCoupon(header, uuid, partnerId);

        return QrCouponDto.QrCouponInfo.fromEntity(qrCoupon);
    }

    private QrCoupon validateQrCoupon(String header, String uuid, Long partnerId) {
        Long userId = tokenProvider.getUserIdFromHeader(header);
        if (!userId.equals(partnerId)){
            throw new CustomException(CustomErrorCode.NOT_PARTNERS_COUPON);
        }

        QrCoupon qrCoupon = qrCouponRepository.findByUuid(uuid)
                .orElseThrow(()->new CustomException(CustomErrorCode.COUPON_NOT_FOUND));

        if (qrCoupon.is_used()) {
            throw new CustomException(CustomErrorCode.COUPON_ALREADY_USED);
        }

        return qrCoupon;
    }
}
