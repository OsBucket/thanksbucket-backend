package com.thanksbucket.core.topic.domain;

import com.thanksbucket.base.domain.AggregateRoot;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = lombok.AccessLevel.PROTECTED)
@Table(name = "topics")
public class Topic extends AggregateRoot<Topic, Long> {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "topic_id")
  private Long id;

  @Column(nullable = false, unique = true)
  private String content;

  public Topic(String content) {
    this.content = content;
  }
}
