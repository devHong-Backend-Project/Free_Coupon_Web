package com.devhong.free_coupon.repository;

import com.devhong.free_coupon.model.CouponFeed;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CouponFeedRepository extends JpaRepository<CouponFeed, Long> {
    @Query("select f from CouponFeed f left join fetch f.participants where f.id = :feed_id")
    Optional<CouponFeed> findById(@Param("feed_id") Long feed_id);

    @Query("select f from CouponFeed f left join fetch f.participants where f.category = :category")
    List<CouponFeed> findByCategory(@Param("category") String category);
}
