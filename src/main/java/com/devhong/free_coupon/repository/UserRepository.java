package com.devhong.free_coupon.repository;

import com.devhong.free_coupon.model.Partner;
import com.devhong.free_coupon.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {
    @Query("select u from User u join fetch u.roles where u.name = :name")
    Optional<Partner> findByName(@Param("name") String name);

    boolean existsByName(String name);

    boolean existsByMobileNumber(String mobileNumber);
}
