package com.rup.web.dto.response;

import com.rup.domain.Member;
import lombok.*;


public class MemberResponseDto {

    @Builder
    @Getter
    public static class MemberInfoResponseDto {
        private String name;
        private Long point;
        //MBTI추가
    }

    @Builder
    @Getter
    @AllArgsConstructor(access = AccessLevel.PRIVATE)
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    public static class LocationResponseDto {
        private Long memberId;
        private String name;
        private String latitude;
        private String longitude;

        public static LocationResponseDto of(Member member) {
            return LocationResponseDto.builder()
                    .memberId(member.getId())
                    .name(member.getName())
                    .latitude(member.getLocation().getLatitude())
                    .longitude(member.getLocation().getLongitude())
                    .build();
        }
    }

    @Getter
    @Builder
    public static class LoginMember {
        private String accessToken;
    }

    @Getter
    @Builder
    public static class existsInfo {
        private boolean isExist;
    }
}
