package com.thanksbucket.ui.dto;

import com.thanksbucket.security.authentication.userdetails.AuthMember;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

@Getter
public class ProfileResponse {
    private final String email;
    private final String nickname;
    private final Collection<GrantedAuthority> memberRoles;

    public ProfileResponse(String email, String nickname, Collection<GrantedAuthority> memberRoles) {
        this.email = email;
        this.nickname = nickname;
        this.memberRoles = memberRoles;
    }

    public static ProfileResponse fromAuthMember(AuthMember member) {
        return new ProfileResponse(member.getEmail(), member.getNickname(), member.getAuthorities());
    }
}
