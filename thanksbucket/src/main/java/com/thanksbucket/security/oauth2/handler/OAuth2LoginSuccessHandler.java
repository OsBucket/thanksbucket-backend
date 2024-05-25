package com.thanksbucket.security.oauth2.handler;

import com.thanksbucket.common.utils.CookieUtils;
import com.thanksbucket.security.authentication.www.jwt.JWTUtils;
import com.thanksbucket.security.oauth2.CustomOAuth2User;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Slf4j
@Component
public class OAuth2LoginSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {
    private final JWTUtils jwtUtils;
    private final String DEFAULT_REDIRECT_URL; // TODO 추후에 SimpleAuthenticationSuccessHandler 사용하기
    private final Integer JWT_ACCESS_TOKEN_COOKIE_MAX_AGE;

    public OAuth2LoginSuccessHandler(JWTUtils jwtUtils,
                                     @Value("${app.frontend.url}") String DEFAULT_REDIRECT_URL,
                                     @Value("${jwt.access-token.cookie.max-age}") Integer JWT_ACCESS_TOKEN_COOKIE_MAX_AGE) {
        this.jwtUtils = jwtUtils;
        this.DEFAULT_REDIRECT_URL = DEFAULT_REDIRECT_URL;
        this.JWT_ACCESS_TOKEN_COOKIE_MAX_AGE = JWT_ACCESS_TOKEN_COOKIE_MAX_AGE;
    }

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        log.info("OAuth2LoginSuccessHandler.onAuthenticationSuccess() 실행 - OAuth2 로그인 성공");

        CustomOAuth2User customOAuth2User = (CustomOAuth2User) authentication.getPrincipal();
        String jwtToken = jwtUtils.generateToken(customOAuth2User.getEmail(), customOAuth2User.getNickname(), customOAuth2User.getAuthorities());
        log.info("JWT 토큰 생성: {}", jwtToken);
        CookieUtils.saveAccessTokenCookie(response, jwtToken, null, JWT_ACCESS_TOKEN_COOKIE_MAX_AGE);
        clearAuthenticationAttributes(request);
        // 요청 Header 부터 리다이렉트 URL을 가져옴
        String redirectURI = request.getHeader("OAUTH2_REDIRECT_URI");
        if (redirectURI == null) {
            redirectURI = DEFAULT_REDIRECT_URL;
        }
        getRedirectStrategy().sendRedirect(request, response, redirectURI);
        log.info("OAuth2 로그인 성공 후 리다이렉트 : {}", redirectURI);
    }
}
