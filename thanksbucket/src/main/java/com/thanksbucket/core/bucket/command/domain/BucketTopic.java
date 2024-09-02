package com.thanksbucket.core.bucket.command.domain;

import com.thanksbucket.base.domain.DomainEntity;
import com.thanksbucket.core.topic.domain.Topic;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity(name = "bucket_topics")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class BucketTopic extends DomainEntity<BucketTopic, Long> {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "bucket_topic_id")
  private Long id;

  @Column
  private Long topicId;

  private BucketTopic(Long topicId) {
    this.topicId = topicId;
  }

  public static BucketTopic from(Topic topic) {
    return new BucketTopic(topic.getId());
  }
}
