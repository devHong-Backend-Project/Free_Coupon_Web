package com.devhong.free_coupon.repository;

import com.devhong.free_coupon.model.QrCoupon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface QrCouponRepository extends JpaRepository<QrCoupon, Long> {
    Optional<QrCoupon> findByUuid(String uuid);
}
