package com.thanksbucket.security.authentication.www;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.thanksbucket.common.response.SuccessResponse;
import com.thanksbucket.security.authentication.userdetails.AuthMemberContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
@Slf4j
public class SessionAuthenticationSuccessHandler implements AuthenticationSuccessHandler {
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        AuthMemberContext authMemberContext = (AuthMemberContext) authentication.getPrincipal();
        response.setStatus(HttpStatus.OK.value());
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);

        HttpSession session = request.getSession();
        session.setAttribute(HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY, SecurityContextHolder.getContext());

        this.addJSESSIONCookie(request, response);

        log.info("로그인 성공: user:{}, JSESSIONID:{}", authMemberContext.getMember().getMemberId(), session.getId());
        objectMapper.registerModule(new JavaTimeModule()).writeValue(response.getWriter(),
                SuccessResponse.builder()
                        .path(request.getRequestURI())
                        .data(null)
                        .build()
        );
    }

    private void addJSESSIONCookie(HttpServletRequest request, HttpServletResponse response) {
        final String SESSION_COOKIE_KEY = "JSESSIONID";
        final int SESSION_EXPIRE_TIME = 60 * 60 * 24 * 365;
        final String SESSION_PATH = "/";

        HttpSession session = request.getSession();
        Cookie savedCookie = new Cookie(SESSION_COOKIE_KEY, session.getId());
        savedCookie.setMaxAge(SESSION_EXPIRE_TIME);
        savedCookie.setSecure(true);
        savedCookie.setPath(SESSION_PATH);
        savedCookie.setHttpOnly(true);
        response.addCookie(savedCookie);
    }
}
