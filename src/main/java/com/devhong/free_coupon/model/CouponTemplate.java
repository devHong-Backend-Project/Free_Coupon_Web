package com.devhong.free_coupon.model;

import com.devhong.free_coupon.type.Category;

import javax.persistence.*;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(indexes = @Index(name = "partner_idx", columnList = "partner_id"))
public class CouponTemplate extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "partner_id", nullable = false, foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT))
    private Partner partner;

    @Enumerated(EnumType.STRING)
    private Category category;

    private String couponName;

    private Long couponValue;

    private Long expiredPeriod;

    private String description;

    private String imgUrl;

}
