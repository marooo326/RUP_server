package com.rup.service;

import com.rup.domain.Member;
import com.rup.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

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
        Optional<Member> getMember = memberRepository.findById(memberId);
        return getMember.get();
    }

}
