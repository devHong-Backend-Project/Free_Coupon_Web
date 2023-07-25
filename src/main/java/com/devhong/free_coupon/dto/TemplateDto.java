package com.devhong.free_coupon.dto;

import com.devhong.free_coupon.model.CouponTemplate;
import com.devhong.free_coupon.model.Partner;
import com.devhong.free_coupon.type.Category;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

public class TemplateDto {

    @Data
    public static class Request {
        @NotBlank
        private String category;
        @NotBlank
        private String couponName;
        @NotNull
        private Integer couponValue;
        @NotNull
        private Integer expiredPeriod;
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

    @Data
    @AllArgsConstructor
    public static class Response {
        private String status;
        private String message;
        private List<?> data;

        public Response(String status, String message){
            this.status = status;
            this.message = message;
            this.data = new ArrayList<>();
        }
    }

    @Builder
    @Data
    public static class TemplateResponse {
        private Long template_id;
        private String category;
        private String couponName;
        private Integer couponValue;
        private Integer expiredPeriod;
        private String description;
        private String imgUrl;

        public static TemplateResponse fromEntity(CouponTemplate couponTemplate) {
            return TemplateResponse.builder()
                    .template_id(couponTemplate.getId())
                    .category(couponTemplate.getCategory().getCategory())
                    .couponName(couponTemplate.getCouponName())
                    .couponValue(couponTemplate.getCouponValue())
                    .expiredPeriod(couponTemplate.getExpiredPeriod())
                    .description(couponTemplate.getDescription())
                    .imgUrl(couponTemplate.getImgUrl())
                    .build();
        }
    }
}
