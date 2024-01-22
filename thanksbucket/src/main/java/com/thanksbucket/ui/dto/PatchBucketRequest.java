package com.thanksbucket.ui.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class PatchBucketRequest {
    @NotNull
    private Boolean isDone;

    public PatchBucketRequest() {
    }
}
