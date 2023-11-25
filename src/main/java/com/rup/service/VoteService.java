package com.rup.service;

import com.rup.domain.Member;
import com.rup.domain.Promise;
import com.rup.domain.mapping.Vote;
import com.rup.repository.MemberRepository;
import com.rup.repository.PromiseRepository;
import com.rup.repository.VoteRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@Slf4j
@RequiredArgsConstructor
public class VoteService {
    private final VoteRepository voteRepository;
    private final PromiseRepository promiseRepository;
    private final MemberRepository memberRepository;

    public void newVote(Long promiseId, Long memberId) {
        Promise promise = promiseRepository.findById(promiseId).get();
        Member member = memberRepository.findById(memberId).get();
        Vote vote = Vote.builder()
                .promise(promise)
                .target(member)
                .build();
        voteRepository.save(vote);
    }
}
