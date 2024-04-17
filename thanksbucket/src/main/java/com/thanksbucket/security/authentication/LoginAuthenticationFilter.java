package com.thanksbucket.security.authentication;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.thanksbucket.security.dto.LoginRequest;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import java.io.IOException;

public class LoginAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
    private static final AntPathRequestMatcher LOGIN_PATH = new AntPathRequestMatcher("/api/auth/login", "POST");
    private static final String USERNAME_PARAMETER = "memberId";
    private static final String PASSWORD_PARAMETER = "password";

    private final ObjectMapper objectMapper = new ObjectMapper();

    public LoginAuthenticationFilter(AuthenticationManager authenticationManager) {
        super(authenticationManager);
        setRequiresAuthenticationRequestMatcher(LOGIN_PATH);
        setUsernameParameter(USERNAME_PARAMETER);
        setPasswordParameter(PASSWORD_PARAMETER);
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) {
        try {
            this.validateMethod(request);

            LoginRequest loginRequest = objectMapper.readValue(request.getInputStream(), LoginRequest.class);
            if (loginRequest.getMemberId().isBlank() || loginRequest.getPassword().isBlank()) {
                throw new AuthenticationServiceException("ID 또는 PASSWORD가 비어있습니다.");
            }

            Authentication authRequest = UsernamePasswordAuthenticationToken.unauthenticated(loginRequest.getMemberId(), loginRequest.getPassword());
            return getAuthenticationManager().authenticate(authRequest);

        } catch (IOException ex) {
            throw new AuthenticationServiceException("요청에 문제가 있습니다.", ex);
        }
    }

    private void validateMethod(HttpServletRequest request) {
        if (!request.getMethod().equals("POST")) {
            throw new AuthenticationServiceException("Authentication method not supported: " + request.getMethod());
        }
    }
}
