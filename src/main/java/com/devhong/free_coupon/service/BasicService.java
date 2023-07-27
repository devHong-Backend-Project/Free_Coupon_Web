package com.devhong.free_coupon.service;

import com.devhong.free_coupon.dto.CouponDto;
import com.devhong.free_coupon.model.CouponFeed;
import com.devhong.free_coupon.repository.CouponFeedRepository;
import com.devhong.free_coupon.repository.FeedParticipantRepository;
import com.devhong.free_coupon.type.Category;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BasicService {

    private final CouponFeedRepository couponFeedRepository;
    private final FeedParticipantRepository feedParticipantRepository;

    @Cacheable(key = "#category.category", value = "category")
    public List<CouponDto.CouponInfo> getCouponListByCategory(CouponDto.CategoryRequest category) {
        List<CouponFeed> couponFeedList = couponFeedRepository.findByAmountGreaterThanAndCategory(0,Category.fromString(category.getCategory()));

        return getCouponInfos(couponFeedList);
    }

    public List<CouponDto.CouponInfo> searchCouponName(String couponName) {
        List<CouponFeed> couponFeedList = couponFeedRepository.findByAmountGreaterThanAndCouponNameContaining(0, couponName);

        return getCouponInfos(couponFeedList);
    }

    private List<CouponDto.CouponInfo> getCouponInfos(List<CouponFeed> couponFeedList) {
        return couponFeedList.stream().map(feed -> {
            Long count = feedParticipantRepository.countUserIdByFeedId(feed.getId());
            CouponDto.CouponInfo couponInfo = CouponDto.CouponInfo.fromEntity(feed);
            couponInfo.setParticipantCount(count);
            return couponInfo;
        }).collect(Collectors.toList());
    }
}
