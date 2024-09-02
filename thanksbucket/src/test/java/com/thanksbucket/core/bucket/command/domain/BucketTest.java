package com.thanksbucket.core.bucket.command.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import com.thanksbucket.core.member.domain.Member;
import com.thanksbucket.core.topic.domain.Topic;
import java.time.LocalDate;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.test.util.ReflectionTestUtils;

class BucketTest {

  static final Long memberId = 1L;
  static final Member member = Member.builder().id(memberId).build();
  static final BucketGoalDate 내일 = BucketGoalDate.from(LocalDate.now().plusDays(1));
  BucketTodo 투두;
  Topic 토픽;

  @BeforeEach
  void setUp() {
    투두 = BucketTodo.start("투두");
    토픽 = new Topic("토픽");
  }

  @Test
  void 버킷생성_성공() {
    Bucket.start(memberId, "버킷이름", 내일, List.of(투두), List.of(토픽));
  }

  @Test
  void 버킷생성_토픽0개_실패() {
    assertThatThrownBy(
        () -> Bucket.start(memberId, "버킷이름", 내일, List.of(투두), List.of()))
        .isInstanceOf(IllegalArgumentException.class);
  }

  @Test
  void 버킷생성_투두0개_실패() {
    assertThatThrownBy(
        () -> Bucket.start(memberId, "버킷이름", 내일, List.of(), List.of(토픽)))
        .isInstanceOf(IllegalArgumentException.class);
  }

  @Test
  void 버킷생성_done최초값은_False() {
    Bucket bucket = Bucket.start(memberId, "버킷이름", 내일, List.of(투두), List.of(토픽));
    assertThat(bucket.isDone()).isFalse();
  }

  @Test
  void 버킷수정_성공() {
    Bucket bucket = Bucket.start(memberId, "버킷이름", 내일, List.of(투두), List.of(토픽));

    bucket.update(Member.builder().id(1L).build(), "수정된버킷이름", 내일);

    assertThat(bucket.getTitle()).isEqualTo("수정된버킷이름");
  }

  @Test
  void 버킷수정_다른유저_실패() {
    Bucket bucket = Bucket.start(memberId, "버킷이름", 내일, List.of(투두), List.of(토픽));

    assertThatThrownBy(
        () -> bucket.update(Member.builder().id(2L).build(), "수정된버킷이름", 내일)
    ).isInstanceOf(IllegalArgumentException.class);
  }

  @Test
  void 버킷_Finish되면_하위투두도_Finish() {
    BucketTodo 투두1 = BucketTodo.start("투두1");
    BucketTodo 투두2 = BucketTodo.start("투두2");
    Bucket bucket = Bucket.start(memberId, "버킷이름", 내일, List.of(투두1, 투두2), List.of(토픽));

    bucket.bucketFinish(member);

    assertThat(bucket.isDone()).isTrue();
    assertThat(투두1.isDone()).isTrue();
    assertThat(투두2.isDone()).isTrue();
  }

  @Test
  void 모든투두_Finish되면_버킷도_Finish() {
    BucketTodo 투두1 = BucketTodo.start("투두1");
    BucketTodo 투두2 = BucketTodo.start("투두2");
    ReflectionTestUtils.setField(투두1, "id", 1L);
    ReflectionTestUtils.setField(투두2, "id", 2L);
    Bucket bucket = Bucket.start(memberId, "버킷이름", 내일, List.of(투두1, 투두2), List.of(토픽));

    bucket.todoFinish(member, 1L);
    bucket.todoFinish(member, 2L);

    assertThat(bucket.isDone()).isTrue();
    assertThat(투두1.isDone()).isTrue();
    assertThat(투두2.isDone()).isTrue();
  }
}