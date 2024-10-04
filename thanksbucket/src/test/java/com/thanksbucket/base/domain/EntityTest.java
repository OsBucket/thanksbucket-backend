package com.thanksbucket.base.domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class EntityTest {

  @Test
  @DisplayName("Entity는 ID에 대해서만 동등성 비교를 진행 / ID 같음 / 성공")
  void entityIdEquals() {
    SomeEntity entity1 = new SomeEntity(1L, 1);
    SomeEntity entity2 = new SomeEntity(1L, 2);

    assertThat(entity1).isEqualTo(entity2);
  }

  @Test
  @DisplayName("VO는 모든 필드에 대해서 동등성 비교를 진행 / ID 다름 / 실패")
  void entityIdNotEquals() {
    SomeEntity entity1 = new SomeEntity(1L, 1);
    SomeEntity entity2 = new SomeEntity(2L, 1);

    assertThat(entity1).isNotEqualTo(entity2);
  }


  static class SomeEntity extends DomainEntity<SomeEntity, Long> {

    private Long id;

    @Override
    public Long getId() {
      return id;
    }

    public int value;

    public SomeEntity(Long id, int value) {
      this.id = id;
      this.value = value;
    }
  }
}