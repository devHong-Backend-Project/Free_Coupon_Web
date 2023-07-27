package com.devhong.free_coupon;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class FreeCouponApplication {

    public static void main(String[] args) {
        SpringApplication.run(FreeCouponApplication.class, args);
    }

}
