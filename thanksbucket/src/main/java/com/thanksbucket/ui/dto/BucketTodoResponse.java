package com.thanksbucket.ui.dto;

import com.thanksbucket.domain.buckettodo.BucketTodo;
import lombok.Getter;

@Getter
public class BucketTodoResponse {
    private final Long id;
    private final String content;
    private final Boolean isDone;

    public BucketTodoResponse(BucketTodo bucketTodo) {
        this.id = bucketTodo.getId();
        this.content = bucketTodo.getContent();
        this.isDone = bucketTodo.isDone();
    }
}
