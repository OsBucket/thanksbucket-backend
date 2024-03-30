package com.thanksbucket.security.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.thanksbucket.security.dto.LoginDto;
import com.thanksbucket.security.token.AjaxAuthenticationToken;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletInputStream;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import java.io.IOException;

public class AjaxLoginProcessingFilter extends AbstractAuthenticationProcessingFilter {
    private static final String LOGIN_PATH = "/api/auth/login";
    public static final String USERNAME_PARAMETER = "memberId";
    public static final String PASSWORD_PARAMETER = "password";

    private final ObjectMapper objectMapper = new ObjectMapper();

    public AjaxLoginProcessingFilter() {
        super(new AntPathRequestMatcher(LOGIN_PATH));
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException, IOException, ServletException {
        String method = request.getMethod();

        if (!method.equals("POST")) {
            throw new AuthenticationServiceException("Authentication method not supported: " + request.getMethod());
        }
        ServletInputStream inputStream = request.getInputStream();
        LoginDto loginDto = objectMapper.readValue(inputStream, LoginDto.class);
        if (loginDto.getMemberId().isBlank() || loginDto.getPassword().isBlank()) {
            throw new IllegalArgumentException("memberId or password is empty");
        }

        Authentication ajaxAuthenticationToken = AjaxAuthenticationToken.unauthenticated(loginDto.getMemberId(), loginDto.getPassword());
        return getAuthenticationManager().authenticate(ajaxAuthenticationToken);
    }

    public final String getUsernameParameter() {
        return USERNAME_PARAMETER;
    }

    public final String getPasswordParameter() {
        return PASSWORD_PARAMETER;
    }
}
