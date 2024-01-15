package com.thanksbucket.application;

import com.thanksbucket.domain.bucket.Bucket;
import com.thanksbucket.domain.bucket.BucketRepository;
import com.thanksbucket.domain.member.Member;
import com.thanksbucket.domain.member.MemberRepository;
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

    @Transactional
    public Long create(String memberId, CreateBucketRequest request) {
        Member member = memberRepository.findByMemberId(memberId).orElseThrow(() -> new IllegalArgumentException("존재하지 않는 회원입니다."));
        Bucket bucket = Bucket.create(member, request.getTitle(), request.getStartDate());
        return bucketRepository.save(bucket).getId();
    }

    public List<BucketResponse> findAll(String memberId) {
        Member member = memberRepository.findByMemberId(memberId).orElseThrow(() -> new IllegalArgumentException("존재하지 않는 회원입니다."));
        List<Bucket> buckets = bucketRepository.findBucketsByMember(member);
        return buckets.stream().map(BucketResponse::new).collect(Collectors.toList());
    }
}
