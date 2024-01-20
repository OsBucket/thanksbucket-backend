package com.thanksbucket.ui.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class CreateBucketTodoRequest {
    @NotBlank
    private String content;
    @NotNull
    private Boolean isDone;
}
