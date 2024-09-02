package com.thanksbucket.core.bucket.command.application.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.thanksbucket.core.bucket.command.domain.BucketTodo;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import java.time.LocalDate;
import java.util.List;
import lombok.Data;

@Data
public class UpdateBucketRequest {

  @NotBlank
  private String title;

  @JsonFormat(pattern = "yyyy-MM-dd", shape = JsonFormat.Shape.STRING)
  private LocalDate goalDate;

  private List<@Positive Long> topicIds;

  private List<UpdateTodoRequest> bucketTodos;

  public List<BucketTodo> toBucketTodos() {
    return bucketTodos.stream()
        .map(todo -> BucketTodo.create(todo.getContent(), todo.getDone()))
        .toList();
  }

  @Data
  public static class UpdateTodoRequest {

    @NotBlank
    private String content;
    @NotNull
    private Boolean done;
  }
}
