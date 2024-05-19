package com.thanksbucket.security.oauth2.userinfo;

import org.jetbrains.annotations.NotNull;

import java.util.Map;

public class NaverOAuth2UserInfo extends OAuth2UserInfo {

    public NaverOAuth2UserInfo(Map<String, Object> attributes) {
        super(attributes);
    }

    @Override
    public String getId() {
        Map<String, Object> response = getResponse();
        return String.valueOf(response.get("id"));
    }

    @Override
    public String getImageUrl() {
        Map<String, Object> response = getResponse();
        return (String) response.get("profile_image");
    }

    @NotNull
    private Map<String, Object> getResponse() {
        Map<String, Object> response = (Map<String, Object>) attributes.get("response");
        if (response == null) {
            //TODO 에러타입 결정
            throw new RuntimeException("네이버 API 응답을 가져올 수 없습니다.");
        }
        return response;
    }
}
