package com.thanksbucket.core.bucket.command.domain;

import com.thanksbucket.base.domain.DomainEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

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

  @Enumerated(value = EnumType.STRING)
  @Column(nullable = false)
  @JdbcTypeCode(value = SqlTypes.VARCHAR)
  @ColumnDefault(value = "'START")
  private ProcessStatus todoStatus;

  private BucketTodo(String content, ProcessStatus todoStatus) {
    this.content = content;
    this.todoStatus = todoStatus;
  }

  public static BucketTodo start(String content) {
    return new BucketTodo(content, ProcessStatus.START);
  }

  public static BucketTodo create(String content, ProcessStatus todoStatus) {
    return new BucketTodo(content, todoStatus);
  }

  public void start() {
    this.todoStatus = ProcessStatus.START;
  }

  public void finish() {
    this.todoStatus = ProcessStatus.FINISH;
  }

  public boolean isFinished() {
    return this.todoStatus.isFinish();
  }
}
