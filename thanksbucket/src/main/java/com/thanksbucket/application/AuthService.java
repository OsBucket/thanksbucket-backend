package com.thanksbucket.application;

import com.thanksbucket.domain.member.AuthMemberRepository;
import com.thanksbucket.domain.member.Member;
import com.thanksbucket.domain.occupation.Occupation;
import com.thanksbucket.security.oauth2.userinfo.OAuth2UserInfo;
import com.thanksbucket.slack.SlackService;
import com.thanksbucket.ui.dto.SignupRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class AuthService {
    private final AuthMemberRepository authMemberRepository;
    private final OccupationService occupationService;
    private final SlackService slackService;

    public Member findById(Long id) {
        return authMemberRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 회원입니다."));
    }

    @Transactional
    public Member findIfNotExistCreateMember(OAuth2UserInfo oAuth2UserInfo) {
        return authMemberRepository.findBySocialTypeAndSocialId(oAuth2UserInfo.getSocialType(), oAuth2UserInfo.getSocialId())
                .orElseGet(() -> authMemberRepository.save(oAuth2UserInfo.toEntity()));
    }

    @Transactional
    public Long signup(SignupRequest request, Long memberId) {
        Member member = authMemberRepository.findById(memberId).orElseThrow(() -> new IllegalArgumentException("소셜 인증에 성공한 유저가 아닙니다."));
        member.validateBeforeSignedUp();
        if (request.getOccupationId() != null) {
            Occupation occupation = occupationService.findById(request.getOccupationId());
            member.updateOccupation(occupation);
        }
        member.signup(request.getNickname(), request.getBirthday(), request.getDiscoveryPath());
        slackService.sendSignupMessage(member.getEmail(), member.getNickname());
        return member.getId();
    }
}
