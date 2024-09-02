package com.thanksbucket.core.bucket.command.domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

class BucketTodoTest {

  @Test
  void 버킷할일생성_성공() {
    BucketTodo.start("할일");
  }

  @Test
  void 버킷할일생성_done최초값False() {
    BucketTodo bucketTodo = BucketTodo.start("할일");
    assertThat(bucketTodo.isDone()).isFalse();
  }
}