package com.thanksbucket.ui.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDate;
import java.util.List;

@AllArgsConstructor
@Getter
public class CreateBucketRequest {
    @NotBlank
    private String title;

    @JsonFormat(pattern = "yyyy-MM-dd", shape = JsonFormat.Shape.STRING)
    private LocalDate goalDate;

    @NotNull
    private List<Long> topicIds;

    @NotNull
    private List<CreateBucketTodoRequest> bucketTodos;
}
