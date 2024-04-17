package com.thanksbucket.ui.api;

import com.thanksbucket.application.BucketService;
import com.thanksbucket.ui.dto.BucketResponse;
import com.thanksbucket.ui.dto.CreateBucketRequest;
import com.thanksbucket.ui.dto.PatchBucketRequest;
import com.thanksbucket.ui.dto.UpdateBucketRequest;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/buckets")
@Tag(name = "buckets", description = "버킷")
@RequiredArgsConstructor
public class BucketController {
    private final BucketService bucketService;

    @PostMapping("")
    public ResponseEntity<Void> create(@AuthenticationPrincipal User user, @Valid @RequestBody CreateBucketRequest request) {
        Long bucketId = bucketService.create(user.getUsername(), request);
        return ResponseEntity.created(URI.create("/api/buckets/" + bucketId)).build();
    }

    @GetMapping("")
    public ResponseEntity<List<BucketResponse>> findAll(@AuthenticationPrincipal User user) {
        return ResponseEntity.ok(bucketService.findAll(user.getUsername()));
    }

    @GetMapping("/{bucketId}")
    public ResponseEntity<BucketResponse> findOne(@AuthenticationPrincipal User user, @Parameter(name = "bucketId") @PathVariable(name = "bucketId") Long bucketId) {
        return ResponseEntity.ok(bucketService.findById(user.getUsername(), bucketId));
    }

    @PutMapping("/{bucketId}")
    public ResponseEntity<Void> put(@AuthenticationPrincipal User user, @Parameter(name = "bucketId") @PathVariable(name = "bucketId") Long bucketId, @Valid @RequestBody UpdateBucketRequest request) {
        bucketService.update(user.getUsername(), bucketId, request);
        return ResponseEntity.created(URI.create("/api/buckets/" + bucketId)).build();
    }

    @PatchMapping("/{bucketId}")
    public ResponseEntity<Void> patch(@AuthenticationPrincipal User user, @Parameter(name = "bucketId") @PathVariable(name = "bucketId") Long bucketId, @Valid @RequestBody PatchBucketRequest request) {
        bucketService.patch(user.getUsername(), bucketId, request);
        return ResponseEntity.created(URI.create("/api/buckets/" + bucketId)).build();
    }

    @DeleteMapping("/{bucketId}")
    public ResponseEntity<Void> delete(@AuthenticationPrincipal User user, @Parameter(name = "bucketId") @PathVariable(name = "bucketId") Long bucketId) {
        bucketService.delete(user.getUsername(), bucketId);
        return ResponseEntity.noContent().build();
    }
}
