package com.thanksbucket.core.bucket.command.application;

import com.thanksbucket.application.MemberService;
import com.thanksbucket.core.bucket.query.BucketData;
import com.thanksbucket.core.bucket.query.BucketQueryRepository;
import com.thanksbucket.core.member.query.domain.Member;
import com.thanksbucket.core.topic.domain.TopicRepository;
import com.thanksbucket.ui.dto.PatchBucketRequest;
import com.thanksbucket.ui.dto.SearchBucketRequest;
import lombok.RequiredArgsConstructor;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class BucketService {

  private final BucketQueryRepository bucketQueryRepository;
  private final MemberService memberService;
  private final TopicRepository topicRepository;

  public BucketData findById(Long bucketId) {
    return bucketQueryRepository.findById(bucketId)
        .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 버킷입니다."));
  }

  public Page<BucketData> findBy(@ParameterObject SearchBucketRequest request) {
    // TODO 쿼리 파라미터 리팩토링
    if (request.getNickname() != null) {
      return this.findByNickname(request.toPageable(), request.getNickname());
    }
    return this.findAll(request.toPageable());
  }

  private Page<BucketData> findAll(Pageable pageable) {
    return bucketQueryRepository.findAll(pageable);
  }

  private Page<BucketData> findByNickname(Pageable pageable, String nickname) {
    Member member = memberService.findByNickname(nickname);
    return bucketQueryRepository.findAllByMemberId(pageable, member.getId());
  }

  @Transactional
  public Long patch(Long memberId, Long bucketId, PatchBucketRequest request) {
    Member member = memberService.findById(memberId);
    BucketData bucket = this.findById(bucketId);
//    bucket.validateOwner(member);
//    bucket.updateIsDone(request.getIsDone());
    return bucket.getId();
  }


  @Transactional
  public void delete(Long memberId, Long bucketId) {
    Member member = memberService.findById(memberId);
    BucketData bucket = this.findById(bucketId);
//    bucket.validateOwner(member);
    bucketQueryRepository.delete(bucket);
  }
}
