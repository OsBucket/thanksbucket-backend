package com.thanksbucket.security.authentication.www.jwt;

import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.SpringSecurityCoreVersion;

import java.util.Collection;

public class JWTAuthenticationToken extends AbstractAuthenticationToken {
    private static final long serialVersionUID = SpringSecurityCoreVersion.SERIAL_VERSION_UID;

    private final String username;
    private final String token;

    private JWTAuthenticationToken(String username, String token) {
        super(null);
        this.username = username;
        this.token = token;
        setAuthenticated(false);
    }

    private JWTAuthenticationToken(String username, String token, Collection<? extends GrantedAuthority> authorities) {
        super(authorities);
        this.username = username;
        this.token = token;
        super.setAuthenticated(true);
    }

    public static JWTAuthenticationToken unauthenticated(String token) {
        return new JWTAuthenticationToken(null, token);
    }

    public static JWTAuthenticationToken authenticated(String username, String token, Collection<? extends GrantedAuthority> authorities) {
        return new JWTAuthenticationToken(username, token, authorities);
    }


    @Override
    public Object getCredentials() {
        return this.token;
    }

    @Override
    public Object getPrincipal() {
        return this.username;
    }

    @Override
    public void setAuthenticated(boolean isAuthenticated) throws IllegalArgumentException {
        if (isAuthenticated) {
            throw new IllegalArgumentException(
                    "Cannot set this token to trusted - use constructor which takes a GrantedAuthority list instead");
        }
        super.setAuthenticated(false);
    }
}
