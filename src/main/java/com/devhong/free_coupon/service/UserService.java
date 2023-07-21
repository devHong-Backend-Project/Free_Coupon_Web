package com.devhong.free_coupon.service;

import com.devhong.free_coupon.exception.CustomErrorCode;
import com.devhong.free_coupon.exception.CustomException;
import com.devhong.free_coupon.model.FeedParticipants;
import com.devhong.free_coupon.model.User;
import com.devhong.free_coupon.repository.CouponFeedRepository;
import com.devhong.free_coupon.repository.FeedParticipantsRepository;
import com.devhong.free_coupon.repository.UserRepository;
import com.devhong.free_coupon.security.TokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final FeedParticipantsRepository feedParticipantsRepository;

    private final TokenProvider tokenProvider;
    private final CouponFeedRepository couponFeedRepository;

    /*
        쿠폰 응모하기
        - validateParticipation :
            유저 유무확인 -> 유저 ticketAmount 확인 -> 피드 유무 확인
        - FeedParticipants에 응모기록 추가
        - 유저 ticketAmount 변경
     */
    @Transactional
    public void participateFeed(String jwtHeader, Long feedId) {
        Long userId = tokenProvider.getUserIdFromHeader(jwtHeader);

        User user = validateParticipation(feedId, userId);

        feedParticipantsRepository.save(FeedParticipants.builder()
                        .userId(userId)
                        .feedId(feedId)
                .build());

        user.useTicket();
    }

    private User validateParticipation(Long feedId, Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new CustomException(CustomErrorCode.USER_NOT_FOUND));
        if (user.getTicketAmount() == 0){
            throw new CustomException(CustomErrorCode.NO_TICKETS_AVAILABLE);
        }
        if(!couponFeedRepository.existsById(feedId)){
            throw new CustomException(CustomErrorCode.FEED_NOT_FOUND);
        }

        return user;
    }
}
