package com.thanksbucket.core.invitation.appication.dto;

import com.thanksbucket.core.invitation.domain.Invitation;
import lombok.Data;

@Data
public class CreateInvitationReq {
  private Integer invitationType;
  private String invitationWho;
  private String invitationWhen;
  private String invitationWhere;
  private String invitationWhat;

  public Invitation toEntity(Long senderId) {
    return Invitation.builder()
        .senderId(senderId)
        .invitationType(invitationType)
        .invitationWho(invitationWho)
        .invitationWhen(invitationWhen)
        .invitationWhere(invitationWhere)
        .invitationWhat(invitationWhat)
        .build();
  }
}
