package com.rup.web.dto.response;

import com.rup.domain.Keyword;
import com.rup.domain.Member;
import com.rup.domain.enums.MemberStatus;
import com.rup.domain.enums.MemberType;
import lombok.*;

import java.util.List;


public class MemberResponseDto {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;
//
//    @Column(nullable = false)
//    private String name;
//
//    @Column(nullable = false)
//    private Long point;
//
//    @Column(nullable = false)
//    @Enumerated(EnumType.STRING)
//    private MemberType type;

    //    @Enumerated(EnumType.STRING)
//    @Column(name = "user_role", nullable = false)
//    @ColumnDefault("'USER'")
//    private UserRole userRole;
//
//    @Column(name = "oauth_id", unique = true, nullable = false)
//    private String OAuthId;
//
//    //    @Column(nullable = false)
//    @Enumerated(EnumType.STRING)
//    private MemberStatus status;
    @Builder
    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class MemberDetailResponseDto {
        private Long id;
        private String name;
        private Long point;
        private MemberType type;
        private MemberStatus status;

        public static MemberDetailResponseDto of(Member member) {
            return MemberDetailResponseDto.builder()
                    .id(member.getId())
                    .name(member.getName())
                    .point(member.getPoint())
                    .type(member.getType())
                    .status(member.getStatus())
                    .build();
        }
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

    @Builder
    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class LoginMember {
        private String accessToken;
    }

    @Builder
    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class existsInfo {
        private boolean isExist;
    }

    @Builder
    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class basicResponseDto {
        private boolean basicResponse;
    }

    @Builder
    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class keywordDto {
        private List<Keyword> keywords;
    }
}
