package com.thanksbucket.ui.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.thanksbucket.domain.buckettodo.BucketTodo;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class BucketTodoResponse {
    private final Long id;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private final LocalDateTime createdAt;
    private final String content;
    private final Boolean isDone;

    public BucketTodoResponse(BucketTodo bucketTodo) {
        this.id = bucketTodo.getId();
        this.createdAt = bucketTodo.getCreatedAt();
        this.content = bucketTodo.getContent();
        this.isDone = bucketTodo.isDone();
    }
}
