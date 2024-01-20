package com.thanksbucket.ui.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.thanksbucket.domain.bucket.Bucket;
import lombok.Getter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Getter
public class BucketResponse {
    private final Long id;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private final LocalDateTime createdAt;
    private final String title;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private final LocalDate startDate;
    private final List<BucketTodoResponse> bucketTodos;
    private final List<TopicResponse> bucketTopics;

    public BucketResponse(Bucket bucket) {
        this.id = bucket.getId();
        this.createdAt = bucket.getCreatedAt();
        this.title = bucket.getTitle();
        this.startDate = bucket.getStartDate();
        this.bucketTodos = bucket.getBucketTodos().stream()
                .map(BucketTodoResponse::new)
                .toList();
        this.bucketTopics = bucket.getBucketTopics().stream()
                .map(bucketTopic -> new TopicResponse(bucketTopic.getTopic()))
                .toList();
    }
}
