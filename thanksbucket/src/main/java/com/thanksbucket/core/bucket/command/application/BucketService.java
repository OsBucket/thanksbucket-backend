package com.thanksbucket.core.bucket.command.application;

import com.thanksbucket.core.bucket.command.domain.Bucket;
import com.thanksbucket.core.bucket.command.domain.BucketRepository;
import com.thanksbucket.core.member.domain.Member;
import com.thanksbucket.core.member.domain.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class BucketService {

  private final BucketRepository bucketRepository;
  private final MemberRepository memberRepository;

//  @Transactional
//  public Long patch(Long memberId, Long bucketId, FinishBucketRequest request) {
//    Member member = memberService.findById(memberId);
//    BucketData bucket = this.findById(bucketId);
////    bucket.validateOwner(member);
////    bucket.updateIsDone(request.getIsDone());
//    return bucket.getId();
//  }


  @Transactional
  public void delete(Long memberId, Long bucketId) {
    Member member = memberRepository.findById(memberId)
        .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 멤버입니다."));
    Bucket bucket = this.bucketRepository.findById(bucketId)
        .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 버킷입니다."));
    bucket.canChange(member);
    bucketRepository.delete(bucket);
  }
}
