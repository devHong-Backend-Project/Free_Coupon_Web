package com.devhong.free_coupon.repository;

import com.devhong.free_coupon.model.CouponTemplate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CouponTemplateRepository extends JpaRepository<CouponTemplate, Long> {
}
