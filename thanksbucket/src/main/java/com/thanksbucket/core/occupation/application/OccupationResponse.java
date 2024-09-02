package com.thanksbucket.core.occupation.application;

import com.thanksbucket.core.occupation.domain.Occupation;
import lombok.Getter;

@Getter
public class OccupationResponse {

  private final Long id;
  private final String name;

  public OccupationResponse(Occupation occupation) {
    this.id = occupation.getId();
    this.name = occupation.getName();
  }
}
