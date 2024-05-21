package com.thanksbucket.application;

import com.thanksbucket.domain.member.Member;
import com.thanksbucket.domain.member.MemberRepository;
import com.thanksbucket.domain.occupation.Occupation;
import com.thanksbucket.domain.occupation.OccupationRepository;
import com.thanksbucket.security.oauth2.userinfo.OAuth2UserInfo;
import com.thanksbucket.slack.SlackService;
import com.thanksbucket.ui.dto.SignupRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final MemberRepository memberRepository;
    private final OccupationRepository occupationRepository;
    private final SlackService slackService;

    public Long signup(SignupRequest request, String email) {
        Member member = memberRepository.findByEmail(email).orElseThrow(() -> new IllegalArgumentException("인증에 성공한 유저가 존재하지 않습니다."));
        if (request.getOccupationId() != null) {
            Occupation occupation = occupationRepository.findById(request.getOccupationId())
                    .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 직업입니다."));
            member.updateOccupation(occupation);
        }
        member.signup(request.getNickname(), request.getBirthday(), request.getDiscoveryPath());
        slackService.sendSignupMessage(member.getEmail(), member.getNickname());
        return member.getId();
    }

    public Member findIfNotExistCreateMember(OAuth2UserInfo oAuth2UserInfo) {
        return memberRepository.findBySocialTypeAndSocialId(oAuth2UserInfo.getSocialType(), oAuth2UserInfo.getSocialId())
                .orElseGet(() -> memberRepository.save(oAuth2UserInfo.toEntity()));
    }

    public Member findByMemberId(String memberId) {
        return memberRepository.findByMemberId(memberId).orElseThrow(() -> new IllegalArgumentException("인증에 성공한 유저가 존재하지 않습니다."));
    }
}
