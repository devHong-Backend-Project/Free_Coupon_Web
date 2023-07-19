package com.devhong.free_coupon.dto;

import com.devhong.free_coupon.model.CouponTemplate;
import com.devhong.free_coupon.model.Partner;
import com.devhong.free_coupon.type.Category;
import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class TemplateDto {

    @Data
    public static class AddRequest {
        @NotBlank
        private String category;
        @NotBlank
        private String couponName;
        @NotNull
        private Long couponValue;
        @NotNull
        private Long expiredPeriod;
        @NotBlank
        private String description;
        @NotBlank
        private String imgUrl;

        public CouponTemplate toEntity(Partner partner){
            return CouponTemplate.builder()
                    .partner(partner)
                    .category(Category.fromString(category))
                    .couponName(couponName)
                    .couponValue(couponValue)
                    .expiredPeriod(expiredPeriod)
                    .description(description)
                    .imgUrl(imgUrl)
                    .build();
        }
    }

    @AllArgsConstructor
    public static class AddResponse {
        public String status;
        public String message;
    }
}
