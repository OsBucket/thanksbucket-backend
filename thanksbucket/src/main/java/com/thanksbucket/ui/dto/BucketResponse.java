package com.thanksbucket.ui.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.thanksbucket.domain.bucket.Bucket;
import lombok.Getter;

import java.time.LocalDate;
import java.util.List;

@Getter
public class BucketResponse {
    private final Long id;
    private final String title;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private final LocalDate goalDate;

    private final Boolean isDone;
    private final MemberResponse member;
    private final List<BucketTodoResponse> bucketTodos;
    private final List<TopicResponse> bucketTopics;

    public BucketResponse(Bucket bucket) {
        this.id = bucket.getId();
        this.title = bucket.getTitle();
        this.goalDate = bucket.getGoalDate();
        this.isDone = bucket.isDone();
        this.member = new MemberResponse(bucket.getMember());
        this.bucketTodos = bucket.getBucketTodos().stream()
                .map(BucketTodoResponse::new)
                .toList();
        this.bucketTopics = bucket.getBucketTopics().stream()
                .map(bucketTopic -> new TopicResponse(bucketTopic.getTopic()))
                .toList();
    }
}
