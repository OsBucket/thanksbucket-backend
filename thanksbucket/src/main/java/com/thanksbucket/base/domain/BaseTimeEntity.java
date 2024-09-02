package com.thanksbucket.base.domain;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import java.time.LocalDateTime;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;


@MappedSuperclass
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public abstract class BaseTimeEntity {

  @Column(updatable = false, nullable = false)
  @ColumnDefault("CURRENT_TIMESTAMP")
  private LocalDateTime createdDate;

  @Column(nullable = false)
  @ColumnDefault("CURRENT_TIMESTAMP")
  private LocalDateTime updatedDate;
}
