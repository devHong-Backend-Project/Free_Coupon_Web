package com.devhong.free_coupon.repository;

import com.devhong.free_coupon.model.CouponFeed;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CouponFeedRepository extends JpaRepository<CouponFeed, Long> {
    Optional<CouponFeed> findById(Long feed_id);

    List<CouponFeed> findByCategory(String category);

    List<CouponFeed> findAllByAmountGreaterThan(Integer amount);
}
