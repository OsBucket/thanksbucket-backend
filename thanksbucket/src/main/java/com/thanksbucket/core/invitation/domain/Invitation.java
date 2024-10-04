package com.thanksbucket.core.invitation.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity(name = "invitations")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Invitation {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "invitation_id")
  private Long id;

  @Column(name = "sender_id")
  private Long senderId;

  @Column
  private Integer invitationType;

  @Column
  private String invitationWho;

  @Column
  private String invitationWhen;

  @Column
  private String invitationWhere;

  @Column
  private String invitationWhat;

  @Builder
  public Invitation(Long senderId, Integer invitationType, String invitationWho,
      String invitationWhen, String invitationWhere,
      String invitationWhat) {
    this.senderId = senderId;
    this.invitationType = invitationType;
    this.invitationWho = invitationWho;
    this.invitationWhen = invitationWhen;
    this.invitationWhere = invitationWhere;
    this.invitationWhat = invitationWhat;
  }
}
