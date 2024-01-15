package com.thanksbucket.security.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class LoginDto {
    private String memberId;
    private String password;

    public LoginDto(String memberId, String password) {
        this.memberId = memberId;
        this.password = password;
    }
}

