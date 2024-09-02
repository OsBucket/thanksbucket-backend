package com.thanksbucket.core.bucket.command.domain;

import com.thanksbucket.base.domain.DomainEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity(name = "bucket_todos")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class BucketTodo extends DomainEntity<BucketTodo, Long> {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "bucket_todo_id")
  private Long id;

  @Column(nullable = false)
  private String content;

  @Column(nullable = false)
  private boolean done;

  private BucketTodo(String content, boolean done) {
    this.content = content;
    this.done = done;
  }

  public static BucketTodo start(String content) {
    return new BucketTodo(content, false);
  }

  public static BucketTodo create(String content, boolean done) {
    return new BucketTodo(content, done);
  }


  public void finish() {
    this.done = true;
  }
}
