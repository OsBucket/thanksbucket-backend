package com.thanksbucket.ui.dto;

import com.thanksbucket.domain.member.Member;
import lombok.Getter;

@Getter
public class MemberResponse {
    private final Long id;
    private final String nickname;
    private final String imageUrl;

    public MemberResponse(Member member) {
        this.id = member.getId();
        this.nickname = member.getNickname();
        this.imageUrl = member.getImageUrl();
    }
}
