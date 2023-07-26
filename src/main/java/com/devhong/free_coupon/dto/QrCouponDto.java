package com.devhong.free_coupon.dto;

import com.devhong.free_coupon.model.QrCoupon;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

public class QrCouponDto {

    @Data
    @Builder
    public static class QrCouponInfo {
        Long id;
        String coupon_name;
        LocalDate expired_date;

        public static QrCouponInfo fromEntity(QrCoupon qrCoupon) {
            return QrCouponInfo.builder()
                    .id(qrCoupon.getId())
                    .coupon_name(qrCoupon.getCoupon_name())
                    .expired_date(qrCoupon.getExpired_date())
                    .build();
        }
    }

}
