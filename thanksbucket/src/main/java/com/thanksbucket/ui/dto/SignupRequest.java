package com.thanksbucket.ui.dto;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.thanksbucket.domain.member.Member;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDate;

@AllArgsConstructor
@Getter
public class SignupRequest {
    @NotBlank
    @Size(min = 2, max = 10)
    private String memberId;

    @NotBlank
    @Size(min = 8, max = 16)
    private String password;

    @NotBlank
    @Size(min = 2, max = 8)
    private String nickname;

    @JsonFormat(pattern = "yyyy-MM-dd", shape = JsonFormat.Shape.STRING)
    private LocalDate birthday;

    private String job;

    public Member toEntity() {
        return new Member(memberId, password, nickname, birthday, job);
    }
}
