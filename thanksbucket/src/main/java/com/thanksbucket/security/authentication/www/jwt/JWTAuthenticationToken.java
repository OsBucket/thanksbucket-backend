package com.thanksbucket.security.authentication.www.jwt;

import com.thanksbucket.security.authentication.userdetails.AuthMember;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.SpringSecurityCoreVersion;

import java.util.Collection;

public class JWTAuthenticationToken extends AbstractAuthenticationToken {
    private static final long serialVersionUID = SpringSecurityCoreVersion.SERIAL_VERSION_UID;

    private final AuthMember principal;
    private final String credentials;

    private JWTAuthenticationToken(AuthMember principal, String credentials) {
        super(null);
        this.principal = principal;
        this.credentials = credentials;
        setAuthenticated(false);
    }

    private JWTAuthenticationToken(AuthMember principal, String credentials, Collection<? extends GrantedAuthority> authorities) {
        super(authorities);
        this.principal = principal;
        this.credentials = credentials;
        super.setAuthenticated(true);
    }

    public static JWTAuthenticationToken unauthenticated(String token) {
        return new JWTAuthenticationToken(null, token);
    }

    public static JWTAuthenticationToken authenticated(AuthMember authMember, String token, Collection<? extends GrantedAuthority> authorities) {
        return new JWTAuthenticationToken(authMember, token, authorities);
    }


    @Override
    public Object getCredentials() {
        return this.credentials;
    }

    @Override
    public Object getPrincipal() {
        return this.principal;
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
