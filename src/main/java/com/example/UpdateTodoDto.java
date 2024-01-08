package com.example;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class UpdateTodoDto {
  private String todoTextToUpdate;

  @JsonCreator
  public UpdateTodoDto(@JsonProperty("todoTextToUpdate") String todoTextToUpdate) {
    this.todoTextToUpdate = todoTextToUpdate;
  }

  public String getTodoTextToUpdate() {
    return todoTextToUpdate;
  }
}
