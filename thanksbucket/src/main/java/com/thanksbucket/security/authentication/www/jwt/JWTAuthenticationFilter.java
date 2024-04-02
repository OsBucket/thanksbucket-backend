package com.thanksbucket.security.authentication.www.jwt;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Slf4j
public class JWTAuthenticationFilter extends AbstractAuthenticationProcessingFilter {

    public static final String AUTHORIZATION_HEADER = "Authorization";
    public static final String TOKEN_PREFIX = "Bearer";

    public JWTAuthenticationFilter(AuthenticationManager authenticationManager) {
        super(new AntPathRequestMatcher("/**"), authenticationManager);
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        String header = request.getHeader(AUTHORIZATION_HEADER);
        if (header == null) {
            throw new AuthenticationServiceException("인증에 실패하였습니다.");
        }
        String[] split = header.split(" ");
        if (split.length < 2 && !split[0].equals(TOKEN_PREFIX)) {
            throw new AuthenticationServiceException("유효하지 않은 토큰 형식입니다.");
        }
        String token = split[1];
        Authentication unauthenticated = JWTAuthenticationToken.unauthenticated(token);
        return getAuthenticationManager().authenticate(unauthenticated);
    }
}
