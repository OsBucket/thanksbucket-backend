package com.thanksbucket.security.authentication.www.jwt;

import com.thanksbucket.security.authentication.userdetails.AuthMember;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

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
        Long memberId = jwtUtils.getMemberId(token);
        String email = jwtUtils.getEmail(token);
        String nickname = jwtUtils.getNickname(token);
        Collection<? extends GrantedAuthority> authorities = jwtUtils.getAuthorities(token);
        AuthMember authMember = AuthMember.fromToken(memberId, email, nickname, token, authorities);
        return JWTAuthenticationToken.authenticated(authMember, token);
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(JWTAuthenticationToken.class);
    }
}
