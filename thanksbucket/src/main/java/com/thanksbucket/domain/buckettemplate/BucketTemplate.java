package com.thanksbucket.domain.buckettemplate;

import com.thanksbucket.domain.buckettemplatetopics.BucketTemplateTopic;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "bucket_template")
public class BucketTemplate {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private Long id;

    @Column(nullable = false)
    private LocalDateTime createdAt;

    @Column(nullable = false)
    private String bucketName;

    @OneToMany(mappedBy = "bucketTemplate", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<BucketTemplateTopic> bucketTemplateTopics = new ArrayList<>();

    @Column(nullable = false)
    private String bucketTodoNames;

    public BucketTemplate(String bucketName, List<BucketTemplateTopic> bucketTemplateTopics, String bucketTodoNames) {
        this.bucketName = bucketName;
        this.bucketTemplateTopics = bucketTemplateTopics;
        this.bucketTodoNames = bucketTodoNames;
    }
}
