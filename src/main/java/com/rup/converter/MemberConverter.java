package com.rup.converter;

import com.rup.domain.Member;
import com.rup.web.dto.response.MemberResponseDto;
import org.springframework.stereotype.Component;

@Component
public class MemberConverter {
    public MemberResponseDto.MemberInfoResponseDto memberEntityToMemberInfoDto(Member member) {
        return MemberResponseDto.MemberInfoResponseDto.builder()
                .name(member.getName())
                .point(member.getPoint())
                .build();
    }
}
