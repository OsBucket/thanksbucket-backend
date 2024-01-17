package com.thanksbucket.security.common;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import java.io.IOException;


@Component
@Slf4j
public class AjaxLoginAuthenticationEntryPoint implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        log.error("권한이 없습니다. {} {} : {}",request.getMethod(),request.getRequestURL(), authException.getMessage());
        response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "권한이 없습니다.");
    }
}
