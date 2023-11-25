package com.rup.web.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class MemberRequestDto {

    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    @Getter
    public static class kakaoMember {
        private String kakaoId;
    }
}
