package com.rup.web.controller;

import com.rup.apiPayload.response.ResponseDto;
import com.rup.converter.MemberConverter;
import com.rup.domain.Member;
import com.rup.service.MemberService;
import com.rup.web.dto.response.MemberResponseDto.MemberInfoResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequiredArgsConstructor
@RequestMapping("/member")
public class MemberController {
    private final MemberService memberService;
    private final MemberConverter memberConverter;

    @GetMapping("/{userId}")
    public ResponseDto<MemberInfoResponseDto> getMember(@PathVariable("userId") Long userId) {
        Member member = memberService.getMember(userId);
        MemberInfoResponseDto memberInfoResponseDto = memberConverter.memberEntityToMemberInfoDto(member);
        return ResponseDto.of(memberInfoResponseDto);
    }
    
}
