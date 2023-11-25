package com.rup.web.dto.request;

import lombok.Builder;
import lombok.Getter;

public class MemberRequestDto {

    @Builder
    @Getter
    public static class kakaoMember {
        private String kakaoId;
    }
}
