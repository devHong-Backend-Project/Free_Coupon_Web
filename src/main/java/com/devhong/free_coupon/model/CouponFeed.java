package com.devhong.free_coupon.model;

import com.devhong.free_coupon.type.Category;
import lombok.*;
import javax.persistence.*;
import java.util.ArrayList;
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

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "feedId")
    private List<FeedParticipants> participants = new ArrayList<>();
}
