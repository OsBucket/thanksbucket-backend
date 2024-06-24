package com.thanksbucket.domain.bucket;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BucketRepository extends JpaRepository<Bucket, Long> {
    Page<Bucket> findAll(Pageable pageable);

    Page<Bucket> findAllByMemberId(Pageable pageable, Long memberId);
}
