package com.thanksbucket.core.invitation.appication.dto;

import com.thanksbucket.core.invitation.domain.Invitation;
import lombok.Data;

@Data
public class InvitationDetail {

  private Long id;
  private Long senderId;
  private Integer invitationType;
  private String invitationWho;
  private String invitationWhen;
  private String invitationWhere;
  private String invitationWhat;

  public static InvitationDetail fromEntity(Invitation invitation) {
    InvitationDetail invitationDetail = new InvitationDetail();
    invitationDetail.setId(invitation.getId());
    invitationDetail.setSenderId(invitation.getSenderId());
    invitationDetail.setInvitationType(invitation.getInvitationType());
    invitationDetail.setInvitationWho(invitation.getInvitationWho());
    invitationDetail.setInvitationWhen(invitation.getInvitationWhen());
    invitationDetail.setInvitationWhere(invitation.getInvitationWhere());
    invitationDetail.setInvitationWhat(invitation.getInvitationWhat());
    return invitationDetail;
  }
}
