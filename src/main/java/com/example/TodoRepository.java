package com.example;

import jakarta.inject.Singleton;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

@Singleton
public class TodoRepository {
  Todo todo1 = new Todo(100, "test todo with id 100", new Date());
  Todo todo2 = new Todo(101, "test todo with id 101", new Date());
  Todo todo3 = new Todo(102, "test todo with id 102", new Date());
  List<Todo> todoList = new ArrayList<>(List.of(todo1, todo2, todo3));
  int currId = 0;

  public Todo create(CreateTodoDto todoToCreate) {
    currId++;
    Todo currentTodo = new Todo(currId, todoToCreate.getTodoText(), new Date());
    todoList.add(currentTodo);
    return currentTodo;
  }

  public List<Todo> getAll() {
    return todoList;
  }

  public Todo getTodoById(String id) {
    Integer idNumber = Integer.parseInt(id);
    Todo todo = todoList.stream().filter((t) -> t.getId() == idNumber).findFirst().orElseThrow();
    return todo;
  }

  public Todo deleteTodo(String id) {
    Integer idNumber = Integer.parseInt(id);
    AtomicReference<Todo> deletedTodo = new AtomicReference<>();
    todoList.removeIf(
        todo -> {
          if (todo.getId() == idNumber) {
            deletedTodo.set(todo);
            return true;
          }
          return false;
        });
    Todo deleted = deletedTodo.get();
    if (deleted != null) {
      return deleted;
    } else {
      throw new IllegalArgumentException("Todo with " + id + " not found");
    }
  }

  public Todo updateTodo(String id, String todoToUpdate) {
    Integer idNumber = Integer.parseInt(id);
    return todoList.stream()
        .filter((todo) -> todo.getId() == idNumber)
        .peek((todo) -> todo.setTodoText(todoToUpdate))
        .findFirst()
        .orElseThrow(() -> new IllegalArgumentException("todo with id " + id + " not found"));
  }
}
