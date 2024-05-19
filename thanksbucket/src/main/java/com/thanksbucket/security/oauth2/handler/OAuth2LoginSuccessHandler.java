package com.thanksbucket.security.oauth2.handler;

import com.thanksbucket.application.AuthService;
import com.thanksbucket.security.authentication.www.jwt.JWTUtils;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Slf4j
@Component
@RequiredArgsConstructor
public class OAuth2LoginSuccessHandler implements AuthenticationSuccessHandler {
    private final JWTUtils jwtUtils;
//    private final AuthService authService;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        log.info("OAuth2LoginSuccessHandler.onAuthenticationSuccess() 실행 - OAuth2 로그인 성공");

//        // OAuth2 로그인 성공 시, JWT 토큰 생성
//        String token = jwtUtils.createToken(authentication);
//        response.sendRedirect("http://localhost:3000/oauth2/redirect?token=" + token);
        response.getWriter().write("OAuth2 로그인 성공");
    }
}
