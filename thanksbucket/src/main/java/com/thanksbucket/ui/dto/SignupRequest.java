package com.thanksbucket.ui.dto;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.thanksbucket.domain.member.Member;
import lombok.AllArgsConstructor;
import lombok.ToString;

import java.time.LocalDate;

@AllArgsConstructor
@ToString
public class SignupRequest {
    private String memberId;
    private String password;
    private String nickname;
    @JsonFormat(pattern = "yyyy-MM-dd", shape = JsonFormat.Shape.STRING)
    private LocalDate birthday;
    private String job;

    public Member toEntity() {
        return new Member(memberId, password, nickname, birthday, job);
    }
}
