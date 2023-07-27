package com.devhong.free_coupon.service;

import com.devhong.free_coupon.dto.CouponDto;
import com.devhong.free_coupon.model.CouponFeed;
import com.devhong.free_coupon.repository.CouponFeedRepository;
import com.devhong.free_coupon.type.Category;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BasicService {

    private final CouponFeedRepository couponFeedRepository;

    public List<CouponDto.CouponInfo> getCouponListByCategory(CouponDto.CategoryRequest category) {
        List<CouponFeed> couponFeedList = couponFeedRepository.findByAmountGreaterThanAndCategory(0,Category.fromString(category.getCategory()));

        return couponFeedList.stream().map(CouponDto.CouponInfo::fromEntity).collect(Collectors.toList());

    }

    public List<CouponDto.CouponInfo> searchCouponName(String couponName) {
        List<CouponFeed> couponFeedList = couponFeedRepository.findByAmountGreaterThanAndCouponNameContaining(0, couponName);

        return couponFeedList.stream().map(CouponDto.CouponInfo::fromEntity).collect(Collectors.toList());
    }
}
