package com.thanksbucket.core.bucket.command.domain;

import com.thanksbucket.base.domain.ValueObject;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import java.time.LocalDate;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Embeddable
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class BucketGoalDate extends ValueObject<BucketGoalDate> {

  @Column(nullable = false)
  private LocalDate goalDate;

  private BucketGoalDate(LocalDate goalDate) {
    this.goalDate = goalDate;
  }

  public static BucketGoalDate from(LocalDate goalDate) {
    if (goalDate.isBefore(LocalDate.now())) {
      throw new IllegalArgumentException(String.format("목표일은 %s 이후여야 합니다", LocalDate.now()));
    }
    return new BucketGoalDate(goalDate);
  }
}
