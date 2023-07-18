package com.devhong.free_coupon.dto;

import com.devhong.free_coupon.model.Partner;
import com.devhong.free_coupon.model.User;
import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.util.List;

public class Auth {

    /*
     * 회원 로그인시 클라이언트 request Dto
     */
    @Data
    public static class SignIn {
        @NotBlank
        private String name;
        @NotBlank
        private String password;
        @NotBlank
        private String userType;
    }

    /*
     * 일반회원 가입시 클라이언트 request Dto
     */
    @Data
    public static class SignUpUser {
        @NotBlank
        private String name;
        @NotBlank
        private String password;
        @NotEmpty
        private List<String> roles;
        @NotBlank
        private String email;
        @NotBlank
        private String mobileNumber;

        public User toEntity() {
                return User.builder()
                        .name(name)
                        .password(password)
                        .email(email)
                        .mobileNumber(mobileNumber)
                        .roles(roles)
                        .build();
        }
    }

    /*
     * 파트너회원 가입시 클라이언트 request Dto
     */
    @Data
    public static class SignUpPartner {
        @NotBlank
        private String name;
        @NotBlank
        private String password;
        @NotEmpty
        private List<String> roles;
        @NotBlank
        private String businessNumber;
        @NotBlank
        private String location;

        public Partner toEntity() {
            return Partner.builder()
                    .name(name)
                    .password(password)
                    .businessNumber(businessNumber)
                    .location(location)
                    .roles(roles)
                    .build();
        }
    }

}
