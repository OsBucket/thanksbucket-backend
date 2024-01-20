package com.thanksbucket.ui.api;

import com.thanksbucket.application.BucketService;
import com.thanksbucket.ui.dto.BucketResponse;
import com.thanksbucket.ui.dto.CreateBucketRequest;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/buckets")
@Tag(name = "buckets", description = "버킷")
@RequiredArgsConstructor
public class BucketController {
    private final BucketService bucketService;

    @PostMapping("")
    public ResponseEntity<Void> create(@AuthenticationPrincipal User user, @Valid @RequestBody CreateBucketRequest request) {
        bucketService.create(user.getUsername(), request);

        return ResponseEntity.ok().build();
    }

    @GetMapping("")
    public ResponseEntity<List<BucketResponse>> findAll(@AuthenticationPrincipal User user) {
        return ResponseEntity.ok(bucketService.findAll(user.getUsername()));
    }

    @GetMapping("/{bucketId}")
    public ResponseEntity<BucketResponse> findOne(@AuthenticationPrincipal User user, @PathVariable Long bucketId) {
        return ResponseEntity.ok(bucketService.findById(user.getUsername(), bucketId));
    }
}
