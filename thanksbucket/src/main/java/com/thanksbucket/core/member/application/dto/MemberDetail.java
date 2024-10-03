package com.thanksbucket.core.member.application.dto;

import com.thanksbucket.core.member.domain.Member;
import lombok.Getter;

@Getter
public class MemberDetail {

  private final Long id;
  private final String nickname;
  private final String imageUrl;

  public MemberDetail(Member member) {
    this.id = member.getId();
    this.nickname = member.getNickname();
    this.imageUrl = member.getImageUrl();
  }
}
