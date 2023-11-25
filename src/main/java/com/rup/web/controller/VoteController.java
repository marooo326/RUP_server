package com.rup.web.controller;

import com.rup.apiPayload.response.ResponseDto;
import com.rup.service.VoteService;
import com.rup.web.dto.response.MemberResponseDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class VoteController {
    private final VoteService voteService;

    @Operation(summary = "지각자에 대한 투표 생성 API", description = "지각자에 대한 투표 생성 API")
    @ApiResponses({
            @ApiResponse(responseCode = "2000", description = "OK 성공"),
    })
    @PostMapping("/vote/newVote/{promiseId}/member/{memberId}")
    public ResponseDto<MemberResponseDto.basicResponseDto> newVote(@PathVariable(name = "promiseId") Long promiseId,
                                                                   @PathVariable(name = "memberId") Long memberId) {
        voteService.newVote(promiseId, memberId);
        return ResponseDto.of(MemberResponseDto.basicResponseDto.builder()
                .basicResponse(true)
                .build());
    }
}
