package com.thanksbucket.core.invitation.ui;

import com.thanksbucket.core.invitation.appication.InvitationService;
import com.thanksbucket.core.invitation.appication.dto.CreateInvitationReq;
import com.thanksbucket.core.invitation.appication.dto.InvitationDetail;
import com.thanksbucket.security.authentication.userdetails.AuthMember;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/invitations")
@RequiredArgsConstructor
public class InvitationController {

  private final InvitationService invitationService;

  @GetMapping("{id}")
  public ResponseEntity<InvitationDetail> findById(@PathVariable(name = "id") Long id) {
    return ResponseEntity.ok().body(invitationService.findById(id));
  }

  @PostMapping("")
  public ResponseEntity<Long> save(@AuthenticationPrincipal AuthMember authMember,
      @RequestBody CreateInvitationReq req) {
    System.out.println(authMember);
    if(authMember == null) {
      return ResponseEntity.ok().body(invitationService.save(null, req));
    }
    return ResponseEntity.ok().body(invitationService.save(authMember.getMemberId(), req));
  }
}
