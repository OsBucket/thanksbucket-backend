package com.thanksbucket.security.authentication.userdetails;

import com.thanksbucket.domain.member.Member;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;

public class AuthMember extends User {
    private final Member member;

    public AuthMember(Member member, Collection<? extends GrantedAuthority> authorities) {
        super(member.getMemberId(), member.getPassword(), authorities);
        this.member = member;
    }

    public Member getMember() {
        return member;
    }
}
