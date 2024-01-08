package com.example;

import jakarta.inject.Singleton;

@Singleton
public class TodoService {
  private final TodoRepository todoRepository;

  public TodoService(TodoRepository todoRepository) {

    this.todoRepository = todoRepository;
  }

  public Todo create(CreateTodoDto todoToCreate) {
    return todoRepository.create(todoToCreate);
  }

  public TodosWrapper getAllTodos() {
    return new TodosWrapper(todoRepository.getAll());
  }

  public Todo getTodoById(String id) {
    return todoRepository.getTodoById(id);
  }

  public Todo deleteTodo(String id) {
    return todoRepository.deleteTodo(id);
  }

  public Todo updateTodo(String id, String todoToUpdate) {
    return todoRepository.updateTodo(id, todoToUpdate);
  }
}
