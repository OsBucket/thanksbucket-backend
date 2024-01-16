package com.thanksbucket.application;

import com.thanksbucket.domain.topic.Topic;
import com.thanksbucket.domain.topic.TopicRepository;
import com.thanksbucket.ui.dto.TopicResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TopicService {
    private final TopicRepository topicRepository;

    public List<TopicResponse> findAll() {
        List<Topic> topics = topicRepository.findAll();
        return topics.stream().map(TopicResponse::new).collect(Collectors.toList());
    }
}
