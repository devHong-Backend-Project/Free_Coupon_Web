package com.devhong.free_coupon.model;

import javax.persistence.*;
import java.time.LocalDateTime;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(indexes = {
        @Index(name = "partner_idx", columnList = "partner_id"),
        @Index(name = "user_idx", columnList = "user_id")})
public class QrCoupon extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long partner_id;

    @ManyToOne(fetch =FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false, foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT))
    private User user;

    private String coupon_name;

    private String qr_url;

    private LocalDateTime expired_date;

    private boolean is_used;

}
