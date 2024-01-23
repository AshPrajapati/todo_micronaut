package com.example;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class CreateTodoDto {
  private String todoText;
  private String category;

  @JsonCreator
  public CreateTodoDto(
      @JsonProperty("todoText") String todoText, @JsonProperty("category") String category) {
    this.todoText = todoText;
    this.category = category;
  }

  public String getTodoText() {
    return todoText;
  }

  public String getCategory() {
    return category;
  }
}
