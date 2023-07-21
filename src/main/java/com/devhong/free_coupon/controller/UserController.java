package com.devhong.free_coupon.controller;

import com.devhong.free_coupon.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RequiredArgsConstructor
@RequestMapping("/user")
@RestController
@PreAuthorize("hasRole('USER')")
public class UserController {

    private final UserService userService;

    /*
        쿠폰 응모하기 (특정 쿠폰 피드에 참여하기)
     */
    @PostMapping("/participate-feed/{feed_id}")
    public ResponseEntity<?> participateFeed(@PathVariable Long feed_id,
                                             @RequestHeader("Authorization") String jwtHeader) {
        userService.participateFeed(jwtHeader, feed_id);
        return ResponseEntity.ok("success");
    }
}
