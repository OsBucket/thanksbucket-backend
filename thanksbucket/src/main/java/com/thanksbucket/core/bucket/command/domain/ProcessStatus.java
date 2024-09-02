package com.thanksbucket.core.bucket.command.domain;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum ProcessStatus {
  START, FINISH;

  public boolean isStart() {
    return this == START;
  }

  public boolean isFinish() {
    return this == FINISH;
  }
}
