package com.thanksbucket.ui.api;

import com.thanksbucket.application.AuthService;
import com.thanksbucket.domain.member.Member;
import com.thanksbucket.security.authentication.userdetails.AuthMember;
import com.thanksbucket.security.authentication.www.jwt.JWTUtils;
import com.thanksbucket.ui.dto.ProfileResponse;
import com.thanksbucket.ui.dto.SignupRequest;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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

import java.net.URLEncoder;

import static java.nio.charset.StandardCharsets.UTF_8;

@RestController
@RequestMapping("/api/auth")
@Tag(name = "auth", description = "인증")
@RequiredArgsConstructor
@Slf4j
public class AuthController {
    private final AuthService authService;
    private final JWTUtils jwtUtils;

    @PostMapping("/signup")
    public ResponseEntity<Void> signup(@Valid @RequestBody SignupRequest request, @AuthenticationPrincipal AuthMember authMember) {
        Long memberId = authService.signup(request, authMember.getEmail());
        Member member = authService.findByMemberId(memberId);
        String jwtToken = jwtUtils.generateToken(member);
        log.debug("회원가입 후 jwtToken: {}", jwtToken);
        ResponseCookie cookie = ResponseCookie.from("Authorization", URLEncoder.encode(String.format("Bearer %s", jwtToken), UTF_8))
                .path("/")
                .httpOnly(true)
                .maxAge(60 * 60 * 24 * 7)
                .build();
        return ResponseEntity.ok()
                .header(HttpHeaders.SET_COOKIE, cookie.toString())
                .build();
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
