package com.devhong.free_coupon.repository;

import com.devhong.free_coupon.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {
    Optional<User> findByName(@Param("name") String name);

    Optional<User> findById(@Param("id") Long id);

    boolean existsByNameOrMobileNumber(String name, String mobileNumber);
}
