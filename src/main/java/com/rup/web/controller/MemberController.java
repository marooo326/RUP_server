package com.rup.web.controller;

import com.rup.apiPayload.response.ResponseDto;
import com.rup.converter.MemberConverter;
import com.rup.domain.Member;
import com.rup.service.MemberService;
import com.rup.web.dto.request.MemberRequestDto;
import com.rup.web.dto.response.MemberResponseDto;
import com.rup.web.dto.response.MemberResponseDto.MemberInfoResponseDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;


@RestController
@RequiredArgsConstructor
@RequestMapping("/member")
public class MemberController {
    private final MemberService memberService;
    private final MemberConverter memberConverter;

    @Operation(summary = "(임시)유저 정보 조회 API", description = "(임시)유저 정보 조회 API입니다.")
    @ApiResponses({
            @ApiResponse(responseCode = "2000", description = "OK 성공"),
    })
    @GetMapping("/{userId}")
    public ResponseDto<MemberInfoResponseDto> getMember(@PathVariable("userId") Long userId) {
        Member member = memberService.getMember(userId);
        MemberInfoResponseDto memberInfoResponseDto = memberConverter.memberEntityToMemberInfoDto(member);
        return ResponseDto.of(memberInfoResponseDto);
    }

    @Operation(summary = "존재하는 유저인지 확인 API", description = "db에 이미 존재하는 유저인지 확인하는 API입니다. kakaoId를 넣어주시면 됩니다.")
    @ApiResponses({
            @ApiResponse(responseCode = "2000", description = "OK 성공"),
    })
    @PostMapping("/exists")
    public ResponseDto<MemberResponseDto.existsInfo> isExistMember(@RequestBody MemberRequestDto.kakaoMember kakaoMember) {
        boolean isExists = memberService.existsMember(kakaoMember.getKakaoId());
        return ResponseDto.of(MemberResponseDto.existsInfo.builder()
                .isExist(isExists)
                .build());
    }

    @Operation(summary = "로그인 API", description = "로그인 API 입니다. kakaoId를 넣어주시면 됩니다.")
    @ApiResponses({
            @ApiResponse(responseCode = "2000", description = "OK 성공"),
    })
    @PostMapping("/login")
    public ResponseDto<MemberResponseDto.LoginMember> memberLogin(@RequestBody MemberRequestDto.kakaoMember kakaoMember) {
        return ResponseDto.of(memberService.login(kakaoMember.getKakaoId()));
    }

}
