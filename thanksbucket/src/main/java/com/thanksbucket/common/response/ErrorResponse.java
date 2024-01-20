package com.thanksbucket.common.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

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

