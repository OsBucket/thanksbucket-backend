package com.thanksbucket.ui.dto;

import com.thanksbucket.domain.buckettodo.BucketTodo;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class CreateBucketTodoRequest {
    @NotBlank
    private String content;
    @NotNull
    private Boolean isDone;

    public BucketTodo toEntity() {
        return BucketTodo.create(content, isDone);
    }
}
