package com.thanksbucket.domain.bucket;

import com.thanksbucket.domain.member.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BucketRepository extends JpaRepository<Bucket, Long> {
    List<Bucket> findBucketsByMemberOrderByCreatedAtDesc(Member member);
}
