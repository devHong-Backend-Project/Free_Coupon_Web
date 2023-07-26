package com.devhong.free_coupon.scheduler;

import com.devhong.free_coupon.model.CouponFeed;
import com.devhong.free_coupon.model.QrCoupon;
import com.devhong.free_coupon.repository.CouponFeedRepository;
import com.devhong.free_coupon.repository.FeedParticipantRepository;
import com.devhong.free_coupon.repository.QrCouponRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.*;

@Slf4j
@Component
@EnableScheduling
@RequiredArgsConstructor
public class LotteryScheduler {

    private final CouponFeedRepository couponFeedRepository;
    private final FeedParticipantRepository feedParticipantsRepository;
    private final QrCouponRepository qrCouponRepository;

    @Value("${qr-api}")
    private String qrApi;
    @Value("${qr-server}")
    private String qrServer;

    @Scheduled(fixedDelay = 600000)
    public void lotteryScheduling() {

        List<CouponFeed> feeds = couponFeedRepository.findAllByAmountGreaterThan(0);

        if (feeds.isEmpty()) {
            log.info("Registered Feed Not Found");
            return;
        }

        for (CouponFeed feed : feeds) {
            drawWinner(feed);
        }
    }

    @Transactional
    public void drawWinner(CouponFeed feed) {
        List<Long> participants = feedParticipantsRepository.findUserIdByFeedId(feed.getId());

        if (participants.isEmpty()) return;

        List<Long> winners = shufflePicking(participants, feed.getAmount());

        winners.forEach(userId -> {
            String uuid = getCustomUUID();
            String qr_url = qrApi + qrServer + uuid + "/" + feed.getPartnerId();

            qrCouponRepository.save(QrCoupon.builder()
                    .partnerId(feed.getPartnerId())
                    .uuid(uuid)
                    .userId(userId)
                    .expired_date(LocalDate.now().plusDays(feed.getExpiredPeriod()))
                    .coupon_name(feed.getCouponName())
                    .qr_url(qr_url)
                    .build());
        });

        feed.useCouponAmount(winners.size());
        couponFeedRepository.save(feed);
    }

    private String getCustomUUID() {
        String uuid = UUID.randomUUID().toString();
        List<String> split = Arrays.asList(uuid.split("-"));
        return String.join("",split);
    }

    public List<Long> shufflePicking(List<Long> list, Integer n) {
        if (list.size() <= n) {
            return list;
        }

        List<Long> shuffledList = new ArrayList<>(list);
        Collections.shuffle(shuffledList);

        return shuffledList.subList(0, n);
    }

}
