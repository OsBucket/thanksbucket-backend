package com.thanksbucket.security.dto;

import lombok.Getter;

@Getter
public class JWTResponse {
    private final String nickname;
    private final String accessToken;
    private final String refreshToken;

    public JWTResponse(String nickname, String accessToken, String refreshToken) {
        this.nickname = nickname;
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
    }

    @Override
    public String toString() {
        return "JWTResponse{" +
                "nickname='" + nickname + '\'' +
                ", accessToken='" + accessToken + '\'' +
                ", refreshToken='" + refreshToken + '\'' +
                '}';
    }
}
