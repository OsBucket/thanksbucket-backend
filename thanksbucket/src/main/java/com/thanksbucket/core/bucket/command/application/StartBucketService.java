package com.thanksbucket.core.bucket.command.application;

import com.thanksbucket.core.bucket.command.domain.Bucket;
import com.thanksbucket.core.bucket.command.domain.BucketGoalDate;
import com.thanksbucket.core.bucket.command.domain.BucketRepository;
import com.thanksbucket.core.bucket.command.domain.BucketTodo;
import com.thanksbucket.core.bucket.ui.dto.StartBucketRequest;
import com.thanksbucket.core.topic.domain.Topic;
import com.thanksbucket.core.topic.domain.TopicRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class StartBucketService {

  private final BucketRepository bucketRepository;
  private final TopicRepository topicRepository;

  public Long start(Long memberId, StartBucketRequest request) {
    List<Topic> topics = topicRepository.findAllById(request.getTopicIds());
    List<BucketTodo> bucketTodos = request.toBucketTodos();
    Bucket bucket = Bucket.start(
        memberId, request.getTitle(), BucketGoalDate.from(request.getGoalDate()),
        bucketTodos, topics);
    bucketRepository.save(bucket);
    return bucket.getId();
  }
}
