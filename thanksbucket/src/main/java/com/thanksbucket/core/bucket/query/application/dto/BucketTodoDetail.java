package com.thanksbucket.core.bucket.query.application.dto;

import com.thanksbucket.core.bucket.command.domain.BucketTodo;
import com.thanksbucket.core.bucket.command.domain.ProcessStatus;
import lombok.Getter;

@Getter
public class BucketTodoDetail {

  private final Long id;
  private final String content;
  private final ProcessStatus todoStatus;

  public BucketTodoDetail(BucketTodo bucketTodo) {
    this.id = bucketTodo.getId();
    this.content = bucketTodo.getContent();
    this.todoStatus = bucketTodo.getTodoStatus();
  }
}
