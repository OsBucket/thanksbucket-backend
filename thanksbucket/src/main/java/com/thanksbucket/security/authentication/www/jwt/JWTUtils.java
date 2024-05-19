package com.thanksbucket.security.authentication.www.jwt;

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
import java.util.Collection;
import java.util.Date;

@Component
@Slf4j
public class JWTUtils {
    private static final String CLAIM_AUTHORITIES_KEY = "AUTHORITIES";
    private static final String CLAIM_NICKNAME_KEY = "NICKNAME";
    private final String SECRET_KEY;
    private final long ACCESS_EXPIRE_MINUTE;
    private final long REFRESH_EXPIRE_MINUTE;

    private final SecretKey key;
//    private final AuthService authService;

    public JWTUtils(@Value("${jwt.secret-key}") String secretKey,
                    @Value("${jwt.access-token.expire-minutes}") long accessExpireMinutes,
                    @Value("${jwt.refresh-token.expire-minutes}") long refreshExpireMinutes
//                    AuthService authService
    ) {
        this.SECRET_KEY = secretKey;
        this.ACCESS_EXPIRE_MINUTE = accessExpireMinutes;
        this.REFRESH_EXPIRE_MINUTE = refreshExpireMinutes;
        this.key = Keys.hmacShaKeyFor(Decoders.BASE64.decode(secretKey));
//        this.authService = authService;
    }

    public String generateToken(String email, String nickname, Collection<GrantedAuthority> authorities) {
        return Jwts.builder()
                .issuer("ThanksBucket")
                .subject(email)
                .expiration(java.sql.Timestamp.valueOf(getExpireDate()))
                .issuedAt(new Date())
                .claims()
                .add(CLAIM_NICKNAME_KEY, nickname)
                .add(CLAIM_AUTHORITIES_KEY, authorities)
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

    public String getEmail(String token) {
        Claims claims = decodeToken(token);
        return claims.getSubject();
    }

    public String getNickname(String token) {
        Claims claims = decodeToken(token);
        return claims.get(CLAIM_NICKNAME_KEY, String.class);
    }

    public LocalDateTime getExpireDate() {
        return LocalDateTime.now().plusMinutes(ACCESS_EXPIRE_MINUTE);
    }
}
