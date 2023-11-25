package com.rup.service;

import com.rup.domain.Member;
import com.rup.domain.Promise;
import com.rup.domain.enums.PromiseMemberStatus;
import com.rup.domain.mapping.PromiseMember;
import com.rup.repository.PromiseMemberRepository;
import com.rup.repository.PromiseRepository;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PromiseService {
    private final PromiseRepository promiseRepository;
    private final PromiseMemberRepository promiseMemberRepository;
    private final MemberService memberService;
    private final int randomCodeLenght = 6;

    private String createRandomCode() {
        return RandomStringUtils.randomNumeric(randomCodeLenght);
    }

    public Promise participateInPromise(Long memberId, String inviteCode) {
        Promise getPromise = promiseRepository.findByInviteCode(inviteCode);
        Member getMember = memberService.findMember(memberId);
        PromiseMember promiseMember = builderPromiseMember(getMember, getPromise);
        promiseMemberRepository.save(promiseMember);
        return getPromise;
    }

    private static PromiseMember builderPromiseMember(Member getMember, Promise getPromise) {
        PromiseMember promiseMember = PromiseMember.builder()
                .member(getMember)
                .promise(getPromise)
                .status(PromiseMemberStatus.NOT_ARRIVED)
                .build();
        return promiseMember;
    }
}
