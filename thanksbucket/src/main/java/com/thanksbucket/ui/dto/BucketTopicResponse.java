package com.thanksbucket.ui.dto;

import com.thanksbucket.domain.buckettopic.BucketTopic;
import lombok.Getter;

@Getter
public class BucketTopicResponse {
    private final BucketResponse bucket;
    private final TopicResponse topic;

    public BucketTopicResponse(BucketTopic bucketTopic) {
        this.bucket = new BucketResponse(bucketTopic.getBucket());
        this.topic = new TopicResponse(bucketTopic.getTopic());
    }
}
