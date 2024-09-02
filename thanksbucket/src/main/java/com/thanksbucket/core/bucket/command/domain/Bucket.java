package com.thanksbucket.core.bucket.command.domain;

import com.thanksbucket.base.domain.AggregateRoot;
import com.thanksbucket.core.member.domain.Member;
import com.thanksbucket.core.topic.domain.Topic;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import java.util.List;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity(name = "buckets")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Bucket extends AggregateRoot<Bucket, Long> {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "bucket_id")
  private Long id;

  @Column(nullable = false)
  private String title;

  @Column(nullable = false)
  private BucketGoalDate bucketGoalDate;

  @Column(nullable = false)
  private boolean done;

  @Column(nullable = false, name = "member_id")
  private Long memberId;

  @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true)
  @JoinColumn(name = "bucket_id")
  private List<BucketTodo> bucketTodos;

  @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true)
  @JoinColumn(name = "bucket_id")
  private List<BucketTopic> bucketTopics;

  @Builder
  private Bucket(Long memberId, String title, BucketGoalDate bucketGoalDate, boolean done,
      List<BucketTodo> bucketTodos, List<BucketTopic> bucketTopics) {
    this.memberId = memberId;
    this.title = title;
    this.bucketGoalDate = bucketGoalDate;
    this.done = done;
    this.bucketTodos = bucketTodos;
    this.bucketTopics = bucketTopics;
    this.validate();
  }

  public static Bucket start(Long memberId, String title, BucketGoalDate goalDate,
      List<BucketTodo> bucketTodos, List<Topic> topics) {
    goalDate.validateFuture();
    return Bucket.builder()
        .memberId(memberId)
        .title(title)
        .bucketGoalDate(goalDate)
        .bucketTodos(bucketTodos)
        .bucketTopics(topics.stream().map(BucketTopic::from).toList())
        .build();
  }

  public void update(Member member, String title, BucketGoalDate goalDate) {
    goalDate.validateFuture();
    this.canChange(member);
    this.memberId = member.getId();
    this.title = title;
    this.bucketGoalDate = goalDate;
  }

  public void syncTodo(List<BucketTodo> bucketTodos) {
    if (bucketTodos.isEmpty()) {
      throw new IllegalArgumentException("TODO는 한개 이상 등록해야합니다.");
    }
    this.bucketTodos.clear();
    this.bucketTodos.addAll(bucketTodos);
  }

  public void syncTopics(List<Topic> topics) {
    if (topics.isEmpty()) {
      throw new IllegalArgumentException("토픽은 한개 이상 등록해야합니다.");
    }
    this.bucketTopics.clear();
    topics.forEach(topic -> this.bucketTopics.add(BucketTopic.from(topic)));
  }

  public void bucketFinish(Member member) {
    this.canChange(member);
    this.done = true;
    this.bucketTodos.forEach(BucketTodo::finish);
  }

  public void todoFinish(Member member, Long todoId) {
    this.canChange(member);
    this.bucketTodos.stream()
        .filter(todo -> todo.getId().equals(todoId))
        .findFirst()
        .ifPresent(BucketTodo::finish);
    if (this.bucketTodos.stream().allMatch(BucketTodo::isDone)) {
      this.done = true;
    }
  }

  private void validate() {
    if (this.bucketTopics.isEmpty()) {
      throw new IllegalArgumentException("토픽은 한개 이상 등록해야합니다.");
    }
    if (this.bucketTodos.isEmpty()) {
      throw new IllegalArgumentException("TODO는 한개 이상 등록해야합니다.");
    }
  }

  public void canChange(Member member) {
    if (!this.memberId.equals(member.getId())) {
      throw new IllegalArgumentException("작성자만 수정할 수 있습니다.");
    }
  }
}
