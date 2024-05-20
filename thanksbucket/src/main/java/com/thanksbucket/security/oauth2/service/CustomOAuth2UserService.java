package com.thanksbucket.security.oauth2.service;

import com.thanksbucket.application.AuthService;
import com.thanksbucket.domain.member.Member;
import com.thanksbucket.domain.member.SocialType;
import com.thanksbucket.security.oauth2.CustomOAuth2User;
import com.thanksbucket.security.oauth2.userinfo.OAuth2UserInfo;
import com.thanksbucket.security.oauth2.userinfo.OAuth2UserInfoFactory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import java.util.Map;

@Slf4j
@Service
@RequiredArgsConstructor
public class CustomOAuth2UserService implements OAuth2UserService<OAuth2UserRequest, OAuth2User> {
    private final AuthService authService;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        log.info("CustomOAuth2UserService.loadUser() 실행 - OAuth2 로그인 요청 진입");
        /**
         * DefaultOAuth2UserService 객체를 생성하여, loadUser(userRequest)를 통해 DefaultOAuth2User 객체를 생성 후 반환
         * DefaultOAuth2UserService의 loadUser()는 소셜 로그인 API의 사용자 정보 제공 URI로 요청을 보내서
         * 사용자 정보를 얻은 후, 이를 통해 DefaultOAuth2User 객체를 생성 후 반환한다.
         * 결과적으로, OAuth2User는 OAuth 서비스에서 가져온 유저 정보를 담고 있는 유저
         */
        OAuth2UserService<OAuth2UserRequest, OAuth2User> delegate = new DefaultOAuth2UserService();
        DefaultOAuth2User oAuth2User = (DefaultOAuth2User) delegate.loadUser(userRequest);

        String registrationId = userRequest.getClientRegistration().getRegistrationId();
        SocialType socialType = SocialType.of(registrationId);

        // JSON 응답이 1Depth인 Google만 의도대로 동작하고 있으므로 실제로 무의미함 socialId 추출은 OAuth2UserInfo에서 구현
        String userNameAttributeName = userRequest.getClientRegistration()
                .getProviderDetails().getUserInfoEndpoint().getUserNameAttributeName();
        Map<String, Object> attributes = oAuth2User.getAttributes(); // 응답JSON

        // socialType에 따라 유저 정보를 통해 OAuthAttributes 객체 생성
        OAuth2UserInfo oAuth2UserInfo = OAuth2UserInfoFactory.of(socialType, attributes);

        Member member = authService.findIfNotExistCreateMember(oAuth2UserInfo);

        // DefaultOAuth2User를 구현한 CustomOAuth2User 객체를 생성해서 반환
        return CustomOAuth2User.ofMember(member, attributes, userNameAttributeName);
    }


}
