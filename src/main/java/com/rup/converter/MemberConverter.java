package com.rup.converter;

import com.rup.domain.Member;
import com.rup.web.dto.response.MemberResponseDto;
import org.springframework.stereotype.Component;

@Component
public class MemberConverter {
    public MemberResponseDto.MemberDetailResponseDto memberEntityToMemberInfoDto(Member member) {
        return MemberResponseDto.MemberDetailResponseDto.builder()
                .name(member.getName())
                .point(member.getPoint())
                .build();
    }
}
