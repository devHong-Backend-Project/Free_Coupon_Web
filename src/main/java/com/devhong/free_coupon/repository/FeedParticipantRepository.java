package com.devhong.free_coupon.repository;

import com.devhong.free_coupon.model.FeedParticipant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FeedParticipantRepository extends JpaRepository<FeedParticipant,Long> {
    @Query("select f.userId from FeedParticipant f where f.feedId = :feedId")
    List<Long> findUserIdByFeedId(@Param("feedId") Long feedId);
}
