package com.thanksbucket.security.oauth2.userinfo;


import com.thanksbucket.domain.member.Member;
import com.thanksbucket.domain.member.SocialType;
import lombok.Getter;

import java.util.Map;

public abstract class OAuth2UserInfo {

    @Getter
    private final SocialType socialType;
    protected Map<String, Object> attributes;

    protected OAuth2UserInfo(SocialType socialType, Map<String, Object> attributes) {
        this.socialType = socialType;
        this.attributes = attributes;
    }

    public abstract String getSocialId(); //소셜 식별 값 : 구글 - "sub", 카카오 - "id", 네이버 - "id"

    public abstract String getEmail();

    public abstract String getImageUrl();

    public Member toEntity() {
        return Member.firstLoginOAuth(getEmail(), getSocialType(), getSocialId(), getImageUrl());
    }
}
