package com.thanksbucket.domain.member;

import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;

@Getter
public enum MemberRole implements GrantedAuthority {
    ROLE_GUEST("ROLE_GUEST"), ROLE_USER("ROLE_USER");

    private final String key;

    MemberRole(String key) {
        this.key = key;
    }

    public static MemberRole of(String key) {
        for (MemberRole role : values()) {
            if (role.getKey().equals(key)) {
                return role;
            }
        }
        throw new IllegalArgumentException("Invalid MemberRole key: " + key);
    }

    @Override
    public String getAuthority() {
        return key;
    }
}
