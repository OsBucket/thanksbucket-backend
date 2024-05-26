package com.thanksbucket.security.authentication.www.jwt;

import com.thanksbucket.domain.member.Member;
import com.thanksbucket.domain.member.MemberRole;
import com.thanksbucket.security.authentication.userdetails.AuthMember;
import com.thanksbucket.security.oauth2.CustomOAuth2User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

@Component
@Slf4j
public class JWTUtils {
    private static final String CLAIM_AUTHORITIES_KEY = "AUTHORITIES";
    private static final String CLAIM_EMAIL_KEY = "EMAIL";
    private static final String CLAIM_NICKNAME_KEY = "NICKNAME";
    private final String SECRET_KEY;
    private final long ACCESS_EXPIRE_MINUTE;
    private final long REFRESH_EXPIRE_MINUTE;

    private final SecretKey key;

    public JWTUtils(@Value("${jwt.secret-key}") String secretKey,
                    @Value("${jwt.access-token.expire-minutes}") long accessExpireMinutes,
                    @Value("${jwt.refresh-token.expire-minutes}") long refreshExpireMinutes
    ) {
        this.SECRET_KEY = secretKey;
        this.ACCESS_EXPIRE_MINUTE = accessExpireMinutes;
        this.REFRESH_EXPIRE_MINUTE = refreshExpireMinutes;
        this.key = Keys.hmacShaKeyFor(Decoders.BASE64.decode(secretKey));
    }

    public String generateToken(CustomOAuth2User customOAuth2User) {
        return generateToken(customOAuth2User.getMemberId(), customOAuth2User.getEmail(), customOAuth2User.getNickname(), customOAuth2User.getAuthorities());
    }

    public String generateToken(Member member) {
        return generateToken(member.getId(), member.getEmail(), member.getNickname(), List.of(member.getMemberRole()));
    }

    public String generateToken(AuthMember authMember) {
        return generateToken(authMember.getMemberId(), authMember.getEmail(), authMember.getNickname(), authMember.getAuthorities());
    }

    public String generateToken(Long memberId, String email, String nickname, Collection<? extends GrantedAuthority> memberRoles) {
        return Jwts.builder()
                .issuer("ThanksBucket")
                .subject(String.valueOf(memberId))
                .expiration(java.sql.Timestamp.valueOf(getExpireDate()))
                .issuedAt(new Date())
                .claims()
                .add(CLAIM_EMAIL_KEY, email)
                .add(CLAIM_NICKNAME_KEY, nickname)
                .add(CLAIM_AUTHORITIES_KEY, memberRoles.stream().map(GrantedAuthority::getAuthority).toList())
                .and()
                .signWith(key)
                .compact();
    }

    public Claims decodeToken(String token) {
        try {
            return Jwts.parser()
                    .verifyWith(key)
                    .build()
                    .parseSignedClaims(token)
                    .getPayload();
        } catch (io.jsonwebtoken.security.SecurityException | MalformedJwtException e) {
            throw new AuthenticationServiceException("잘못된 JWT 서명입니다.");
        } catch (ExpiredJwtException e) {
            throw new AuthenticationServiceException("만료된 JWT 토큰입니다.");
        } catch (UnsupportedJwtException e) {
            throw new AuthenticationServiceException("지원되지 않는 JWT 토큰입니다.");
        }
    }

    public Long getMemberId(String token) {
        Claims claims = decodeToken(token);
        return Long.parseLong(claims.getSubject());
    }

    public String getEmail(String token) {
        Claims claims = decodeToken(token);
        return claims.getSubject();
    }

    public String getNickname(String token) {
        Claims claims = decodeToken(token);
        return claims.get(CLAIM_NICKNAME_KEY, String.class);
    }

    public List<MemberRole> getAuthorities(String token) {
        Claims claims = decodeToken(token);
        ArrayList<String> roles = claims.get(CLAIM_AUTHORITIES_KEY, ArrayList.class);
        return roles.stream().map(MemberRole::of).toList();
    }

    public LocalDateTime getExpireDate() {
        return LocalDateTime.now().plusMinutes(ACCESS_EXPIRE_MINUTE);
    }
}
