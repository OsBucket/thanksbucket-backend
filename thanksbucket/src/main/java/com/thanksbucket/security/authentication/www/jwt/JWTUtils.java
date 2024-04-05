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
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.security.Key;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Map;

import static com.thanksbucket.security.authentication.userdetails.AuthMember.DEFAULT_ROLE;

@Component
@Slf4j
public class JWTUtils {
    private static final String CLAIM_AUTHORITIES_KEY = "AUTH";
    private final String SECRET_KEY;
    private final long EXPIRE_MINUTES;

    private final Key key;

    public JWTUtils(@Value("${jwt.token.secret-key}") String secretKey, @Value("${jwt.token.access-token-expire-minutes}") long expireMinutes) {
        this.SECRET_KEY = secretKey;
        this.EXPIRE_MINUTES = expireMinutes;
        this.key = Keys.hmacShaKeyFor(Decoders.BASE64.decode(secretKey));
    }

    public String generateToken(String username, Collection<GrantedAuthority> authorities) {
        return Jwts.builder()
                .issuer("ThanksBucket")
                .subject("Authorization")
                .audience().add(username).and()
                .expiration(java.sql.Date.valueOf(getExpireDate().toLocalDate()))
                .notBefore(new Date())
                .issuedAt(new Date())
                .id(username)
                .claims().add(CLAIM_AUTHORITIES_KEY, authorities).and()
                .signWith(key)
                .compact();
    }

    public Claims decodeToken(String token) {
        try {
            return Jwts.parser()
                    .verifyWith((SecretKey) key)
                    .build()
                    .parseSignedClaims(token)
                    .getPayload();
        } catch (io.jsonwebtoken.security.SecurityException | MalformedJwtException e) {
            log.info("잘못된 JWT 서명입니다.");
        } catch (ExpiredJwtException e) {
            log.info("만료된 JWT 토큰입니다.");
        } catch (UnsupportedJwtException e) {
            log.info("지원되지 않는 JWT 토큰입니다.");
        }
        throw new IllegalArgumentException("JWT 토큰이 잘못되었습니다.");
    }

    public Collection<GrantedAuthority> getAuthorities(String token) {
        Claims claims = decodeToken(token);
        List<Map<String, GrantedAuthority>> roles = claims.get(CLAIM_AUTHORITIES_KEY, List.class);
        // TODO Authorities 반환값이 이상함 확인 필요
        // TODO 임시 하드 코딩
        return List.of(new SimpleGrantedAuthority(DEFAULT_ROLE));
//        return roles.stream().map(authorities -> authorities.get("authority")).collect(Collectors.toList());
    }

    public String getUsername(String token) {
        Claims claims = decodeToken(token);
        String id = claims.getId();
        return id;
    }

    public LocalDateTime getExpireDate() {
        return LocalDateTime.now().plusMinutes(EXPIRE_MINUTES);
    }
}
