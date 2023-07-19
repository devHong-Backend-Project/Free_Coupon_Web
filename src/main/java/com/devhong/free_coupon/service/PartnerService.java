package com.devhong.free_coupon.service;

import com.devhong.free_coupon.dto.TemplateDto;
import com.devhong.free_coupon.exception.CustomErrorCode;
import com.devhong.free_coupon.exception.CustomException;
import com.devhong.free_coupon.model.CouponTemplate;
import com.devhong.free_coupon.model.Partner;
import com.devhong.free_coupon.repository.CouponTemplateRepository;
import com.devhong.free_coupon.repository.PartnerRepository;
import com.devhong.free_coupon.security.TokenProvider;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class PartnerService {

    private final CouponTemplateRepository couponTemplateRepository;
    private final PartnerRepository partnerRepository;
    private final TokenProvider tokenProvider;

    public CouponTemplate addTemplate(TemplateDto.AddRequest request, String token) {
        String name = getNameFromToken(token);
        log.info(name);
        Partner partner = partnerRepository.findByName(name)
                .orElseThrow(()-> new CustomException(CustomErrorCode.USER_NOT_FOUND));

        return couponTemplateRepository.save(request.toEntity(partner));
    }

    private String getNameFromToken(String token) {
        return tokenProvider.getUsername(token.substring("Bearer ".length())).split("_")[0];
    }
}
