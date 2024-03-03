package com.thanksbucket.ui.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.thanksbucket.domain.buckettemplate.BucketTemplate;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
public class BucketTemplateResponse {
    private final Long id;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private final LocalDateTime createdAt;
    private final String bucketName;
    private final List<BucketTemplateTopicResponse> bucketTemplateTopics;
    private final String bucketTodoNames;

    public BucketTemplateResponse(BucketTemplate bucketTemplate) {
        this.id = bucketTemplate.getId();
        this.createdAt = bucketTemplate.getCreatedAt();
        this.bucketName = bucketTemplate.getBucketName();
        this.bucketTemplateTopics = bucketTemplate.getBucketTemplateTopics()
                .stream().map(BucketTemplateTopicResponse::new).toList();
        this.bucketTodoNames = bucketTemplate.getBucketTodoNames();
    }
}
