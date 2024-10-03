package com.thanksbucket.core.invitation.appication;

import com.thanksbucket.core.invitation.appication.dto.CreateInvitationReq;
import com.thanksbucket.core.invitation.appication.dto.InvitationDetail;
import com.thanksbucket.core.invitation.domain.Invitation;
import com.thanksbucket.core.invitation.domain.InvitationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class InvitationService {

  private final InvitationRepository invitationRepository;

  public Long save(Long memberId, CreateInvitationReq req) {
    return invitationRepository.save(req.toEntity(memberId)).getId();
  }

  public InvitationDetail findById(Long id) {
    return InvitationDetail.fromEntity(
        invitationRepository.findById(id)
            .orElseThrow());
  }
}
