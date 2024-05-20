package com.thanksbucket.domain.member;

import lombok.Getter;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

@Getter
public enum MemberRole {
    GUEST("ROLE_GUEST"), USER("ROLE_USER");

    private final String key;

    MemberRole(String key) {
        this.key = key;
    }

    public SimpleGrantedAuthority toSimpleGrantedAuthority() {
        return new SimpleGrantedAuthority(key);
    }
}
