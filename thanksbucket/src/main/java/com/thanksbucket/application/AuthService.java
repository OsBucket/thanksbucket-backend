package com.thanksbucket.application;

import com.thanksbucket.domain.member.MemberRepository;
import com.thanksbucket.ui.dto.SignupRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final MemberRepository memberRepository;

    public Long signup(SignupRequest signupRequest) {
        memberRepository.findByMemberId(signupRequest.getMemberId()).ifPresent(member -> {
            throw new IllegalArgumentException("이미 존재하는 회원입니다.");
        });
        return memberRepository.save(signupRequest.toEntity()).getId();
    }
}
