package com.thanksbucket.application;

import com.thanksbucket.domain.member.Member;
import com.thanksbucket.domain.member.MemberRepository;
import com.thanksbucket.domain.occupation.Occupation;
import com.thanksbucket.domain.occupation.OccupationRepository;
import com.thanksbucket.security.oauth2.userinfo.OAuth2UserInfo;
import com.thanksbucket.slack.SlackService;
import com.thanksbucket.ui.dto.SignupRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final MemberRepository memberRepository;
    private final OccupationRepository occupationRepository;
    private final PasswordEncoder passwordEncoder;
    private final SlackService slackService;

    public Long signup(SignupRequest request) {
        memberRepository.findByMemberId(request.getMemberId())
                .ifPresent(member -> {
                    throw new IllegalArgumentException("이미 존재하는 회원입니다.");
                });
        if (request.getOccupationId() != null) {
            Occupation occupation = occupationRepository.findById(request.getOccupationId())
                    .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 직업입니다."));
            Member member = Member.signup(passwordEncoder, request.getMemberId(), request.getPassword(), request.getNickname(), request.getBirthday(), occupation);
            Member savedMember = memberRepository.save(member);
            slackService.sendSignupMessage(savedMember.getNickname());
            return savedMember.getId();
        }
        Member member = Member.signup(passwordEncoder, request.getMemberId(), request.getPassword(), request.getNickname(), request.getBirthday(), null);
        Member savedMember = memberRepository.save(member);
        slackService.sendSignupMessage(savedMember.getNickname());
        return savedMember.getId();
    }

    public Member findIfNotExistCreateMember(OAuth2UserInfo oAuth2UserInfo) {
        return memberRepository.findBySocialTypeAndSocialId(oAuth2UserInfo.getSocialType(), oAuth2UserInfo.getSocialId())
                .orElseGet(() -> memberRepository.save(oAuth2UserInfo.toEntity()));
    }

    public Member findByMemberId(String memberId) {
        return memberRepository.findByMemberId(memberId).orElseThrow(() -> new IllegalArgumentException("인증에 성공한 유저가 존재하지 않습니다."));
    }
}
