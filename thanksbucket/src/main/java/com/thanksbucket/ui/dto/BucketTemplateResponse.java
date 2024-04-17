package com.thanksbucket.ui.dto;

import com.thanksbucket.domain.buckettemplate.BucketTemplate;
import lombok.Getter;

import java.util.List;

@Getter
public class BucketTemplateResponse {
    private final Long id;
    private final String bucketName;
    private final List<BucketTemplateTopicResponse> bucketTemplateTopics;
    private final String bucketTodoNames;

    public BucketTemplateResponse(BucketTemplate bucketTemplate) {
        this.id = bucketTemplate.getId();
        this.bucketName = bucketTemplate.getBucketName();
        this.bucketTemplateTopics = bucketTemplate.getBucketTemplateTopics()
                .stream().map(BucketTemplateTopicResponse::new).toList();
        this.bucketTodoNames = bucketTemplate.getBucketTodoNames();
    }
}
