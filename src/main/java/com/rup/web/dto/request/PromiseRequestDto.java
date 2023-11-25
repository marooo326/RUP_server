package com.rup.web.dto.request;

import lombok.Getter;

import java.time.LocalDateTime;

public class PromiseRequestDto {
    @Getter
    public static class PromiseCreateDto {
        private String name;
        private LocalDateTime promiseTime;
        private Long penalty;
        private String longitude;
        private String latitude;
        private String address;
    }

    @Getter
    public static class LocationUpdateDto {
        private String longitude;
        private String latitude;
    }
}
