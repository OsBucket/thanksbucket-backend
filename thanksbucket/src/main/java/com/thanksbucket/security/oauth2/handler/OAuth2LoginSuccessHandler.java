package com.thanksbucket.security.oauth2.handler;

import com.thanksbucket.common.utils.CookieUtils;
import com.thanksbucket.security.authentication.www.jwt.JWTUtils;
import com.thanksbucket.security.oauth2.CustomOAuth2User;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;

import java.io.IOException;

@Slf4j
@RequiredArgsConstructor
public class OAuth2LoginSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {
    private final JWTUtils jwtUtils;
    private String DEFAULT_REDIRECT_URL;

    @Value("${app.frontend.url}")
    public void setDefaultRedirectUrl(String defaultRedirectUrl) {
        this.DEFAULT_REDIRECT_URL = defaultRedirectUrl;
    }

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        log.info("OAuth2LoginSuccessHandler.onAuthenticationSuccess() 실행 - OAuth2 로그인 성공");

        CustomOAuth2User customOAuth2User = (CustomOAuth2User) authentication.getPrincipal();
        String jwtToken = jwtUtils.generateToken(customOAuth2User.getEmail(), customOAuth2User.getNickname(), customOAuth2User.getAuthorities());
        log.info("JWT 토큰 생성: {}", jwtToken);
        String cookieValue = String.format("Bearer %s", jwtToken);
        log.info("Authorization 쿠키 생성: {}", cookieValue);
        CookieUtils.saveCookie(response, HttpHeaders.AUTHORIZATION, String.format("Bearer %s", jwtToken), 60 * 60 * 24 * 7);
        clearAuthenticationAttributes(request);
        getRedirectStrategy().sendRedirect(request, response, DEFAULT_REDIRECT_URL);
        log.info("OAuth2 로그인 성공 후 리다이렉트 완료");
    }
}
