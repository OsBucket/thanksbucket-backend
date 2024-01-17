package com.thanksbucket.ui.dto;

import com.thanksbucket.domain.bucket.Bucket;
import lombok.Getter;

import java.time.LocalDate;

@Getter
public class BucketResponse {
    private final String title;
    private final LocalDate startDate;

    public BucketResponse(Bucket bucket) {
        this.title = bucket.getTitle();
        this.startDate = bucket.getStartDate();
    }
}
