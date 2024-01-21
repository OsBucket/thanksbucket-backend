package com.thanksbucket.domain.buckettodo;

import com.thanksbucket.domain.bucket.Bucket;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class BucketTodo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private Long id;

    @Column(nullable = false)
    private LocalDateTime createdAt;

    @Column(nullable = false)
    private String content;

    @Column(nullable = false)
    private boolean isDone;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "bucket_id", nullable = false)
    private Bucket bucket;

    public BucketTodo(String content, boolean isDone, Bucket bucket) {
        this.content = content;
        this.isDone = isDone;
        this.bucket = bucket;
    }

    public static BucketTodo create(String content, boolean isDone) {
        BucketTodo bucketTodo = new BucketTodo(content, isDone, null);
        bucketTodo.createdAt = LocalDateTime.now();
        return bucketTodo;
    }

    public void setBucket(Bucket bucket) {
        this.bucket = bucket;
    }
}
