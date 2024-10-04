package com.thanksbucket.core.bucket.query.application.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.thanksbucket.core.bucket.command.domain.ProcessStatus;
import com.thanksbucket.core.bucket.query.domain.BucketData;
import java.time.LocalDate;
import lombok.Getter;

@Getter
public class BucketSummary {

  private final Long id;
  private final String title;
  @JsonFormat(pattern = "yyyy-MM-dd")
  private final LocalDate goalDate;
  private final ProcessStatus bucketStatus;
  private final int todoCount;

  public BucketSummary(BucketData bucket) {
    this.id = bucket.getId();
    this.title = bucket.getTitle();
    this.goalDate = bucket.getBucketGoalDate().getGoalDate();
    this.bucketStatus = bucket.getBucketStatus();
    this.todoCount = bucket.getBucketTodos().size();
  }
}
