package com.thanksbucket.core.bucket.query;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BucketQueryRepository extends JpaRepository<BucketData, Long> {

  Page<BucketData> findAll(Pageable pageable);

  Page<BucketData> findAllByMemberId(Pageable pageable, Long memberId);
}
