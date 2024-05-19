package com.thanksbucket.ui.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class PatchBucketRequest {
    @NotNull
    private Boolean isDone;
}
