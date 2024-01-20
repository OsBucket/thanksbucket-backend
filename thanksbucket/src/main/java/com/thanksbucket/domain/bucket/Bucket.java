package com.thanksbucket.domain.bucket;

import com.thanksbucket.domain.buckettodo.BucketTodo;
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

    @OneToMany(mappedBy = "bucket", cascade = CascadeType.ALL)
    private List<BucketTodo> bucketTodos = new ArrayList<>();


    public Bucket(String title, LocalDate startDate, Member member) {
        this.title = title;
        this.startDate = startDate;
        this.member = member;
    }

    public static Bucket create(String title, LocalDate startDate, Member member) {
        Bucket bucket = new Bucket(title, startDate, member);
        bucket.createdAt = LocalDateTime.now();
        return bucket;
    }

    public void resetTopics(List<Topic> topics) {
        this.bucketTopics = topics.stream().map(topic -> BucketTopic.create(this, topic)).collect(Collectors.toList());
    }

    public void addTodo(String content, Boolean isDone) {
        this.bucketTodos.add(BucketTodo.create(content, isDone, this));
    }

    public void validateOwner(Member member) {
        if (!this.member.equals(member)) {
            throw new IllegalArgumentException("해당 버킷에 대한 권한이 없습니다.");
        }
    }
}
