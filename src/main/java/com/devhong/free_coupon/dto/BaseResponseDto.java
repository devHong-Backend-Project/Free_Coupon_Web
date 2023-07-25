package com.devhong.free_coupon.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

public class BaseResponseDto {

    @Data
    @AllArgsConstructor
    public static class BaseResponse {
        private String status;
        private String message;
    }

    @Data
    @AllArgsConstructor
    public static class DataResponse {
        private String status;
        private String message;
        private List<?> data;

        public DataResponse(String status, String message){
            this.status = status;
            this.message = message;
            this.data = new ArrayList<>();
        }
    }
}
