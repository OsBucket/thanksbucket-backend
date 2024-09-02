package com.thanksbucket.application;

import com.thanksbucket.core.member.domain.Member;
import com.thanksbucket.core.member.domain.MemberRepository;
import com.thanksbucket.core.occupation.domain.Occupation;
import com.thanksbucket.core.occupation.domain.OccupationRepository;
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

  private final MemberRepository memberRepository;
  private final OccupationRepository occupationRepository;
  private final SlackService slackService;

  public Member findById(Long id) {
    return memberRepository.findById(id)
        .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 회원입니다."));
  }

  @Transactional
  public Member findIfNotExistCreateMember(OAuth2UserInfo oAuth2UserInfo) {
    return memberRepository.findBySocialTypeAndSocialId(oAuth2UserInfo.getSocialType(),
            oAuth2UserInfo.getSocialId())
        .orElseGet(() -> memberRepository.save(oAuth2UserInfo.toEntity()));
  }

  @Transactional
  public Long signup(SignupRequest request, Long memberId) {
    Member member = memberRepository.findById(memberId)
        .orElseThrow(() -> new IllegalArgumentException("소셜 인증에 성공한 유저가 아닙니다."));
    member.validateBeforeSignedUp();
    if (request.getOccupationId() != null) {
      Occupation occupation = occupationRepository.findById(request.getOccupationId())
          .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 직업입니다."));
      member.updateOccupation(occupation);
    }
    member.signup(request.getNickname(), request.getBirthday(), request.getDiscoveryPath());
    slackService.sendSignupMessage(member.getEmail(), member.getNickname());
    return member.getId();
  }
}
