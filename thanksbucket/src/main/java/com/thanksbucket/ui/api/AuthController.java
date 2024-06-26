package com.thanksbucket.ui.api;

import com.thanksbucket.application.AuthService;
import com.thanksbucket.common.utils.CookieUtils;
import com.thanksbucket.domain.member.Member;
import com.thanksbucket.security.authentication.userdetails.AuthMember;
import com.thanksbucket.security.authentication.www.jwt.JWTUtils;
import com.thanksbucket.ui.dto.ProfileResponse;
import com.thanksbucket.ui.dto.SignupRequest;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@Tag(name = "auth", description = "인증")
@Slf4j
public class AuthController {
    private final AuthService authService;
    private final JWTUtils jwtUtils;
    private final Integer JWT_ACCESS_TOKEN_COOKIE_MAX_AGE;

    public AuthController(AuthService authService, JWTUtils jwtUtils, @Value("${jwt.access-token.cookie.max-age}") Integer JWT_ACCESS_TOKEN_COOKIE_MAX_AGE) {
        this.authService = authService;
        this.jwtUtils = jwtUtils;
        this.JWT_ACCESS_TOKEN_COOKIE_MAX_AGE = JWT_ACCESS_TOKEN_COOKIE_MAX_AGE;
    }

    @PostMapping("/signup")
    public ResponseEntity<String> signup(@Valid @RequestBody SignupRequest request, @AuthenticationPrincipal AuthMember authMember) {
        Long memberId = authService.signup(request, authMember.getMemberId());
        Member member = authService.findById(memberId);
        String jwtToken = jwtUtils.generateToken(member);
        log.debug("회원가입 후 jwtToken: {}", jwtToken);
        return ResponseEntity.ok()
            .header(HttpHeaders.AUTHORIZATION, "Bearer " + jwtToken)
            .body("Bearer " + jwtToken);
    }

    @GetMapping("/logout")
    public ResponseEntity<Void> logout(HttpSession session, @AuthenticationPrincipal User user) {
        log.info("{}가 로그아웃 하였습니다.", user.getUsername());
        if (session != null) {
            session.invalidate();
        }
        return ResponseEntity.ok().build();
    }

    @GetMapping("/profile")
    public ResponseEntity<ProfileResponse> profile(@AuthenticationPrincipal AuthMember authMember) {
        return ResponseEntity.ok(ProfileResponse.fromAuthMember(authMember));
    }
}
