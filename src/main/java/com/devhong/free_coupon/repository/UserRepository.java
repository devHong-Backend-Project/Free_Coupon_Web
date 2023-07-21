package com.devhong.free_coupon.repository;

import com.devhong.free_coupon.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {
    @Query("select u from User u join fetch u.roles where u.name = :name")
    Optional<User> findByName(@Param("name") String name);

    @Query("select u from User u join fetch u.roles where u.id = :id")
    Optional<User> findById(@Param("id") Long id);

    boolean existsByName(String name);

    boolean existsByMobileNumber(String mobileNumber);
}
