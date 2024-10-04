package com.thanksbucket.core.bucket.command.application.dto;

import com.thanksbucket.core.bucket.command.domain.ProcessStatus;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class FinishBucketRequest {

  @NotNull
  private ProcessStatus status;
}
