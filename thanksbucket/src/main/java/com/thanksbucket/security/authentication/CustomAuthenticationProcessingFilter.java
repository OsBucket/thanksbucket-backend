package com.thanksbucket.security.authentication;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.thanksbucket.security.dto.LoginDto;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import java.io.IOException;

public class CustomAuthenticationProcessingFilter extends AbstractAuthenticationProcessingFilter {
    private static final AntPathRequestMatcher LOGIN_PATH = new AntPathRequestMatcher("/api/auth/login", "POST");
    private static final String USERNAME_PARAMETER = "memberId";
    private static final String PASSWORD_PARAMETER = "password";

    private final ObjectMapper objectMapper = new ObjectMapper();

    public CustomAuthenticationProcessingFilter() {
        super(LOGIN_PATH);
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        try {
            this.validateMethod(request);

            LoginDto loginDto = objectMapper.readValue(request.getInputStream(), LoginDto.class);
            if (loginDto.getMemberId().isBlank() || loginDto.getPassword().isBlank()) {
                throw new IllegalArgumentException("ID 또는 PASSWORD가 비어있습니다.");
            }

            Authentication authRequest = CustomAuthenticationToken.unauthenticated(loginDto.getMemberId(), loginDto.getPassword());
            return getAuthenticationManager().authenticate(authRequest);

        } catch (InternalAuthenticationServiceException ex) {
            throw ex;
        } catch (IOException ex) {
            throw new InternalAuthenticationServiceException("요청에 문제가 있습니다.", ex);
        } catch (Exception ex) {
            throw new InternalAuthenticationServiceException(ex.getMessage(), ex);
        }
    }

    private void validateMethod(HttpServletRequest request) {
        if (!request.getMethod().equals("POST")) {
            throw new IllegalArgumentException("Authentication method not supported: " + request.getMethod());
        }
    }

    public final String getUsernameParameter() {
        return USERNAME_PARAMETER;
    }

    public final String getPasswordParameter() {
        return PASSWORD_PARAMETER;
    }
}
