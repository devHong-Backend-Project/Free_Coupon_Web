package com.devhong.free_coupon.repository;

import com.devhong.free_coupon.model.Partner;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PartnerRepository extends JpaRepository<Partner, Long> {
    @Query("select p from Partner p join fetch p.roles")
    Optional<Partner> findByName(String name);

    boolean existsByName(String name);

    boolean existsByBusinessNumber(String businessNumber);
}
