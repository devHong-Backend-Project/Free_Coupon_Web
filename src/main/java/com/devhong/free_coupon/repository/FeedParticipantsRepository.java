package com.devhong.free_coupon.repository;

import com.devhong.free_coupon.model.FeedParticipants;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FeedParticipantsRepository extends JpaRepository<FeedParticipants,Long> {
    @Query("select p.userId from FeedParticipants p where p.feedId = :feedId ")
    List<Long> findUserIdByFeedId(@Param("feedId")Long feedId);
}
