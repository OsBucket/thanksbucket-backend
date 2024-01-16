package com.thanksbucket.ui.api;

import com.thanksbucket.application.TopicService;
import com.thanksbucket.ui.dto.TopicResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/topics")
@Tag(name = "topics", description = "토픽")
@RequiredArgsConstructor
public class TopicController {
    private final TopicService topicService;

    @GetMapping("")
    public ResponseEntity<List<TopicResponse>> findAll() {
        return ResponseEntity.ok(topicService.findAll());
    }
}
