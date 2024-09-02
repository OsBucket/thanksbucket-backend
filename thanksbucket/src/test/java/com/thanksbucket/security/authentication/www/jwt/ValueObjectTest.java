package com.thanksbucket.security.authentication.www.jwt;

import static org.assertj.core.api.Assertions.assertThat;

import com.thanksbucket.base.domain.ValueObject;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ValueObjectTest {

  @Test
  @DisplayName("VO는 모든 필드에 대해서 동등성 비교를 진행 / 필드값 같음 / 성공")
  void valueObjectEquals() {
    SomeValueObject vo1 = new SomeValueObject(1, List.of("a", "b"));
    SomeValueObject vo2 = new SomeValueObject(1, List.of("a", "b"));

    assertThat(vo1).isEqualTo(vo2);
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