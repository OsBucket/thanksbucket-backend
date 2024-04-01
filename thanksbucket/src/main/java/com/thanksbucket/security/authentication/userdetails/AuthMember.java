package com.thanksbucket.security.authentication.userdetails;

import com.thanksbucket.domain.member.Member;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;
import java.util.List;

public class AuthMember extends User {
    public static final String DEFAULT_ROLE = "ROLE_USER";

    public static AuthMember fromMember(Member member) {
        List<GrantedAuthority> defaultAuthorities = generateDefaultAuthorities();
        return new AuthMember(member.getMemberId(), member.getPassword(), defaultAuthorities);
    }

    public AuthMember(String username, String password, Collection<? extends GrantedAuthority> authorities) {
        super(username, password, authorities);
    }

    public AuthMember(String username, String password, boolean enabled, boolean accountNonExpired, boolean credentialsNonExpired, boolean accountNonLocked, Collection<? extends GrantedAuthority> authorities) {
        super(username, password, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, authorities);
    }

    private static List<GrantedAuthority> generateDefaultAuthorities() {
        return List.of(new SimpleGrantedAuthority(DEFAULT_ROLE));
    }
}
