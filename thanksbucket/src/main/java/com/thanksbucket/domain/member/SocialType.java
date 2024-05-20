package com.thanksbucket.domain.member;

public enum SocialType {
    KAKAO, GOOGLE, NAVER;

    public static SocialType of(String providerId) {
        if ("naver".equals(providerId)) {
            return NAVER;
        }
        if ("google".equals(providerId)) {
            return GOOGLE;
        }
        if ("kakao".equals(providerId)) {
            return KAKAO;
        }
        throw new IllegalArgumentException("지원하지 않는 소셜 타입입니다.");
    }
}
