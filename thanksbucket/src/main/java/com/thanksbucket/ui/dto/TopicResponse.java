package com.thanksbucket.ui.dto;

import com.thanksbucket.domain.topic.Topic;
import lombok.Getter;

@Getter
public class TopicResponse {
    private final Long id;
    private final String content;

    public TopicResponse(Topic topic) {
        this.id = topic.getId();
        this.content = topic.getContent();
    }
}
