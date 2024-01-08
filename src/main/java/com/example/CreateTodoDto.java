package com.example;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class CreateTodoDto {
  private String todoText;

  @JsonCreator
  public CreateTodoDto(@JsonProperty("todoText") String todoText) {
    this.todoText = todoText;
  }

  public String getTodoText() {
    return todoText;
  }
}
