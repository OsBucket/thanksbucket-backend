package com.thanksbucket.ui.dto;

import com.thanksbucket.security.authentication.userdetails.AuthMember;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

@Getter
public class ProfileResponse {
    private final Long id;
    private final String email;
    private final String nickname;
    private final Collection<GrantedAuthority> memberRoles;

    public ProfileResponse(Long id, String email, String nickname, Collection<GrantedAuthority> memberRoles) {
        this.id = id;
        this.email = email;
        this.nickname = nickname;
        this.memberRoles = memberRoles;
    }

    public static ProfileResponse fromAuthMember(AuthMember authMember) {
        return new ProfileResponse(authMember.getMemberId(), authMember.getEmail(), authMember.getNickname(), authMember.getAuthorities());
    }
}
