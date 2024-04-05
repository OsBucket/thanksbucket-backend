package com.thanksbucket.security.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class JWTResponse {
    private final String memberId;
    private final String accessToken;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private final LocalDateTime accessTokenExpiresIn;
    private final String refreshToken;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private final LocalDateTime refreshTokenExpiresIn;

    public JWTResponse(String memberId, String accessToken, LocalDateTime accessTokenExpiresIn) {
        this(memberId, accessToken, accessTokenExpiresIn, null, null);
    }

    public JWTResponse(String memberId, String accessToken, LocalDateTime accessTokenExpiresIn, String refreshToken, LocalDateTime refreshTokenExpiresIn) {
        this.memberId = memberId;
        this.accessToken = accessToken;
        this.accessTokenExpiresIn = accessTokenExpiresIn;
        this.refreshToken = refreshToken;
        this.refreshTokenExpiresIn = refreshTokenExpiresIn;
    }

    @Override
    public String toString() {
        return "JWTResponse{" +
                "memberId='" + memberId + '\'' +
                ", accessToken='" + accessToken + '\'' +
                ", accessTokenExpiresIn=" + accessTokenExpiresIn +
                ", refreshToken='" + refreshToken + '\'' +
                ", refreshTokenExpiresIn=" + refreshTokenExpiresIn +
                '}';
    }
}
