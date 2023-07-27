package com.devhong.free_coupon.repository;

import com.devhong.free_coupon.model.CouponFeed;
import com.devhong.free_coupon.type.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CouponFeedRepository extends JpaRepository<CouponFeed, Long> {
    Optional<CouponFeed> findById(Long feed_id);

    List<CouponFeed> findByAmountGreaterThanAndCategory(Integer amount, Category category);

    List<CouponFeed> findByAmountGreaterThanAndCouponNameContaining(Integer amount, String couponName);

    List<CouponFeed> findAllByAmountGreaterThan(Integer amount);
}
