package com.example;

import jakarta.persistence.*;
import java.util.Date;

@Entity
@Table(name = "todo")
public class Todo {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;

  @Column(name = "todo_text")
  private String todoText;

  @Column(name = "date")
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

  public void setTodoDate(Date todoDate) {
    this.todoDate = todoDate;
  }
}
