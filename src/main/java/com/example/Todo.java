package com.example;

import java.util.Date;

public class Todo {
  private int id;
  private String todoText;
  private Date todoDate;

  public Todo() {}

  public Todo(int id, String todoText, Date todoDate) {
    this.id = id;
    this.todoText = todoText;
    this.todoDate = todoDate;
  }

  public int getId() {
    return id;
  }

  public String getTodoText() {
    return todoText;
  }

  public Date getTodoDate() {
    return todoDate;
  }

  public void setTodoText(String todoText) {
    this.todoText = todoText;
  }
}
