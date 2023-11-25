package com.rup.service;

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
        Promise promise = Promise.builder()
                .name(createDto.getName())
                .promiseTime(createDto.getPromiseTime())
                .penalty(createDto.getPenalty())
                .location(location)
                .author(memberService.findMember(memberId))
                .inviteCode(createRandomCode())
                .build();

        return promiseRepository.save(promise);
    }

    public Promise participateInPromise(Long memberId, String inviteCode) {
        Promise getPromise = promiseRepository.findByInviteCode(inviteCode);
        Member getMember = memberService.findMember(memberId);
        PromiseMember promiseMember = builderPromiseMember(getMember, getPromise);
        promiseMemberRepository.save(promiseMember);
        return getPromise;
    }


    public List<Promise> getAllPromises(Long memberId) {
        Member member = memberRepository.findById(memberId).orElseThrow(() -> new IllegalArgumentException("존재하지 않는 회원입니다."));
        return promiseRepository.findAllByAuthor(member);
    }

    public Promise getPromise(Long memberId, Long promiseId) {
        return promiseRepository.findById(promiseId).orElseThrow(() -> new IllegalArgumentException("존재하지 않는 약속입니다."));
    }

    public List<MemberResponseDto.LocationResponseDto> updateLocation(Long memberId,
                                                                      Long promiseId,
                                                                      PromiseRequestDto.LocationUpdateDto updateDto) {
        Member member = memberRepository.findById(memberId).orElseThrow(() -> new IllegalArgumentException("존재하지 않는 회원입니다."));
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


}
