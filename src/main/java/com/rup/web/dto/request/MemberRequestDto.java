package com.rup.web.dto.request;

import com.rup.domain.enums.MemberKeyword;
import com.rup.domain.enums.MemberType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

public class MemberRequestDto {

    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    @Getter
    public static class kakaoMember {
        private String kakaoId;
    }

    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    @Getter
    public static class signUpDto {
        private String kakaoId;
        private String nickname;
        @Enumerated(EnumType.STRING)
        private MemberType type;

        @Enumerated(EnumType.STRING)
        private MemberKeyword keyword;


    }

}
