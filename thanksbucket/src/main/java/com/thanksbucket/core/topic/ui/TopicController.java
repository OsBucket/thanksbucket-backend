package com.thanksbucket.core.topic.ui;

import com.thanksbucket.core.topic.application.TopicResponse;
import com.thanksbucket.core.topic.application.TopicService;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/topics")
@Tag(name = "topics", description = "토픽")
@RequiredArgsConstructor
public class TopicController {

  private final TopicService topicService;

  @GetMapping("")
  public ResponseEntity<List<TopicResponse>> findAll() {
    List<TopicResponse> topics = topicService.findAll();
    return ResponseEntity.ok(topics);
  }
}
