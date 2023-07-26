package com.devhong.free_coupon.repository;

import com.devhong.free_coupon.model.FeedParticipants;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FeedParticipantsRepository extends JpaRepository<FeedParticipants,Long> {
}
