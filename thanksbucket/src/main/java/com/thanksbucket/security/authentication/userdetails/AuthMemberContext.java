package com.thanksbucket.security.authentication.userdetails;

import com.thanksbucket.domain.member.Member;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;

public class AuthMemberContext extends User {
    private final Member member;

    public AuthMemberContext(Member member, Collection<? extends GrantedAuthority> authorities) {
        super(member.getMemberId(), member.getPassword(), authorities);
        this.member = member;
    }

    public Member getMember() {
        return member;
    }
}
