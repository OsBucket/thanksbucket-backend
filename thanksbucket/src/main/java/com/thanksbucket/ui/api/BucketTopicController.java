package com.thanksbucket.ui.api;

import com.thanksbucket.application.BucketTopicService;
import com.thanksbucket.ui.dto.BucketTopicResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/bucket-topics")
@Tag(name = "bucket-topics", description = "버킷 토픽")
@RequiredArgsConstructor
public class BucketTopicController {
    private final BucketTopicService bucketTopicService;

    @GetMapping("")
    public ResponseEntity<List<BucketTopicResponse>> findAllByBucketId(@NotNull @RequestParam Long bucketId) {
        return ResponseEntity.ok(bucketTopicService.findAllByBucketId(bucketId));
    }
}
