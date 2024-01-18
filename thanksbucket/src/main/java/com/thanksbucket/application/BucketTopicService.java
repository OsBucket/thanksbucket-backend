package com.thanksbucket.application;

import com.thanksbucket.domain.buckettopic.BucketTopicRepository;
import com.thanksbucket.ui.dto.BucketTopicResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class BucketTopicService {
    private final BucketTopicRepository bucketTopicRepository;

    public List<BucketTopicResponse> findAllByBucketId(Long bucketId) {
        return bucketTopicRepository.findBucketTopicsByBucketId(bucketId)
                .stream().map(BucketTopicResponse::new)
                .collect(Collectors.toList());
    }

}
