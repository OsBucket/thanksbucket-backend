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
        return memberRepository.save(signupRequest.toEntity()).getId();
    }
}
