package com.thanksbucket.security.authentication.www.jwt;

import com.thanksbucket.security.authentication.userdetails.AuthMember;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;

@Slf4j
public class JWTTokenProvider implements AuthenticationProvider {
    private static final String TOKEN_PREFIX = "Bearer";

    private final JWTUtils jwtUtils;

    public JWTTokenProvider(JWTUtils jwtUtils) {
        this.jwtUtils = jwtUtils;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        JWTAuthenticationToken jwtAuthenticationToken = (JWTAuthenticationToken) authentication;

        String header = (String) jwtAuthenticationToken.getCredentials();
        log.info("입력 헤더 - Authorization : {}", header);
        if (header == null) {
            throw new AuthenticationServiceException("인증에 실패하였습니다.");
        }
        String[] split = header.split(" ");
        if (split.length < 2 && !split[0].equals(TOKEN_PREFIX)) {
            throw new AuthenticationServiceException("유효하지 않은 토큰 형식입니다.");
        }
        String token = split[1];
        String email = jwtUtils.getEmail(token);
        String nickname = jwtUtils.getNickname(token);
        AuthMember authMember = AuthMember.fromToken(email, nickname, token);
        return JWTAuthenticationToken.authenticated(authMember, token);
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(JWTAuthenticationToken.class);
    }
}
