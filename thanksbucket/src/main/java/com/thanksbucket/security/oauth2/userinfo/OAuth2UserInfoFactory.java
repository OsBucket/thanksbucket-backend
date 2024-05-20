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
        throw new IllegalArgumentException("지원하지 않는 소셜 타입입니다.");
//        if (socialType == SocialType.KAKAO) {
//            return ofKakao(userNameAttributeName, attributes);
//        }
//        return ofGoogle(userNameAttributeName, attributes);
    }

//    private static OAuthAttributes ofKakao(String userNameAttributeName, Map<String, Object> attributes) {
//        return OAuthAttributes.builder()
//                .nameAttributeKey(userNameAttributeName)
//                .oauth2UserInfo(new KakaoOAuth2UserInfo(attributes))
//                .build();
//    }
//
//    public static OAuthAttributes ofGoogle(String userNameAttributeName, Map<String, Object> attributes) {
//        return OAuthAttributes.builder()
//                .nameAttributeKey(userNameAttributeName)
//                .oauth2UserInfo(new GoogleOAuth2UserInfo(attributes))
//                .build();
//    }

    private static OAuth2UserInfo ofNaver(Map<String, Object> attributes) {
        return new NaverOAuth2UserInfo(attributes);
    }
}
