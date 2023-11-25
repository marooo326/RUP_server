package com.rup.web.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

public class PromiseRequestDto {
    @Builder
    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class PromiseCreateDto {
        private String name;
        private LocalDateTime promiseTime;
        private Long penalty;
        private String longitude;
        private String latitude;
        private String address;
    }

    @Builder
    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class LocationUpdateDto {
        private String longitude;
        private String latitude;
    }
}
