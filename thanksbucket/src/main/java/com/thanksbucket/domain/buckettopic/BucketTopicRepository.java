package com.thanksbucket.domain.buckettopic;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BucketTopicRepository extends JpaRepository<BucketTopic, Long> {
    List<BucketTopic> findBucketTopicsByBucketId(Long bucketId);
}
