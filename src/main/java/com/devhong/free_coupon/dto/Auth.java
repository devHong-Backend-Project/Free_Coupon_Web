package com.devhong.free_coupon.dto;

import com.devhong.free_coupon.model.Partner;
import com.devhong.free_coupon.model.User;
import lombok.*;

import javax.validation.constraints.NotBlank;

public class Auth {

    /*
     * 회원 로그인시 클라이언트 request Dto
     */
    @Data
    public static class SignIn {
        @NotBlank
        private String nickname;
        @NotBlank
        private String password;
        @NotBlank
        private String userType;
    }

    @Data
    @AllArgsConstructor
    public static class SignInResponse {
        private String status;
        private String message;
        private String token;
    }

    /*
     * 일반회원 가입시 클라이언트 request Dto
     */
    @Data
    public static class SignUpUser {
        @NotBlank
        private String nickname;
        @NotBlank
        private String password;
        @NotBlank
        private String email;
        @NotBlank
        private String mobileNumber;

        public User toEntity() {
                return User.builder()
                        .nickname(nickname)
                        .password(password)
                        .email(email)
                        .mobileNumber(mobileNumber)
                        .build();
        }
    }

    /*
     * 파트너회원 가입시 클라이언트 request Dto
     */
    @Data
    public static class SignUpPartner {
        @NotBlank
        private String nickname;
        @NotBlank
        private String password;
        @NotBlank
        private String businessNumber;
        @NotBlank
        private String location;

        public Partner toEntity() {
            return Partner.builder()
                    .nickname(nickname)
                    .password(password)
                    .businessNumber(businessNumber)
                    .location(location)
                    .build();
        }
    }

    @Data
    @AllArgsConstructor
    public static class SignUpResponse {
        private String status;
        private String message;
    }
}
