package com.thanksbucket.common.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

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
