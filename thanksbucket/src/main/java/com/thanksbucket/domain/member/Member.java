package com.thanksbucket.domain.member;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDate;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "members")
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private Long id;

    @Column(unique = true)
    private String memberId;

    @Column
    private String password;

    @Column
    private String nickname;

    @Column
    private LocalDate birthday;

    @Column
    private String job;

    public Member(String memberId, String password, String nickname, LocalDate birthday, String job) {
        this.memberId = memberId;
        this.password = password;
        this.nickname = nickname;
        this.birthday = birthday;
        this.job = job;
    }

    public static Member signup(PasswordEncoder passwordEncoder, String memberId, String password, String nickname, LocalDate birthday, String job) {
        password = passwordEncoder.encode(password);
        return new Member(memberId, password, nickname, birthday, job);
    }
}
