package com.thanksbucket.core.bucket.command.application;

import com.thanksbucket.core.bucket.command.application.dto.FinishBucketRequest;
import com.thanksbucket.core.bucket.command.domain.Bucket;
import com.thanksbucket.core.bucket.command.domain.BucketRepository;
import com.thanksbucket.core.member.domain.Member;
import com.thanksbucket.core.member.domain.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
@Transactional
public class BucketStatusService {

  private final BucketRepository bucketRepository;
  private final MemberRepository memberRepository;

  public void changeBucketStatus(Long memberId, Long bucketId, FinishBucketRequest request) {
    Member member = memberRepository.findById(memberId)
        .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 멤버입니다."));
    Bucket bucket = bucketRepository.findById(bucketId)
        .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 버킷입니다."));
    bucket.changeBucketStatus(member, request.getStatus());
  }

  public void changeBucketTodoStatus(Long memberId, Long bucketId, Long todoId,
      FinishBucketRequest request) {
    Member member = memberRepository.findById(memberId)
        .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 멤버입니다."));
    Bucket bucket = bucketRepository.findById(bucketId)
        .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 버킷입니다."));
    bucket.changeBucketTodoStatus(member, todoId, request.getStatus());
  }
}
