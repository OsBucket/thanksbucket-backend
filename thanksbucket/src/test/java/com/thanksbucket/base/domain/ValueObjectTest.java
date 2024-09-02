package com.thanksbucket.base.domain;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ValueObjectTest {

  @Test
  @DisplayName("VO는 모든 필드에 대해서 동등성 비교를 진행 / 필드값 같음 / 성공")
  void VOEquals() {
    SomeValueObject vo1 = new SomeValueObject(1, List.of("a", "b"));
    SomeValueObject vo2 = new SomeValueObject(1, List.of("a", "b"));

    assertThat(vo1).isEqualTo(vo2);
  }

  @Test
  @DisplayName("VO는 모든 필드에 대해서 동등성 비교를 진행 / 필드값 다름 / 실패")
  void VONotEquals() {
    SomeValueObject vo1 = new SomeValueObject(1, List.of("a", "b"));
    SomeValueObject vo2 = new SomeValueObject(2, List.of("a", "b"));

    assertThat(vo1).isNotEqualTo(vo2);
  }

  @Test
  @DisplayName("VO는 모든 필드에 대해서 동등성 비교를 진행 / List 순서가 다름 / 실패")
  void VONotEqualsList() {
    SomeValueObject vo1 = new SomeValueObject(1, List.of("b", "a"));
    SomeValueObject vo2 = new SomeValueObject(1, List.of("a", "b"));

    assertThat(vo1).isNotEqualTo(vo2);
  }


  static class SomeValueObject extends ValueObject<SomeValueObject> {

    private final int value;

    private final List<String> li;

    public SomeValueObject(int value, List<String> li) {
      this.value = value;
      this.li = li;
    }
  }
}