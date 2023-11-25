package com.rup.web.dto.response;

import com.rup.domain.Promise;
import lombok.*;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

public class PromiseResponseDto {


    @Builder
    @Getter
    @AllArgsConstructor(access = AccessLevel.PRIVATE)
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    public static class PromiseSummaryResponseDto {
        private Long id;
        private String name;
        private LocalDateTime promiseTime;
        private Long penalty;
        private String inviteCode;
        private String authorName;
        private String createdAt;

        public static PromiseSummaryResponseDto of(Promise promise) {
            return PromiseSummaryResponseDto.builder()
                    .id(promise.getId())
                    .name(promise.getName())
                    .promiseTime(promise.getPromiseTime())
                    .penalty(promise.getPenalty())
                    .inviteCode(promise.getInviteCode())
                    .authorName(promise.getAuthor().getName())
                    .createdAt(promise.getCreatedAt().toString())
                    .build();
        }
    }

    @Builder
    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class PromiseDetailResponseDto {
        private Long id;
        private String name;
        private LocalDateTime promiseTime;
        private String promiseDate;
        private String promiseHour;
        private Long leftDate;
        private Long leftHour;
        private Long leftMinute;
        private Long penalty;
        private String address;
        private String inviteCode;
        private String authorName;
        private String createdAt;

        public static PromiseDetailResponseDto of(Promise promise) {
            Long day = ChronoUnit.DAYS.between(LocalDateTime.now(), promise.getPromiseTime());
            Long hour = ChronoUnit.HOURS.between(LocalDateTime.now(), promise.getPromiseTime()) - day * 24;
            Long minute = ChronoUnit.MINUTES.between(LocalDateTime.now(), promise.getPromiseTime()) - day * 24 * 60 - hour * 60;
            return PromiseDetailResponseDto.builder()
                    .id(promise.getId())
                    .name(promise.getName())
                    .promiseTime(promise.getPromiseTime())
                    .promiseDate(promise.getPromiseTime().toLocalDate().toString())
                    .promiseHour(promise.getPromiseTime().toLocalTime().toString().substring(0, 2))
                    .leftDate(day)
                    .leftHour(hour)
                    .leftMinute(minute)
                    .penalty(promise.getPenalty())
                    .address(promise.getLocation().getAddress())
                    .inviteCode(promise.getInviteCode())
                    .authorName(promise.getAuthor().getName())
                    .createdAt(promise.getCreatedAt().toString())
                    .build();
        }
    }
}
