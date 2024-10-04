package com.thanksbucket.common.ui.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.time.LocalDateTime;
import lombok.Builder;
import lombok.Getter;

@Getter
public class SuccessResponse<T> {

  @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
  private final LocalDateTime timestamp = LocalDateTime.now();
  private String path;
  private T data;

  @Builder
  public SuccessResponse(String path, T data) {
    this.path = path;
    this.data = data;
  }
}
