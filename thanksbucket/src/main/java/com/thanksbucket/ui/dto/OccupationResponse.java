package com.thanksbucket.ui.dto;

import com.thanksbucket.domain.occupation.Occupation;
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
