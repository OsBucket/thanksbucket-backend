package com.thanksbucket.security.authentication.www.jwt;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.thanksbucket.common.response.SuccessResponse;
import com.thanksbucket.security.authentication.userdetails.AuthMember;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import java.io.IOException;

@Slf4j
public class JWTAuthenticationSuccessHandler implements AuthenticationSuccessHandler {
    private final ObjectMapper objectMapper = new ObjectMapper();

    private final JWTUtils jwtUtils;

    public JWTAuthenticationSuccessHandler(JWTUtils jwtUtils) {
        this.jwtUtils = jwtUtils;
    }

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        AuthMember authMember = (AuthMember) authentication.getPrincipal();
        response.setStatus(HttpStatus.OK.value());
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);

        String jwtToken = jwtUtils.generateToken(authMember.getUsername(), authMember.getAuthorities());

        log.info("로그인 성공: user: {}, JWT-Token: {}", authMember.getUsername(), jwtToken);
        objectMapper.registerModule(new JavaTimeModule()).writeValue(response.getWriter(),
                SuccessResponse.builder()
                        .path(request.getRequestURI())
                        .data(null)
                        .build()
        );
    }
}
