package com.devhong.free_coupon.model;

import com.devhong.free_coupon.type.Category;
import lombok.*;
import javax.persistence.*;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class CouponFeed extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long partnerId;

    @Enumerated(EnumType.STRING)
    private Category category;

    private String couponName;

    private Integer couponValue;

    private Integer expiredPeriod;

    @Column(columnDefinition = "TEXT")
    private String description;

    private String imgUrl;

    private Integer amount;
}
