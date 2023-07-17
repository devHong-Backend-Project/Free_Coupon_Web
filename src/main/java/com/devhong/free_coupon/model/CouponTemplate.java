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
public class CouponTemplate extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "partner_id", nullable = false, foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT))
    private Partner partner;

    @Enumerated(EnumType.STRING)
    private Category category;

    private String coupon_name;

    private Long coupon_value;

    private Long expired_period;

    private String description;

    private String img_url;

}
