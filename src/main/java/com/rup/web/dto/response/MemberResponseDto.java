package com.rup.web.dto.response;

import lombok.Builder;
import lombok.Getter;


public class MemberResponseDto {

    @Builder
    @Getter
    public static class MemberInfoResponseDto {
        private String name;
        private Long point;
        //MBTI추가
    }
}
