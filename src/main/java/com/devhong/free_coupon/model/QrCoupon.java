package com.devhong.free_coupon.model;

import javax.persistence.*;
import java.time.LocalDate;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(indexes = {
        @Index(name = "partner_idx", columnList = "partnerId"),
        @Index(name = "user_idx", columnList = "userId")})
public class QrCoupon extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String uuid;

    private Long partnerId;

    private Long userId;

    private String coupon_name;

    private String qr_url;

    private LocalDate expired_date;

    private boolean is_used = false;

}
