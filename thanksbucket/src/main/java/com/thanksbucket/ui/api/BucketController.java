package com.thanksbucket.ui.api;

import com.thanksbucket.application.BucketService;
import com.thanksbucket.domain.bucket.Bucket;
import com.thanksbucket.security.authentication.userdetails.AuthMember;
import com.thanksbucket.ui.dto.BucketResponse;
import com.thanksbucket.ui.dto.CreateBucketRequest;
import com.thanksbucket.ui.dto.PatchBucketRequest;
import com.thanksbucket.ui.dto.SearchBucketRequest;
import com.thanksbucket.ui.dto.UpdateBucketRequest;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
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
    public ResponseEntity<Void> create(@AuthenticationPrincipal AuthMember authMember, @Valid @RequestBody CreateBucketRequest request) {
        Long bucketId = bucketService.create(authMember.getMemberId(), request);
        return ResponseEntity.created(URI.create("/api/buckets/" + bucketId)).build();
    }

    @GetMapping("")
    public ResponseEntity<List<BucketResponse>> findAll(@ParameterObject SearchBucketRequest request) {
        List<Bucket> buckets = bucketService.findBy(request);
        return ResponseEntity.ok(buckets.stream().map(BucketResponse::new).toList());
    }

    @GetMapping("/{bucketId}")
    public ResponseEntity<BucketResponse> findById(@PathVariable(name = "bucketId") Long bucketId) {
        Bucket bucket = bucketService.findById(bucketId);
        return ResponseEntity.ok(new BucketResponse(bucket));
    }

    @PutMapping("/{bucketId}")
    public ResponseEntity<Void> put(@AuthenticationPrincipal AuthMember authMember,
                                    @PathVariable(name = "bucketId") Long bucketId,
                                    @Valid @RequestBody UpdateBucketRequest request) {
        bucketService.update(authMember.getMemberId(), bucketId, request);
        return ResponseEntity.created(URI.create("/api/buckets/" + bucketId)).build();
    }

    @PatchMapping("/{bucketId}")
    public ResponseEntity<Void> patch(@AuthenticationPrincipal AuthMember authMember,
                                      @PathVariable(name = "bucketId") Long bucketId,
                                      @Valid @RequestBody PatchBucketRequest request) {
        bucketService.patch(authMember.getMemberId(), bucketId, request);
        return ResponseEntity.created(URI.create("/api/buckets/" + bucketId)).build();
    }

    @DeleteMapping("/{bucketId}")
    public ResponseEntity<Void> delete(@AuthenticationPrincipal AuthMember authMember,
                                       @PathVariable(name = "bucketId") Long bucketId) {
        bucketService.delete(authMember.getMemberId(), bucketId);
        return ResponseEntity.noContent().build();
    }
}
