package com.thanksbucket.security.authentication.userdetails;

import com.thanksbucket.domain.member.Member;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;
import java.util.List;

public class AuthMember extends User {
    private final String email;
    private final String nickname;

    private AuthMember(Long memberId, String email, String nickname, String password, Collection<? extends GrantedAuthority> authorities) {
        super(String.valueOf(memberId), password, authorities);
        this.email = email;
        this.nickname = nickname;
    }

    public static AuthMember fromMember(Member member) {
        return new AuthMember(member.getId(), member.getEmail(), member.getNickname(), member.getPassword(), List.of(member.getMemberRole()));
    }

    public static AuthMember fromToken(Long memberId, String email, String nickname, String token, Collection<? extends GrantedAuthority> authorities) {
        return new AuthMember(memberId, email, nickname, token, authorities);
    }

    public Long getMemberId() {
        return Long.parseLong(getUsername());
    }

    public String getEmail() {
        return email;
    }

    public String getNickname() {
        return nickname;
    }
}
