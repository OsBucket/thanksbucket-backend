package com.thanksbucket.application;

import com.thanksbucket.domain.member.Member;
import com.thanksbucket.domain.member.MemberRepository;
import com.thanksbucket.ui.dto.SignupRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    public Long signup(SignupRequest request) {
        memberRepository.findByMemberId(request.getMemberId()).ifPresent(member -> {
            throw new IllegalArgumentException("이미 존재하는 회원입니다.");
        });
        Member member = Member.signup(passwordEncoder, request.getMemberId(), request.getPassword(), request.getNickname(), request.getBirthday(), request.getJob());
        return memberRepository.save(member).getId();
    }
}
