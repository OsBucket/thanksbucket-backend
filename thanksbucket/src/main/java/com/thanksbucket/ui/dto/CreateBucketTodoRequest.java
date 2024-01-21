package com.thanksbucket.ui.dto;

import com.thanksbucket.domain.buckettodo.BucketTodo;
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
    private boolean isDone;

    public BucketTodo toEntity() {
        return BucketTodo.create(this.getContent(), this.isDone());
    }
}
