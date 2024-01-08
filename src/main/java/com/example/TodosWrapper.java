package com.example;

import java.util.List;

public class TodosWrapper {
  private List<Todo> todos;

  public TodosWrapper() {}

  public TodosWrapper(List<Todo> todos) {
    this.todos = todos;
  }

  public List<Todo> getTodos() {
    return todos;
  }

  public int getTodosLength() {
    return todos.size();
  }
}
