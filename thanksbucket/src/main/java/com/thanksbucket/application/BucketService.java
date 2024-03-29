package com.thanksbucket.application;

import com.thanksbucket.domain.bucket.Bucket;
import com.thanksbucket.domain.bucket.BucketRepository;
import com.thanksbucket.domain.member.Member;
import com.thanksbucket.domain.member.MemberRepository;
import com.thanksbucket.domain.topic.Topic;
import com.thanksbucket.domain.topic.TopicRepository;
import com.thanksbucket.ui.dto.BucketResponse;
import com.thanksbucket.ui.dto.CreateBucketRequest;
import com.thanksbucket.ui.dto.CreateBucketTodoRequest;
import com.thanksbucket.ui.dto.PatchBucketRequest;
import com.thanksbucket.ui.dto.UpdateBucketRequest;
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

        Bucket bucket = Bucket.create(request.getTitle(), request.getGoalDate(), member);
        bucket.addTopics(topics);
        bucket.addTodos(request.getBucketTodos()
                .stream()
                .map(CreateBucketTodoRequest::toEntity)
                .toList());
        return bucketRepository.save(bucket).getId();
    }

    public List<BucketResponse> findAll(String memberId) {
        Member member = memberRepository.findByMemberId(memberId).orElseThrow(() -> new IllegalArgumentException("존재하지 않는 회원입니다."));
        List<Bucket> buckets = bucketRepository.findBucketsByMemberOrderByCreatedAtDesc(member);
        return buckets.stream().map(BucketResponse::new).collect(Collectors.toList());
    }

    public BucketResponse findById(String memberId, Long bucketId) {
        Member member = memberRepository.findByMemberId(memberId).orElseThrow(() -> new IllegalArgumentException("존재하지 않는 회원입니다."));
        Bucket bucket = bucketRepository.findById(bucketId).orElseThrow(() -> new IllegalArgumentException("존재하지 않는 버킷입니다."));
        bucket.validateOwner(member);
        return new BucketResponse(bucket);
    }

    @Transactional
    public Long update(String memberId, Long bucketId, UpdateBucketRequest request) {
        Member member = memberRepository.findByMemberId(memberId).orElseThrow(() -> new IllegalArgumentException("존재하지 않는 회원입니다."));
        Bucket bucket = bucketRepository.findById(bucketId).orElseThrow(() -> new IllegalArgumentException("존재하지 않는 버킷입니다."));
        List<Topic> topics = topicRepository.findAllById(request.getTopicIds());

        bucket.update(member, request.getTitle(), request.getGoalDate());
        bucket.updateTopics(topics);
        bucket.updateTodos(request.getBucketTodos()
                .stream()
                .map(CreateBucketTodoRequest::toEntity)
                .toList());
        // TODO 리팩토링 필요
        bucket.updateIsDone(request.getIsDone());
        return bucket.getId();
    }

    @Transactional
    public Long patch(String memberId, Long bucketId, PatchBucketRequest request) {
        Member member = memberRepository.findByMemberId(memberId).orElseThrow(() -> new IllegalArgumentException("존재하지 않는 회원입니다."));
        Bucket bucket = bucketRepository.findById(bucketId).orElseThrow(() -> new IllegalArgumentException("존재하지 않는 버킷입니다."));
        bucket.validateOwner(member);
        bucket.updateIsDone(request.getIsDone());
        return bucket.getId();
    }


    @Transactional
    public void delete(String memberId, Long bucketId) {
        Member member = memberRepository.findByMemberId(memberId).orElseThrow(() -> new IllegalArgumentException("존재하지 않는 회원입니다."));
        Bucket bucket = bucketRepository.findById(bucketId).orElseThrow(() -> new IllegalArgumentException("존재하지 않는 버킷입니다."));
        bucket.validateOwner(member);
        bucketRepository.delete(bucket);
    }
}
