package com.thanksbucket.ui.dto;

import lombok.Getter;

@Getter
public class ProfileResponse {
    private final Long memberId;
    private final String nickname;

    public ProfileResponse(Long memberId, String nickname) {
        this.memberId = memberId;
        this.nickname = nickname;
    }
}
