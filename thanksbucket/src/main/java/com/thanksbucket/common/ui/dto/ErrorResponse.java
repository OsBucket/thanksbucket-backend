package com.thanksbucket.common.ui.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.time.LocalDateTime;
import lombok.Builder;
import lombok.Getter;

@Getter
public class ErrorResponse {

  @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
  private final LocalDateTime timestamp = LocalDateTime.now();
  private String path;
  private String message;

  @Builder
  public ErrorResponse(String path, String message) {
    this.path = path;
    this.message = message;
  }

  public void setPath(String path) {
    this.path = path;
  }
}

