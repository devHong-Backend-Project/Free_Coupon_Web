package com.devhong.free_coupon.dto;

import com.devhong.free_coupon.model.CouponFeed;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

public class CouponDto {

    @Data
    public static class CategoryRequest {
        @NotBlank
        String category;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class CouponInfo{
        private String category;

        private String couponName;

        private Integer couponValue;

        private Integer expiredPeriod;

        private String description;

        private String imgUrl;

        private Integer amount;

        private Long participantCount;

        public static CouponInfo fromEntity(CouponFeed couponFeed) {
            return CouponInfo.builder()
                    .category(couponFeed.getCategory().getCategory())
                    .couponName(couponFeed.getCouponName())
                    .couponValue(couponFeed.getCouponValue())
                    .expiredPeriod(couponFeed.getExpiredPeriod())
                    .description(couponFeed.getDescription())
                    .imgUrl(couponFeed.getImgUrl())
                    .amount(couponFeed.getAmount())
                    .build();
        }

    }
}
