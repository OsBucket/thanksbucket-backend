package com.thanksbucket.core.bucket.command.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import com.thanksbucket.core.topic.domain.Topic;
import java.time.LocalDate;
import java.util.List;
import org.junit.jupiter.api.Test;

class BucketTest {

  static final Long memberId = 1L;
  static final BucketGoalDate 내일 = BucketGoalDate.from(LocalDate.now().plusDays(1));
  static final BucketTodo 할일 = BucketTodo.start("할일");
  static final Topic 토픽 = new Topic("토픽");

  @Test
  void 버킷생성_성공() {
    Bucket.start(memberId, "버킷이름", 내일, List.of(할일), List.of(토픽));
  }


  @Test
  void 버킷생성_토픽0개_실패() {
    assertThatThrownBy(
        () -> Bucket.start(memberId, "버킷이름", 내일, List.of(할일), List.of()))
        .isInstanceOf(IllegalArgumentException.class);
  }

  @Test
  void 버킷생성_투두0개_실패() {
    assertThatThrownBy(
        () -> Bucket.start(memberId, "버킷이름", 내일, List.of(), List.of(토픽)))
        .isInstanceOf(IllegalArgumentException.class);
  }

  @Test
  void 버킷생성_done최초값False() {
    Bucket bucket = Bucket.start(memberId, "버킷이름", 내일, List.of(할일), List.of(토픽));
    assertThat(bucket.isDone()).isFalse();
  }
}