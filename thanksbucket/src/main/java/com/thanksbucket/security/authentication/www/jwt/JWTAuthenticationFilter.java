package com.thanksbucket.security.authentication.www.jwt;

import com.thanksbucket.common.utils.CookieUtils;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;

@Slf4j
public class JWTAuthenticationFilter extends OncePerRequestFilter {
    private static final String AUTHORIZATION_HEADER = "Authorization";

    private final AuthenticationManager authenticationManager;

    public JWTAuthenticationFilter(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        log.debug("JWT Filter PASS START");
        String header = request.getHeader(AUTHORIZATION_HEADER);
        //TODO header 없을 때 쿠키로 처리
        if (header == null) {
            Cookie cookie = CookieUtils.getCookie(request, "Authorization").orElse(null);
            String token = URLDecoder.decode(cookie.getValue(), StandardCharsets.UTF_8);
            header = token;
        }
        Authentication unauthenticated = JWTAuthenticationToken.unauthenticated(header);
        try {
            Authentication authResult = this.authenticationManager.authenticate(unauthenticated);
            SecurityContextHolder.getContext().setAuthentication(authResult);
        } catch (AuthenticationException e) {
            log.debug("JWT Filter AuthenticationException", e);
        }
        filterChain.doFilter(request, response);
    }

}
