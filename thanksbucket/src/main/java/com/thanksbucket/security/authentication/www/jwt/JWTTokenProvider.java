package com.thanksbucket.security.authentication.www.jwt;

import com.thanksbucket.security.authentication.userdetails.AuthMember;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

@Slf4j
public class JWTTokenProvider implements AuthenticationProvider {
    private final JWTUtils jwtUtils;

    public JWTTokenProvider(JWTUtils jwtUtils) {
        this.jwtUtils = jwtUtils;
    }

    @Override
    public Authentication authenticate(Authentication authentication) {
        String token = (String) authentication.getCredentials();
        String username = jwtUtils.getUsername(token);
        Collection<GrantedAuthority> authorities = jwtUtils.getAuthorities(token);
        AuthMember authMember = new AuthMember(username, token, authorities);
        return JWTAuthenticationToken.authenticated(authMember, token, authorities);
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(JWTAuthenticationToken.class);
    }
}
