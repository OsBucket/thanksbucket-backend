package com.thanksbucket.ui.dto;

import com.thanksbucket.domain.buckettemplatetopics.BucketTemplateTopic;
import lombok.Getter;

@Getter
public class BucketTemplateTopicResponse {
    private final Long id;
    private final TopicResponse topic;

    public BucketTemplateTopicResponse(BucketTemplateTopic bucketTemplateTopic) {
        this.id = bucketTemplateTopic.getId();
        this.topic = new TopicResponse(bucketTemplateTopic.getTopic());
    }
}
