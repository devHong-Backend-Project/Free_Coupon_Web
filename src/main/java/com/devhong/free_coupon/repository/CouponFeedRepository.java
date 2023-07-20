package com.devhong.free_coupon.repository;

import com.devhong.free_coupon.model.CouponFeed;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CouponFeedRepository extends JpaRepository<CouponFeed, Long> {
}
