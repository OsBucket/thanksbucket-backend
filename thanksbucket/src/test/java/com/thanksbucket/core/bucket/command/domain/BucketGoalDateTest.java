package com.thanksbucket.core.bucket.command.domain;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertAll;

import java.time.LocalDate;
import org.junit.jupiter.api.Test;

class BucketGoalDateTest {

  static final BucketGoalDate 어제 = BucketGoalDate.from(LocalDate.now().minusDays(1));
  static final BucketGoalDate 오늘 = BucketGoalDate.from(LocalDate.now());
  static final BucketGoalDate 내일 = BucketGoalDate.from(LocalDate.now().plusDays(1));

  @Test
  void 목표날짜검증_오늘이후_성공() {
    assertAll(
        () -> 오늘.validateFuture(),
        () -> 내일.validateFuture()
    );
  }

  @Test
  void 목표날짜검증_오늘이전_실패() {
    assertThatThrownBy(() -> 어제.validateFuture())
        .isInstanceOf(IllegalArgumentException.class);
  }
}