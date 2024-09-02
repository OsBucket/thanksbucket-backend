package com.thanksbucket.core.topic.application;

import com.thanksbucket.core.topic.domain.TopicRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TopicService {

  private final TopicRepository topicRepository;

  public List<TopicResponse> findAll() {
    return topicRepository.findAll()
        .stream().map(TopicResponse::new)
        .toList();
  }
}
