package com.thanksbucket.domain.member;

import com.thanksbucket.domain.bucket.Bucket;
import com.thanksbucket.domain.common.BaseTimeEntity;
import com.thanksbucket.domain.occupation.Occupation;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "members")
public class Member extends BaseTimeEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id", unique = true, nullable = false)
  private Long id;

  @Column(nullable = false)
  private String email;

  @Column
  private String password;

  @Column(unique = true)
  private String nickname;

  @Column
  private String imageUrl;

  @Column
  private LocalDate birthday;

  @Column(columnDefinition = "varchar(255)", nullable = false)
  @Enumerated(EnumType.STRING)
  private MemberRole memberRole;

  @Column(columnDefinition = "varchar(255)")
  @Enumerated(EnumType.STRING)
  private SocialType socialType;

  @Column
  private String socialId;

  @Column
  private String discoveryPath;

  @Column
  private String refreshToken;

  @OneToMany(mappedBy = "member", cascade = CascadeType.ALL)
  private List<Bucket> buckets = new ArrayList<>();

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "occupation_id")
  private Occupation occupation;

  @Builder
  public Member(Long id, String email, String nickname, String imageUrl, LocalDate birthday,
      MemberRole memberRole, SocialType socialType, String socialId, String discoveryPath,
      String refreshToken, List<Bucket> buckets, Occupation occupation) {
    this.id = id;
    this.email = email;
    this.nickname = nickname;
    this.imageUrl = imageUrl;
    this.birthday = birthday;
    this.memberRole = memberRole;
    this.socialType = socialType;
    this.socialId = socialId;
    this.discoveryPath = discoveryPath;
    this.refreshToken = refreshToken;
    this.buckets = buckets;
    this.occupation = occupation;
  }

  public static Member firstLoginOAuth(String email, SocialType socialType, String socialId,
      String imageUrl) {
    return Member.builder()
        .email(email)
        .socialType(socialType)
        .socialId(socialId)
        .imageUrl(imageUrl)
        .memberRole(MemberRole.ROLE_GUEST)
        .build();
  }

  //TODO 삭제 예정
  public Member(String email, String nickname, MemberRole memberRole) {
    this.email = email;
    this.nickname = nickname;
    this.memberRole = memberRole;
  }

  public void signup(String nickname, LocalDate birthday, String discoveryPath) {
    this.nickname = nickname;
    this.birthday = birthday;
    this.discoveryPath = discoveryPath;
    this.memberRole = MemberRole.ROLE_USER;
  }

  public void validateBeforeSignedUp() {
    if (this.memberRole == MemberRole.ROLE_USER) {
      throw new IllegalStateException("이미 가입된 회원입니다.");
    }
  }

  public void updateOccupation(Occupation occupation) {
    this.occupation = occupation;
  }

  public void updateRefreshToken(String refreshToken) {
    this.refreshToken = refreshToken;
  }
}
