package com.thanksbucket.ui.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class SearchBucketRequest {
    @Schema(description = "멤버 닉네임")
    private String nickname;
}
