package com.rup.web.controller;

import com.rup.annotation.AuthMember;
import com.rup.apiPayload.response.ResponseDto;
import com.rup.domain.Promise;
import com.rup.service.PromiseService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.rup.web.dto.response.PromiseResponseDto.PromiseSummaryResponseDto;

@RestController
@RequestMapping("/v1/promises")
@RequiredArgsConstructor
public class PromiseController {
    private final PromiseService promiseService;

    @PostMapping("/participate")
    public ResponseDto<PromiseSummaryResponseDto> participateInPromise(@AuthMember Long memberId, @RequestBody String inviteCode) {
        Promise promise = promiseService.participateInPromise(memberId, inviteCode);
        return ResponseDto.of(PromiseSummaryResponseDto.of(promise));
    }
}
