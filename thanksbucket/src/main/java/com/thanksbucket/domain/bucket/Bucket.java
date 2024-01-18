package com.thanksbucket.domain.bucket;

import com.thanksbucket.domain.buckettopic.BucketTopic;
import com.thanksbucket.domain.member.Member;
import com.thanksbucket.domain.topic.Topic;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "buckets")
public class Bucket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private Long id;

    @Column(nullable = false)
    private LocalDateTime createdAt;

    @Column
    private String title;

    @Column
    private LocalDate startDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @OneToMany(mappedBy = "bucket", cascade = CascadeType.ALL)
    private List<BucketTopic> bucketTopics = new ArrayList<>();


    public Bucket(String title, LocalDate startDate, Member member, List<BucketTopic> bucketTopics) {
        this.title = title;
        this.startDate = startDate;
        this.member = member;
        this.bucketTopics = bucketTopics;
    }

    public static Bucket create(Member member, String title, LocalDate startDate, List<Topic> topics) {
        Bucket bucket = new Bucket(title, startDate, member, null);
        bucket.createdAt = LocalDateTime.now();
        bucket.bucketTopics = topics.stream().map(topic -> new BucketTopic(LocalDateTime.now(), bucket, topic)).collect(Collectors.toList());
        return bucket;
    }
}
