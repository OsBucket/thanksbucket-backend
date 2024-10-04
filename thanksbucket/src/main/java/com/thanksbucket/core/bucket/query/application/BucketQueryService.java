package com.thanksbucket.core.bucket.query.application;

import com.thanksbucket.application.MemberService;
import com.thanksbucket.core.bucket.command.domain.BucketTopic;
import com.thanksbucket.core.bucket.query.application.dto.BucketDetail;
import com.thanksbucket.core.bucket.query.domain.BucketData;
import com.thanksbucket.core.bucket.query.domain.BucketQueryRepository;
import com.thanksbucket.core.member.domain.Member;
import com.thanksbucket.core.topic.domain.Topic;
import com.thanksbucket.core.topic.domain.TopicRepository;
import com.thanksbucket.ui.dto.SearchBucketRequest;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class BucketQueryService {

  private final BucketQueryRepository bucketQueryRepository;
  private final TopicRepository topicRepository;
  private final MemberService memberService;

  public BucketDetail findById(Long bucketId) {
    BucketData bucketData = bucketQueryRepository.findById(bucketId)
        .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 버킷입니다."));
    List<Topic> topics = topicRepository.findAllById(
        bucketData.getBucketTopics().stream().map(
            BucketTopic::getTopicId).toList());
    Member member = memberService.findById(bucketData.getMemberId());
    return new BucketDetail(bucketData, topics, member);
  }

  public Page<BucketDetail> findBy(@ParameterObject SearchBucketRequest request) {
    // TODO 쿼리 파라미터 리팩토링
    if (request.getNickname() != null) {
      Member member = memberService.findByNickname(request.getNickname());
      Page<BucketData> buckets = bucketQueryRepository.findAllByMemberId(request.toPageable(),
          member.getId());
      return buckets.map(
          bucketData -> {
            List<Topic> topics = topicRepository.findAllById(
                bucketData.getBucketTopics().stream().map(
                    BucketTopic::getTopicId).toList());
            return new BucketDetail(bucketData, topics, member);
          });
    }

    Page<BucketData> buckets = bucketQueryRepository.findAll(request.toPageable());
    return buckets.map(
        bucketData -> {
          List<Topic> topics = topicRepository.findAllById(
              bucketData.getBucketTopics().stream().map(
                  BucketTopic::getTopicId).toList());
          Member member = memberService.findById(bucketData.getMemberId());
          return new BucketDetail(bucketData, topics, member);
        });
  }
}
