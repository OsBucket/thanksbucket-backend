package com.thanksbucket.domain.buckettopic;

import com.thanksbucket.domain.bucket.Bucket;
import com.thanksbucket.domain.common.BaseTimeEntity;
import com.thanksbucket.domain.topic.Topic;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "bucket_topics")
public class BucketTopic extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "bucket_id", nullable = false)
    private Bucket bucket;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "topic_id", nullable = false)
    private Topic topic;

    public BucketTopic(Bucket bucket, Topic topic) {
        this.bucket = bucket;
        this.topic = topic;
    }

    public static BucketTopic create(Bucket bucket, Topic topic) {
        return new BucketTopic(bucket, topic);
    }
}
