package com.thanksbucket.core.bucket.ui.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.thanksbucket.core.bucket.command.domain.BucketTodo;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import java.time.LocalDate;
import java.util.List;
import lombok.Data;

@Data
public class StartBucketRequest {

  @NotBlank
  private String title;

  @JsonFormat(pattern = "yyyy-MM-dd", shape = JsonFormat.Shape.STRING)
  private LocalDate goalDate;

  private List<@Positive Long> topicIds;

  private List<StartTodoRequest> bucketTodos;

  public List<BucketTodo> toBucketTodos() {
    return bucketTodos.stream()
        .map((bucketTodoRequest) -> BucketTodo.start(bucketTodoRequest.getContent()))
        .toList();
  }


  @Data
  public static class StartTodoRequest {

    @NotBlank
    private String content;
  }
}
