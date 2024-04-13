package com.thanksbucket.application;

import com.thanksbucket.domain.buckettemplate.BucketTemplate;
import com.thanksbucket.domain.buckettemplate.BucketTemplateRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class BucketTemplateService {
    private final BucketTemplateRepository bucketTemplateRepository;

    public List<BucketTemplate> find() {
        return bucketTemplateRepository.findAllByOrderByBucketName();
    }
}
