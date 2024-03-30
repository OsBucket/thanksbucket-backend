package com.thanksbucket.security.authentication;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.thanksbucket.security.dto.LoginDto;
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

public class CustomAuthenticationProcessingFilter extends AbstractAuthenticationProcessingFilter {
    private static final String LOGIN_PATH = "/api/auth/login";
    public static final String USERNAME_PARAMETER = "memberId";
    public static final String PASSWORD_PARAMETER = "password";

    private final ObjectMapper objectMapper = new ObjectMapper();

    public CustomAuthenticationProcessingFilter() {
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

        Authentication authenticationToken = CustomAuthenticationToken.unauthenticated(loginDto.getMemberId(), loginDto.getPassword());
        return getAuthenticationManager().authenticate(authenticationToken);
    }

    public final String getUsernameParameter() {
        return USERNAME_PARAMETER;
    }

    public final String getPasswordParameter() {
        return PASSWORD_PARAMETER;
    }
}
