package com.thanksbucket.core.bucket.command.application;

import com.thanksbucket.core.bucket.command.application.dto.UpdateBucketRequest;
import com.thanksbucket.core.bucket.command.domain.Bucket;
import com.thanksbucket.core.bucket.command.domain.BucketGoalDate;
import com.thanksbucket.core.bucket.command.domain.BucketRepository;
import com.thanksbucket.core.bucket.command.domain.BucketTodo;
import com.thanksbucket.core.member.domain.Member;
import com.thanksbucket.core.member.domain.MemberRepository;
import com.thanksbucket.core.topic.domain.Topic;
import com.thanksbucket.core.topic.domain.TopicRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class UpdateBucketService {

  private final BucketRepository bucketRepository;
  private final TopicRepository topicRepository;
  private final MemberRepository memberRepository;

  public void update(Long memberId, Long bucketId, UpdateBucketRequest request) {
    Member member = memberRepository.findById(memberId)
        .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 멤버입니다."));

    Bucket bucket = bucketRepository.findById(bucketId)
        .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 버킷입니다."));
    bucket.update(member, request.getTitle(), BucketGoalDate.from(request.getGoalDate()));

    List<BucketTodo> bucketTodos = request.toBucketTodos();
    bucket.syncTodo(bucketTodos);

    List<Topic> topics = topicRepository.findAllById(request.getTopicIds());
    bucket.syncTopics(topics);
  }
}
