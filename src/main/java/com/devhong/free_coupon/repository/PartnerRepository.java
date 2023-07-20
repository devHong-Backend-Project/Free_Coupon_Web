package com.devhong.free_coupon.repository;

import com.devhong.free_coupon.model.CouponTemplate;
import com.devhong.free_coupon.model.Partner;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PartnerRepository extends JpaRepository<Partner, Long> {
    @Query("select p from Partner p join fetch p.roles WHERE p.name = :name")
    Optional<Partner> findByName(@Param("name") String name);

    @Query("select p from Partner p join fetch p.roles WHERE p.id = :id")
    Optional<Partner> findById(@Param("id") Long id);

    @Query("SELECT p.templates FROM Partner p WHERE p.id = :partnerId")
    List<CouponTemplate> findTemplatesByPartnerId(@Param("partnerId") Long partnerId);

    boolean existsByName(String name);

    boolean existsByBusinessNumber(String businessNumber);
}
