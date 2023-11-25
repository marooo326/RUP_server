package com.rup.web.controller;

import com.rup.annotation.AuthMember;
import com.rup.apiPayload.response.ResponseDto;
import com.rup.domain.Promise;
import com.rup.service.PromiseService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.rup.web.dto.request.PromiseRequestDto.LocationUpdateDto;
import static com.rup.web.dto.request.PromiseRequestDto.PromiseCreateDto;
import static com.rup.web.dto.response.MemberResponseDto.LocationResponseDto;
import static com.rup.web.dto.response.PromiseResponseDto.PromiseDetailResponseDto;
import static com.rup.web.dto.response.PromiseResponseDto.PromiseSummaryResponseDto;

@RestController
@RequestMapping("/v1/promises")
@RequiredArgsConstructor
public class PromiseController {
    private final PromiseService promiseService;

    @PostMapping
    public ResponseDto<PromiseSummaryResponseDto> createPromise(@AuthMember Long memberId, @RequestBody PromiseCreateDto createDto) {
        Promise promise = promiseService.createPromise(memberId, createDto);
        return ResponseDto.of(PromiseSummaryResponseDto.of(promise));
    }

    @PostMapping("/participate")
    public ResponseDto<PromiseSummaryResponseDto> participateInPromise(@AuthMember Long memberId, @RequestBody String inviteCode) {
        Promise promise = promiseService.participateInPromise(memberId, inviteCode);
        return ResponseDto.of(PromiseSummaryResponseDto.of(promise));
    }

    @GetMapping("/")
    public ResponseDto<List<PromiseSummaryResponseDto>> getPromises(@AuthMember Long memberId) {
        List<Promise> promise = promiseService.getAllPromises(memberId);
        return ResponseDto.of(promise.stream().map(PromiseSummaryResponseDto::of).toList());
    }

    @GetMapping("/{promiseId}")
    public ResponseDto<PromiseSummaryResponseDto> getPromise(@AuthMember Long memberId, @PathVariable Long promiseId) {
        Promise promise = promiseService.getPromise(memberId, promiseId);
        return ResponseDto.of(PromiseDetailResponseDto.of(promise));
    }

    @PostMapping("/{promiseId}/locations")
    public ResponseDto<List<LocationResponseDto>> updateLocation(
            @AuthMember Long memberId,
            @PathVariable Long promiseId,
            @RequestBody LocationUpdateDto updateDto) {
        // 자신의 위치를 업데이트 할 때, 반환값으로 나머지 멤버들 위치 전부 넘겨 주기
        List<LocationResponseDto> allLocations = promiseService.updateLocation(memberId, promiseId, updateDto);
        return ResponseDto.of(allLocations);
    }

}
