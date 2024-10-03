package com.thanksbucket.core.bucket.query.application.dto;

import com.thanksbucket.core.bucket.command.domain.BucketTodo;
import com.thanksbucket.core.bucket.command.domain.BucketTopic;
import com.thanksbucket.core.bucket.command.domain.ProcessStatus;
import com.thanksbucket.core.topic.domain.Topic;
import lombok.Getter;

@Getter
public class BucketTopicDetail {

  private final Long id;
  private final String content;

  public BucketTopicDetail(Topic topic) {
    this.id = topic.getId();
    this.content = topic.getContent();
  }
}
