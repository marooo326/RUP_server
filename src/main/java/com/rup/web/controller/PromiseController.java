package com.rup.web.controller;

import com.rup.apiPayload.response.ResponseDto;
import com.rup.domain.Promise;
import com.rup.service.PromiseService;
import com.rup.web.dto.request.PromiseRequestDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.rup.web.dto.request.PromiseRequestDto.LocationUpdateDto;
import static com.rup.web.dto.request.PromiseRequestDto.PromiseCreateDto;
import static com.rup.web.dto.response.MemberResponseDto.LocationResponseDto;
import static com.rup.web.dto.response.MemberResponseDto.MemberDetailResponseDto;
import static com.rup.web.dto.response.PromiseResponseDto.PromiseDetailResponseDto;
import static com.rup.web.dto.response.PromiseResponseDto.PromiseSummaryResponseDto;

@RestController
@RequestMapping("/v1/promises")
@RequiredArgsConstructor
public class PromiseController {
    private final PromiseService promiseService;

    @Operation(summary = "약속 생성", description = "약속 생성 API 입니다.")
    @ApiResponses({
            @ApiResponse(responseCode = "2000", description = "OK 성공"),
    })
    @PostMapping
    public ResponseDto<PromiseSummaryResponseDto> createPromise(@RequestBody PromiseCreateDto createDto) {
        Promise promise = promiseService.createPromise(getMemberId(), createDto);
        return ResponseDto.of(PromiseSummaryResponseDto.of(promise));
    }

    @Operation(summary = "약속 참여 API", description = "약속참여 API 입니다. Body에 inviteCode를 넣어주시면 됩니다.")
    @ApiResponses({
            @ApiResponse(responseCode = "2000", description = "OK 성공"),
    })
    @PostMapping("/participate")
    public ResponseDto<PromiseSummaryResponseDto> participateInPromise(@RequestBody PromiseRequestDto.participateInPromiseDto inviteCode) {
        Promise promise = promiseService.participateInPromise(getMemberId(), inviteCode.getInviteCode());
        return ResponseDto.of(PromiseSummaryResponseDto.of(promise));
    }

    @Operation(summary = "본인 약속 전체 조회 API", description = "약속 전체조회 API입니다. 자신이 참여한 약속 전체를 조회합니다.")
    @ApiResponses({
            @ApiResponse(responseCode = "2000", description = "OK 성공"),
    })
    @GetMapping("/")
    public ResponseDto<List<PromiseSummaryResponseDto>> getPromises() {
        List<Promise> promise = promiseService.getAllPromises(getMemberId());
        return ResponseDto.of(promise.stream().map(PromiseSummaryResponseDto::of).toList());
    }

    @Operation(summary = "약속 단건 조회 API", description = "약속 단건조회 API입니다. 자신이 참여한 약속 중 하나를 조회합니다.")
    @ApiResponses({
            @ApiResponse(responseCode = "2000", description = "OK 성공"),
    })
    @GetMapping("/{promiseId}")
    public ResponseDto<PromiseDetailResponseDto> getPromise(@PathVariable Long promiseId) {
        Promise promise = promiseService.getPromise(getMemberId(), promiseId);
        return ResponseDto.of(PromiseDetailResponseDto.of(promise));
    }

    @Operation(summary = "위치 업데이트 API", description = "위치를 업데이트하는 API입니다. Body에 위도, 경도, 주소를 넣어주시면 본인 위치를 업데이트하고, 나머지 멤버들의 위치를 반환합니다.")
    @ApiResponses({
            @ApiResponse(responseCode = "2000", description = "OK 성공"),
    })
    @PostMapping("/{promiseId}/locations")
    public ResponseDto<List<LocationResponseDto>> updateLocation(
            @PathVariable Long promiseId,
            @RequestBody LocationUpdateDto updateDto) {
        // 자신의 위치를 업데이트 할 때, 반환값으로 나머지 멤버들 위치 전부 넘겨 주기
        List<LocationResponseDto> allLocations = promiseService.updateLocation(getMemberId(), promiseId, updateDto);
        return ResponseDto.of(allLocations);
    }

    @Operation(summary = "약속 완료 API", description = "약속을 완료시키는 API입니다. 약속을 완료시키고, 지각자들의 정보를 반환합니다. 해당 정보로 투표 진행하시면 됩니다.")
    @ApiResponses({
            @ApiResponse(responseCode = "2000", description = "OK 성공"),
    })
    @PostMapping("/{promiseId}/complete")
    public ResponseDto<List<MemberDetailResponseDto>> completePromise(@PathVariable Long promiseId) {
        return ResponseDto.of(promiseService.completePromise(promiseId));
    }

    @Operation(summary = "약속 삭제 API", description = "약속을 삭제하시면 됩니다.)")
    @ApiResponses({
            @ApiResponse(responseCode = "2000", description = "OK 성공"),
    })
    @DeleteMapping("/{promiseId}")
    public ResponseDto<Void> deletePromise(@PathVariable Long promiseId) {
        promiseService.deletePromise(promiseId);
        return ResponseDto.of(null);
    }

    private Long getMemberId() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return Long.parseLong(authentication.getName().toString());
    }


    //삭제
    //수정 1시간 전에만


}
