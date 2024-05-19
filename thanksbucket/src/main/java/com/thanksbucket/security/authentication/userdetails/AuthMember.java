package com.thanksbucket.security.authentication.userdetails;

import com.thanksbucket.domain.member.Member;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;
import java.util.List;

public class AuthMember extends User {
    public static final String DEFAULT_ROLE = "ROLE_USER";
    private final String nickname;

    private AuthMember(String email, String nickname, String password) {
        this(email, nickname, password, generateDefaultAuthorities());
    }

    private AuthMember(String email, String nickname, String password, Collection<? extends GrantedAuthority> authorities) {
        super(email, password, authorities);
        this.nickname = nickname;
    }

    public static AuthMember fromMember(Member member) {
        return new AuthMember(member.getEmail(), member.getNickname(), member.getPassword());
    }

    public static AuthMember fromToken(String email, String nickname, String token) {
        return new AuthMember(email, nickname, token);
    }

    private static List<GrantedAuthority> generateDefaultAuthorities() {
        return List.of(new SimpleGrantedAuthority(DEFAULT_ROLE));
    }

    public String getEmail() {
        return getUsername();
    }

    public String getNickname() {
        return nickname;
    }
}
