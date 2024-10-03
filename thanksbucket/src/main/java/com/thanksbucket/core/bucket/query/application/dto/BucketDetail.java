package com.thanksbucket.core.bucket.query.application.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.thanksbucket.core.bucket.command.domain.ProcessStatus;
import com.thanksbucket.core.bucket.query.domain.BucketData;
import com.thanksbucket.core.member.application.dto.MemberDetail;
import com.thanksbucket.core.member.domain.Member;
import com.thanksbucket.core.topic.domain.Topic;
import java.time.LocalDate;
import java.util.List;
import lombok.Getter;

@Getter
public class BucketDetail {

  private final Long id;
  private final String title;
  @JsonFormat(pattern = "yyyy-MM-dd")
  private final LocalDate goalDate;
  private final ProcessStatus bucketStatus;
  private final List<BucketTodoDetail> bucketTodos;
  private final List<BucketTopicDetail> bucketTopics;
  private final MemberDetail member;

  public BucketDetail(BucketData bucket, List<Topic> topics, Member member) {
    this.id = bucket.getId();
    this.title = bucket.getTitle();
    this.goalDate = bucket.getBucketGoalDate().getGoalDate();
    this.bucketStatus = bucket.getBucketStatus();
    this.bucketTodos = bucket.getBucketTodos().stream()
        .map(BucketTodoDetail::new)
        .toList();
    this.bucketTopics = topics.stream()
        .map(BucketTopicDetail::new)
        .toList();
    this.member = new MemberDetail(member);
  }
}
