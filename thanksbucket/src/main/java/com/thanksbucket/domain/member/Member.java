package com.thanksbucket.domain.member;

import com.thanksbucket.domain.bucket.Bucket;
import com.thanksbucket.domain.occupation.Occupation;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "members")
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private Long id;

    @Column(nullable = false)
    private LocalDateTime createdAt;

    @Column(unique = true)
    private String memberId;

    @Column
    private String password;

    @Column
    private String nickname;

    @Column
    private LocalDate birthday;

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL)
    private List<Bucket> buckets = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "occupation_id")
    private Occupation occupation;

    public Member(String memberId, String password, String nickname, LocalDate birthday, Occupation occupation) {
        this.memberId = memberId;
        this.password = password;
        this.nickname = nickname;
        this.birthday = birthday;
        this.occupation = occupation;
    }

    public static Member signup(PasswordEncoder passwordEncoder, String memberId, String password, String nickname, LocalDate birthday, Occupation occupation) {
        password = passwordEncoder.encode(password);
        Member member = new Member(memberId, password, nickname, birthday, occupation);
        member.createdAt = LocalDateTime.now();
        return member;
    }
}
