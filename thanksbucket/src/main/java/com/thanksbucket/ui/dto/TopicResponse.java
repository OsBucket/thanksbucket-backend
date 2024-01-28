package com.thanksbucket.ui.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.thanksbucket.domain.topic.Topic;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class TopicResponse {
    private final Long id;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private final LocalDateTime createdAt;
    private final String content;

    public TopicResponse(Topic topic) {
        this.id = topic.getId();
        this.createdAt = topic.getCreatedAt();
        this.content = topic.getContent();
    }
}
