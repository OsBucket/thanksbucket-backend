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
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "members")
public class Member extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private Long id;

    @Column(unique = true)
    private String memberId;

    @Column
    private String password;

    @Column
    private String email;

    @Column
    private String nickname;

    @Column
    private String imageUrl;

    @Column
    private LocalDate birthday;

    @Enumerated(EnumType.STRING)
    private MemberRole memberRole;

    @Enumerated(EnumType.STRING)
    private SocialType socialType;

    @Column
    private String socialId;

    @Column
    private String refreshToken;

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL)
    private List<Bucket> buckets = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "occupation_id")
    private Occupation occupation;

    @Builder
    private Member(Long id, String memberId, String password, String email, String nickname, String imageUrl, LocalDate birthday, MemberRole memberRole, SocialType socialType, String socialId, String refreshToken, List<Bucket> buckets, Occupation occupation) {
        this.id = id;
        this.memberId = memberId;
        this.password = password;
        this.email = email;
        this.nickname = nickname;
        this.imageUrl = imageUrl;
        this.birthday = birthday;
        this.memberRole = memberRole;
        this.socialType = socialType;
        this.socialId = socialId;
        this.refreshToken = refreshToken;
        this.buckets = buckets;
        this.occupation = occupation;
    }


    public static Member firstLoginOAuth(String email, SocialType socialType, String socialId, String imageUrl) {
        return Member.builder()
                .email(email)
                .socialType(socialType)
                .socialId(socialId)
                .imageUrl(imageUrl)
                .memberRole(MemberRole.GUEST)
                .build();
    }


    public Member(String memberId, String password, String nickname, LocalDate birthday, Occupation occupation) {
        this.memberId = memberId;
        this.password = password;
        this.nickname = nickname;
        this.birthday = birthday;
        this.occupation = occupation;
    }

    //TODO 삭제 예정
    public Member(String email, String nickname) {
        this.email = email;
        this.nickname = nickname;
    }

    public static Member signup(PasswordEncoder passwordEncoder, String memberId, String password, String nickname, LocalDate birthday, Occupation occupation) {
        password = passwordEncoder.encode(password);
        return new Member(memberId, password, nickname, birthday, occupation);
    }

    public void updateRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }
}
