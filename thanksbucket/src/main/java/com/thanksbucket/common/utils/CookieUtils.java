package com.thanksbucket.common.utils;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.web.util.WebUtils;

import java.net.URLEncoder;
import java.util.Optional;

import static java.nio.charset.StandardCharsets.UTF_8;
import static java.util.Optional.ofNullable;
import static lombok.AccessLevel.PRIVATE;

@NoArgsConstructor(access = PRIVATE)
public class CookieUtils {
    public static void saveAccessTokenCookie(HttpServletResponse response, String jwtToken, String domain, int maxAge) {
        ResponseCookie cookie = createAccessTokenCookie(jwtToken, domain, maxAge);
        response.setHeader(HttpHeaders.SET_COOKIE, cookie.toString());
    }

    public static ResponseCookie createAccessTokenCookie(String jwtToken, String domain, int maxAge) {
        return createCookie(HttpHeaders.AUTHORIZATION, generateJwtTokenFormat(jwtToken), domain, "/", maxAge, true, true, "none");
    }

    public static ResponseCookie createCookie(String cookieName, String value, String domain, String path, int maxAge, boolean httpOnly, boolean secure, String sameSite) {
        return ResponseCookie.from(cookieName, URLEncoder.encode(value, UTF_8))
                .domain(domain)
                .path(path)
                .maxAge(maxAge)
                .httpOnly(httpOnly)
                .secure(secure)
                .sameSite(sameSite)
                .build();
    }

    public static Cookie createSimpleCookie(String cookieName, String value, String domain, String path, int maxAge, boolean httpOnly, boolean secure, String sameSite) {
        Cookie cookie = new Cookie(cookieName, URLEncoder.encode(value, UTF_8));
        cookie.setDomain(domain);
        cookie.setPath(path);
        cookie.setMaxAge(maxAge);
        cookie.setHttpOnly(httpOnly);
        cookie.setSecure(secure);
        return cookie;
    }

    public static Optional<Cookie> getCookie(HttpServletRequest request, String cookieName) {
        return ofNullable(WebUtils.getCookie(request, cookieName));
    }

    public static void clear(HttpServletResponse response, Cookie cookie) {
        cookie.setValue("");
        cookie.setPath("/");
        cookie.setMaxAge(0);
        response.addCookie(cookie);
    }

    private static String generateJwtTokenFormat(String jwtToken) {
        return String.format("Bearer %s", jwtToken);
    }
}
