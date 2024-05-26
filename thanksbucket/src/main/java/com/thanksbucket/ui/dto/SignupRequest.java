package com.thanksbucket.ui.dto;


import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
public class SignupRequest {
    @NotBlank
    @Size(min = 2, max = 8)
    private String nickname;

    @JsonFormat(pattern = "yyyy-MM-dd", shape = JsonFormat.Shape.STRING)
    private LocalDate birthday;

    private Long occupationId;

    @Size(max = 255)
    private String discoveryPath;
}
