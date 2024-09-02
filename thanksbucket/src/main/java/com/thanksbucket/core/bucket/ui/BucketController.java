package com.thanksbucket.core.bucket.ui;

import com.thanksbucket.core.bucket.command.application.BucketService;
import com.thanksbucket.core.bucket.command.application.StartBucketService;
import com.thanksbucket.core.bucket.command.application.UpdateBucketService;
import com.thanksbucket.core.bucket.query.BucketData;
import com.thanksbucket.core.bucket.ui.dto.StartBucketRequest;
import com.thanksbucket.core.bucket.ui.dto.UpdateBucketRequest;
import com.thanksbucket.security.authentication.userdetails.AuthMember;
import com.thanksbucket.ui.dto.BucketResponse;
import com.thanksbucket.ui.dto.PatchBucketRequest;
import com.thanksbucket.ui.dto.SearchBucketRequest;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import java.net.URI;
import lombok.RequiredArgsConstructor;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Page;
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

@RestController
@RequestMapping("/buckets")
@Tag(name = "buckets", description = "버킷")
@RequiredArgsConstructor
public class BucketController {

  private final BucketService bucketService;
  private final StartBucketService startBucketService;
  private final UpdateBucketService updateBucketService;

  @PostMapping("")
  public ResponseEntity<Void> create(@AuthenticationPrincipal AuthMember authMember,
      @Valid @RequestBody StartBucketRequest request) {
    Long bucketId = startBucketService.start(authMember.getMemberId(), request);
    return ResponseEntity.created(URI.create("/buckets/" + bucketId)).build();
  }

  @GetMapping("")
  public ResponseEntity<Page<BucketResponse>> findAll(
      @AuthenticationPrincipal AuthMember authMember,
      @ParameterObject SearchBucketRequest request) {
    System.out.println("authMember = " + authMember);
    Page<BucketData> buckets = bucketService.findBy(request);
    return ResponseEntity.ok(buckets.map(BucketResponse::new));
  }

  @GetMapping("/{bucketId}")
  public ResponseEntity<BucketResponse> findById(@PathVariable(name = "bucketId") Long bucketId) {
    BucketData bucket = bucketService.findById(bucketId);
    return ResponseEntity.ok(new BucketResponse(bucket));
  }

  @PutMapping("/{bucketId}")
  public ResponseEntity<Void> put(@AuthenticationPrincipal AuthMember authMember,
      @PathVariable(name = "bucketId") Long bucketId,
      @Valid @RequestBody UpdateBucketRequest request) {
    updateBucketService.update(authMember.getMemberId(), bucketId, request);
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
