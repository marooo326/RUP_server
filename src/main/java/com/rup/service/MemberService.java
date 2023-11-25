package com.rup.service;

import com.rup.apiPayload.code.ErrorStatus;
import com.rup.apiPayload.exception.handler.MemberExceptionHandler;
import com.rup.domain.Member;
import com.rup.domain.enums.UserRole;
import com.rup.jwt.JwtProvider;
import com.rup.repository.MemberRepository;
import com.rup.web.dto.request.MemberRequestDto;
import com.rup.web.dto.response.MemberResponseDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@Slf4j
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;
    private final JwtProvider jwtProvider;


    public Long saveMember(Member member) {
        Member saveMember = memberRepository.save(member);
        return saveMember.getId();
    }

    @Transactional(readOnly = true)
    public Member getMember(Long memberId) {
        return findMember(memberId);
    }

    public Member findMember(Long memberId) {
        return memberRepository.findById(memberId).orElseThrow(() -> new MemberExceptionHandler(ErrorStatus._MEMBER_NOT_FOUND_EXCEPTION));
    }

    public boolean existsMember(String oAuthId) {
        return memberRepository.existsByOAuthId(oAuthId);
    }

    public MemberResponseDto.LoginMember login(String oAuthId) {
        Member oauthMember = memberRepository.findByOAuthId(oAuthId);
        String token = jwtProvider.createToken(oauthMember.getId(), List.of(new SimpleGrantedAuthority("USER")));
        return MemberResponseDto.LoginMember.builder()
                .accessToken(token)
                .build();
    }

    public void signUp(MemberRequestDto.signUpDto signUpDto) {
        Member member = Member.builder()
                .name(signUpDto.getNickname())
                .OAuthId(signUpDto.getKakaoId())
                .point(0L)
                .userRole(UserRole.USER)
                .type(signUpDto.getType())
                .keyword(signUpDto.getKeyword())
                .build();
        memberRepository.save(member);

    }
}
