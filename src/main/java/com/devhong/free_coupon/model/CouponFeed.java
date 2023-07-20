package com.devhong.free_coupon.model;

import com.devhong.free_coupon.type.Category;
import lombok.*;
import javax.persistence.*;
import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class CouponFeed extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long partner_id;

    @Enumerated(EnumType.STRING)
    private Category category;

    private String couponName;

    private Long couponValue;

    private Long expiredPeriod;

    @Column(columnDefinition = "TEXT")
    private String description;

    private String imgUrl;

    private Long amount;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "coupon_participants",joinColumns = @JoinColumn(name= "feed_id", referencedColumnName = "id"))
    private List<Long> participants;
}
