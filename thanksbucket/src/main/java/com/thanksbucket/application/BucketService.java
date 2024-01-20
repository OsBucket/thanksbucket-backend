package com.thanksbucket.application;

import com.thanksbucket.domain.bucket.Bucket;
import com.thanksbucket.domain.bucket.BucketRepository;
import com.thanksbucket.domain.member.Member;
import com.thanksbucket.domain.member.MemberRepository;
import com.thanksbucket.domain.topic.Topic;
import com.thanksbucket.domain.topic.TopicRepository;
import com.thanksbucket.ui.dto.BucketResponse;
import com.thanksbucket.ui.dto.CreateBucketRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class BucketService {
    private final MemberRepository memberRepository;
    private final BucketRepository bucketRepository;
    private final TopicRepository topicRepository;

    @Transactional
    public Long create(String memberId, CreateBucketRequest request) {
        Member member = memberRepository.findByMemberId(memberId).orElseThrow(() -> new IllegalArgumentException("존재하지 않는 회원입니다."));
        List<Topic> topics = topicRepository.findAllById(request.getTopicIds());

        Bucket bucket = Bucket.create(request.getTitle(), request.getStartDate(), member);
        bucket.resetTopics(topics);
        request.getBucketTodos()
                .forEach(todo -> bucket.addTodo(todo.getContent(), todo.getIsDone()));
        return bucketRepository.save(bucket).getId();
    }

    public List<BucketResponse> findAll(String memberId) {
        Member member = memberRepository.findByMemberId(memberId).orElseThrow(() -> new IllegalArgumentException("존재하지 않는 회원입니다."));
        List<Bucket> buckets = bucketRepository.findBucketsByMember(member);
        return buckets.stream().map(BucketResponse::new).collect(Collectors.toList());
    }

    public BucketResponse findById(String memberId, Long bucketId) {
        Member member = memberRepository.findByMemberId(memberId).orElseThrow(() -> new IllegalArgumentException("존재하지 않는 회원입니다."));
        Bucket bucket = bucketRepository.findById(bucketId).orElseThrow(() -> new IllegalArgumentException("존재하지 않는 버킷입니다."));
        bucket.validateOwner(member);
        return new BucketResponse(bucket);
    }
}
