package com.devhong.free_coupon.model;

import javax.persistence.*;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(indexes = @Index(name = "feed_idx", columnList = "feedId"))
public class FeedParticipants {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long feedId;

    private Long userId;

}
