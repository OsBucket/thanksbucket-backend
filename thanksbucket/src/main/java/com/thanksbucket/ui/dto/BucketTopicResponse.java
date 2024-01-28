package com.thanksbucket.ui.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.thanksbucket.domain.buckettopic.BucketTopic;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class BucketTopicResponse {
    private final Long id;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private final LocalDateTime createdAt;
    private final BucketResponse bucket;
    private final TopicResponse topic;

    public BucketTopicResponse(BucketTopic bucketTopic) {
        this.id = bucketTopic.getId();
        this.createdAt = bucketTopic.getCreatedAt();
        this.bucket = new BucketResponse(bucketTopic.getBucket());
        this.topic = new TopicResponse(bucketTopic.getTopic());
    }
}
