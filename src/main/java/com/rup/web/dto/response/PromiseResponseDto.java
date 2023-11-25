package com.rup.web.dto.response;

import com.rup.domain.Promise;
import lombok.*;

import java.time.LocalDateTime;

public class PromiseResponseDto {


    @Builder
    @Getter
    @AllArgsConstructor(access = AccessLevel.PRIVATE)
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    public static class PromiseSummaryResponseDto {
        private String name;
        private LocalDateTime promiseTime;
        private Long penalty;
        private String inviteCode;
        private String authorName;
        private String createdAt;

        public static PromiseSummaryResponseDto of(Promise promise) {
            return PromiseSummaryResponseDto.builder()
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
    @AllArgsConstructor(access = AccessLevel.PRIVATE)
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    public static class PromiseDetailResponseDto {
        //세부정보 모두 반환
    }
}
