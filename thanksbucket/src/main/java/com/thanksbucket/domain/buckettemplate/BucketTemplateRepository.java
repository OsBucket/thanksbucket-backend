package com.thanksbucket.domain.buckettemplate;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BucketTemplateRepository extends JpaRepository<BucketTemplate, Long> {

    List<BucketTemplate> findBucketTemplateByBucketNameContaining(String bucketName);
}
