package com.thanksbucket.application;

import com.thanksbucket.domain.bucket.Bucket;
import com.thanksbucket.domain.bucket.BucketRepository;
import com.thanksbucket.domain.member.Member;
import com.thanksbucket.domain.topic.Topic;
import com.thanksbucket.domain.topic.TopicRepository;
import com.thanksbucket.ui.dto.CreateBucketRequest;
import com.thanksbucket.ui.dto.CreateBucketTodoRequest;
import com.thanksbucket.ui.dto.PatchBucketRequest;
import com.thanksbucket.ui.dto.SearchBucketRequest;
import com.thanksbucket.ui.dto.UpdateBucketRequest;
import lombok.RequiredArgsConstructor;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class BucketService {
    private final BucketRepository bucketRepository;
    private final MemberService memberService;
    private final TopicRepository topicRepository;

    public Bucket findById(Long bucketId) {
        return bucketRepository.findById(bucketId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 버킷입니다."));
    }

    public List<Bucket> findBy(@ParameterObject SearchBucketRequest request) {
        // TODO 쿼리 파라미터 리팩토링
        if (request.getNickname() != null) {
            return this.findByNickname(request.getNickname());
        }
        return this.findAll();
    }

    private List<Bucket> findAll() {
        return bucketRepository.findAllByOrderByIdDesc();
    }

    private List<Bucket> findByNickname(String nickname) {
        Member member = memberService.findByNickname(nickname);
        return bucketRepository.findAllByMemberId(member.getId());
    }

    @Transactional
    public Long create(Long memberId, CreateBucketRequest request) {
        Member member = memberService.findById(memberId);
        List<Topic> topics = topicRepository.findAllById(request.getTopicIds());

        Bucket bucket = Bucket.create(request.getTitle(), request.getGoalDate(), member);
        bucket.addTopics(topics);
        bucket.addTodos(request.getBucketTodos()
                .stream()
                .map(CreateBucketTodoRequest::toEntity)
                .toList());
        return bucketRepository.save(bucket).getId();
    }

    @Transactional
    public Long update(Long memberId, Long bucketId, UpdateBucketRequest request) {
        Member member = memberService.findById(memberId);
        Bucket bucket = this.findById(bucketId);
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
    public Long patch(Long memberId, Long bucketId, PatchBucketRequest request) {
        Member member = memberService.findById(memberId);
        Bucket bucket = this.findById(bucketId);
        bucket.validateOwner(member);
        bucket.updateIsDone(request.getIsDone());
        return bucket.getId();
    }


    @Transactional
    public void delete(Long memberId, Long bucketId) {
        Member member = memberService.findById(memberId);
        Bucket bucket = this.findById(bucketId);
        bucket.validateOwner(member);
        bucketRepository.delete(bucket);
    }
}
