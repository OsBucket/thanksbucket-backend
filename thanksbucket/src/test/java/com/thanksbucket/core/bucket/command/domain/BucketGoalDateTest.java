package com.thanksbucket.core.bucket.command.domain;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertAll;

import java.time.LocalDate;
import org.junit.jupiter.api.Test;

class BucketGoalDateTest {

  static final LocalDate 어제 = LocalDate.now().minusDays(1);
  static final LocalDate 오늘 = LocalDate.now();
  static final LocalDate 내일 = LocalDate.now().plusDays(1);

  @Test
  void 목표날짜생성_오늘이후_성공() {
    assertAll(
        () -> BucketGoalDate.from(오늘),
        () -> BucketGoalDate.from(내일)
    );
  }

  @Test
  void 목표날짜생성_오늘이전_실패() {
    assertThatThrownBy(() -> BucketGoalDate.from(어제))
        .isInstanceOf(IllegalArgumentException.class);
  }
}