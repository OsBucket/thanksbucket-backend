package com.thanksbucket.security.authentication.userdetails;

import com.thanksbucket.domain.member.Member;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;
import java.util.List;

public class AuthMember extends User {
    private final String nickname;

    private AuthMember(String email, String nickname, String password, Collection<? extends GrantedAuthority> authorities) {
        super(email, password, authorities);
        this.nickname = nickname;
    }

    public static AuthMember fromMember(Member member) {
        return new AuthMember(member.getEmail(), member.getNickname(), member.getPassword(), List.of(member.getMemberRole()));
    }

    public static AuthMember fromToken(String email, String nickname, String token, Collection<? extends GrantedAuthority> authorities) {
        return new AuthMember(email, nickname, token, authorities);
    }

    public String getEmail() {
        return getUsername();
    }

    public String getNickname() {
        return nickname;
    }
}
