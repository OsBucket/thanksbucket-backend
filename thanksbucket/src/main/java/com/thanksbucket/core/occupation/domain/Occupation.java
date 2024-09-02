package com.thanksbucket.core.occupation.domain;

import com.thanksbucket.base.domain.AggregateRoot;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "occupations")
public class Occupation extends AggregateRoot<Occupation, Long> {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "occupation_id")
  private Long id;

  @Column(unique = true, nullable = false)
  private String name;

  public Occupation(String name) {
    this.name = name;
  }
}
