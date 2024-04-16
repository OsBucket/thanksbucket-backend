package com.thanksbucket.domain.bucket;

import com.thanksbucket.domain.buckettodo.BucketTodo;
import com.thanksbucket.domain.buckettopic.BucketTopic;
import com.thanksbucket.domain.common.BaseTimeEntity;
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
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "buckets")
public class Bucket extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private Long id;

    @Column
    private String title;

    @Column
    private LocalDate goalDate;

    @Column(nullable = false)
    private boolean isDone;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @OneToMany(mappedBy = "bucket", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<BucketTopic> bucketTopics = new ArrayList<>();

    @OneToMany(mappedBy = "bucket", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<BucketTodo> bucketTodos = new ArrayList<>();


    public Bucket(String title, LocalDate goalDate, boolean isDone, Member member) {
        this.title = title;
        this.goalDate = goalDate;
        this.isDone = isDone;
        this.member = member;
    }

    public static Bucket create(String title, LocalDate goalDate, Member member) {
        Bucket bucket = new Bucket(title, goalDate, false, member);
        return bucket;
    }

    public void addTopics(List<Topic> topics) {
        this.bucketTopics.addAll(topics.stream().map(topic -> BucketTopic.create(this, topic)).toList());
    }

    public void addTodos(List<BucketTodo> todos) {
        todos.forEach(todo -> todo.setBucket(this));
        this.bucketTodos.addAll(todos);
    }

    public void validateOwner(Member member) {
        if (!this.member.equals(member)) {
            throw new IllegalArgumentException("해당 버킷에 대한 권한이 없습니다.");
        }
    }

    public void update(Member member, String title, LocalDate goalDate) {
        this.validateOwner(member);
        this.bucketTopics.clear();
        this.bucketTodos.clear();
        this.title = title;
        this.goalDate = goalDate;
    }

    public void updateTopics(List<Topic> topics) {
        this.bucketTopics.clear();
        this.addTopics(topics);
    }

    public void updateTodos(List<BucketTodo> bucketTodos) {
        this.bucketTodos.clear();
        this.addTodos(bucketTodos);
    }

    public void updateIsDone(boolean isDone) {
        if (!isDone) {
            this.isDone = false;
            return;
        }
        bucketTodos.forEach(BucketTodo::done);
        this.isDone = true;
    }
}
