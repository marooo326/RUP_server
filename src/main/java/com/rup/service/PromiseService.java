package com.rup.service;

import com.rup.apiPayload.code.ErrorStatus;
import com.rup.apiPayload.exception.handler.PromiseExceptionHandler;
import com.rup.domain.Location;
import com.rup.domain.Member;
import com.rup.domain.Promise;
import com.rup.domain.enums.PromiseMemberStatus;
import com.rup.domain.mapping.PromiseMember;
import com.rup.repository.MemberRepository;
import com.rup.repository.PromiseMemberRepository;
import com.rup.repository.PromiseRepository;
import com.rup.web.dto.request.PromiseRequestDto;
import com.rup.web.dto.response.MemberResponseDto;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.rup.web.dto.request.PromiseRequestDto.PromiseCreateDto;

@Service
@Transactional
@RequiredArgsConstructor
public class PromiseService {
    private final int RANDOM_CODE_LENGTH = 6;

    private final PromiseRepository promiseRepository;
    private final MemberRepository memberRepository;
    private final PromiseMemberRepository promiseMemberRepository;
    private final MemberService memberService;

    public Promise createPromise(Long memberId, PromiseCreateDto createDto) {
        Location location = Location.builder()
                .address(createDto.getAddress())
                .longitude(createDto.getLongitude())
                .latitude(createDto.getLatitude())
                .build();
        Member member = memberService.findMember(memberId);
        Promise promise = Promise.builder()
                .name(createDto.getName())
                .promiseTime(createDto.getPromiseTime())
                .penalty(createDto.getPenalty())
                .location(location)
                .author(member)
                .inviteCode(createRandomCode())
                .build();

        return promiseRepository.save(promise);
    }

    public Promise participateInPromise(Long memberId, String inviteCode) {
        System.out.println("inviteCode = " + inviteCode);
        Promise promise = promiseRepository.findByInviteCode(inviteCode).orElseThrow(() -> new PromiseExceptionHandler(ErrorStatus._PROMISE_NOT_FOUND_EXCEPTION));
        Member member = memberService.findMember(memberId);
        PromiseMember promiseMember = builderPromiseMember(member, promise);
        promiseMemberRepository.save(promiseMember);
        return promise;
    }


    public List<Promise> getAllPromises(Long memberId) {
        Member member = memberRepository.findById(memberId).orElseThrow(() -> new PromiseExceptionHandler(ErrorStatus._PROMISE_NOT_FOUND_EXCEPTION));
        return promiseRepository.findAllByAuthor(member);
    }

    public Promise getPromise(Long memberId, Long promiseId) {
        return promiseRepository.findById(promiseId).orElseThrow(() -> new PromiseExceptionHandler(ErrorStatus._PROMISE_NOT_FOUND_EXCEPTION));
    }

    public List<MemberResponseDto.LocationResponseDto> updateLocation(Long memberId,
                                                                      Long promiseId,
                                                                      PromiseRequestDto.LocationUpdateDto updateDto) {
        Member member = memberRepository.findById(memberId).orElseThrow(() -> new PromiseExceptionHandler(ErrorStatus._MEMBER_NOT_FOUND_EXCEPTION));
        Location location = Location.builder()
                .longitude(updateDto.getLongitude())
                .latitude(updateDto.getLatitude())
                .build();
        member.updateLocation(location);
        List<PromiseMember> promiseMembers = promiseMemberRepository.findAllByPromiseId(promiseId);
        return promiseMembers.stream().map(promiseMember ->
                MemberResponseDto.LocationResponseDto.of(promiseMember.getMember())
        ).toList();
    }

    private static PromiseMember builderPromiseMember(Member getMember, Promise getPromise) {
        PromiseMember promiseMember = PromiseMember.builder()
                .member(getMember)
                .promise(getPromise)
                .status(PromiseMemberStatus.NOT_ARRIVED)
                .build();
        return promiseMember;
    }

    private String createRandomCode() {
        return RandomStringUtils.randomNumeric(RANDOM_CODE_LENGTH);
    }

    public List<MemberResponseDto.MemberDetailResponseDto> completePromise(Long promiseId) {
        Promise promise = promiseRepository.findById(promiseId).orElseThrow(() -> new PromiseExceptionHandler(ErrorStatus._PROMISE_NOT_FOUND_EXCEPTION));
        promise.complete();
        List<PromiseMember> latePromiseMember = promise.getPromiseMembers().stream()
                .filter(promiseMember -> promiseMember.getStatus() == PromiseMemberStatus.NOT_ARRIVED)
                .toList();
        return latePromiseMember.stream().map(promiseMember ->
                MemberResponseDto.MemberDetailResponseDto.of(promiseMember.getMember())).toList();
    }

    public void completeMember(Long memberId, Long promiseId) {
        PromiseMember promiseMember = promiseMemberRepository.findByMemberIdAndPromiseId(memberId, promiseId).orElseThrow(() -> new PromiseExceptionHandler(ErrorStatus._PROMISE_NOT_FOUND_EXCEPTION));
        promiseMember.complete();
    }

    public void deletePromise(Long promiseId) {
        promiseRepository.deleteById(promiseId);
    }


}
