package com.thanksbucket.ui.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.thanksbucket.domain.buckettemplatetopics.BucketTemplateTopic;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class BucketTemplateTopicResponse {
    private final Long id;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private final LocalDateTime createdAt;
    private final TopicResponse topic;

    public BucketTemplateTopicResponse(BucketTemplateTopic bucketTemplateTopic) {
        this.id = bucketTemplateTopic.getId();
        this.createdAt = bucketTemplateTopic.getCreatedAt();
        this.topic = new TopicResponse(bucketTemplateTopic.getTopic());
    }
}
