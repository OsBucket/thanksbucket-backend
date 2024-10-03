package com.thanksbucket.core.bucket.query.domain;

import com.thanksbucket.base.domain.BaseTimeEntity;
import com.thanksbucket.core.bucket.command.domain.BucketGoalDate;
import com.thanksbucket.core.bucket.command.domain.BucketTodo;
import com.thanksbucket.core.bucket.command.domain.BucketTopic;
import com.thanksbucket.core.bucket.command.domain.ProcessStatus;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.util.List;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "buckets")
public class BucketData extends BaseTimeEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "bucket_id")
  private Long id;

  @Column(nullable = false)
  private String title;

  @Column(nullable = false)
  private BucketGoalDate bucketGoalDate;

  @Enumerated(value = EnumType.STRING)
  @Column(nullable = false)
  @JdbcTypeCode(value = SqlTypes.VARCHAR)
  @ColumnDefault(value = "'START")
  private ProcessStatus bucketStatus;

  @Column(nullable = false, name = "member_id")
  private Long memberId;

  @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true)
  @JoinColumn(name = "bucket_id")
  private List<BucketTodo> bucketTodos;

  @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true)
  @JoinColumn(name = "bucket_id")
  private List<BucketTopic> bucketTopics;

}
