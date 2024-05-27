package com.thanksbucket.security.oauth2.userinfo;

import com.thanksbucket.domain.member.SocialType;
import jakarta.validation.constraints.NotNull;

import java.util.Map;

public class KakaoOAuth2UserInfo extends OAuth2UserInfo {

    public KakaoOAuth2UserInfo(Map<String, Object> attributes) {
        super(SocialType.KAKAO, attributes);
    }

    @Override
    @NotNull
    public String getSocialId() {
        return attributes.get("id").toString();
    }

    @Override
    public String getEmail() {
        // TODO 추가적인 null 처리 필요
        Map<String, Object> kakaoAccount = (Map<String, Object>) attributes.get("kakao_account");
        return (String) kakaoAccount.get("email");
    }

    @Override
    public String getImageUrl() {
        Map<String, Object> kakaoAccount = (Map<String, Object>) attributes.get("kakao_account");
        Map<String, Object> profile = (Map<String, Object>) kakaoAccount.get("profile");
        return (String) profile.get("profile_image_url");
    }
}
