package com.thanksbucket.ui.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.thanksbucket.domain.occupation.Occupation;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class OccupationResponse {
    private final Long id;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private final LocalDateTime createdAt;
    private final String name;

    public OccupationResponse(Occupation occupation) {
        this.id = occupation.getId();
        this.createdAt = occupation.getCreatedAt();
        this.name = occupation.getName();
    }
}
