package com.thanksbucket.ui.dto;

import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

@Getter
@Setter
@NoArgsConstructor
public class SearchBucketRequest {
    @Schema(description = "멤버 닉네임")
    private String nickname;

    @Parameter(description = "페이지 번호(0...N)", example = "0", required = true)
    private Integer page;

    @Parameter(description = "페이지 크기", example = "10", required = true)
    private Integer size;

    public Pageable toPageable() {
        return PageRequest.of(page, size);
    }
}
