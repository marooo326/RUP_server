package com.rup.service;

import com.rup.apiPayload.code.ErrorStatus;
import com.rup.apiPayload.exception.handler.MemberExceptionHandler;
import com.rup.domain.Member;
import com.rup.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;

    public Long saveMember(Member member) {
        Member saveMember = memberRepository.save(member);
        return saveMember.getId();
    }

    @Transactional(readOnly = true)
    public Member getMember(Long memberId) {
        return memberRepository.findById(memberId).orElseThrow(() -> new MemberExceptionHandler(ErrorStatus._MEMBER_NOT_FOUND_EXCEPTION));
    }
}
