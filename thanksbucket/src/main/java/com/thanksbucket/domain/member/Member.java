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

import java.time.LocalDate;

@Entity
@Getter
@Table(name = "members")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private Long id;
    @Column
    private String memberId;
    @Column
    private String password;
    @Column
    private String nickname;
    @Column
    private LocalDate birthday;
    @Column
    private String job;


    public Member(String memberId, String password) {
        this.memberId = memberId;
        this.password = password;
    }

    public Member(String memberId, String password, String nickname, LocalDate birthday, String job) {
        this.memberId = memberId;
        this.password = password;
        this.nickname = nickname;
        this.birthday = birthday;
        this.job = job;
    }
}
