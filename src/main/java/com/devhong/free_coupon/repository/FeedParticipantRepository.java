package com.devhong.free_coupon.repository;

import com.devhong.free_coupon.model.FeedParticipant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FeedParticipantRepository extends JpaRepository<FeedParticipant,Long> {
}
