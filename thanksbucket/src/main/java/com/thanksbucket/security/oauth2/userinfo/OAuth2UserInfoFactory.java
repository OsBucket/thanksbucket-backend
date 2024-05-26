package com.thanksbucket.security.oauth2.userinfo;

import com.thanksbucket.domain.member.SocialType;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.Map;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class OAuth2UserInfoFactory {

    public static OAuth2UserInfo of(SocialType socialType, Map<String, Object> attributes) {
        if (socialType == SocialType.NAVER) {
            return ofNaver(attributes);
        }
        if (socialType == SocialType.KAKAO) {
            return ofKakao(attributes);
        }
        throw new IllegalArgumentException("지원하지 않는 소셜 타입입니다.");
//        return ofGoogle(userNameAttributeName, attributes);
    }

    private static OAuth2UserInfo ofNaver(Map<String, Object> attributes) {
        return new NaverOAuth2UserInfo(attributes);
    }

    private static OAuth2UserInfo ofKakao(Map<String, Object> attributes) {
        return new KakaoOAuth2UserInfo(attributes);
    }

//    public static OAuthAttributes ofGoogle(String userNameAttributeName, Map<String, Object> attributes) {
//        return OAuthAttributes.builder()
//                .nameAttributeKey(userNameAttributeName)
//                .oauth2UserInfo(new GoogleOAuth2UserInfo(attributes))
//                .build();
//    }
}
