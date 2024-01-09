package com.example;

import com.example.exception.TodoNotFoundException;
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

  public Todo getTodoById(Integer id) {
    return todoRepository
        .getTodoById(id)
        .orElseThrow(() -> new TodoNotFoundException("todo not found with id" + id));
  }

  public Todo deleteTodo(Integer id) {
    todoRepository
        .getTodoById(id)
        .orElseThrow(() -> new TodoNotFoundException("todo not found with id" + id));
    return todoRepository.deleteTodo(id);
  }

  public Todo updateTodo(Integer id, String todoToUpdate) {
    todoRepository
        .getTodoById(id)
        .orElseThrow(() -> new TodoNotFoundException("todo not found with id" + id));
    return todoRepository.updateTodo(id, todoToUpdate);
  }
}
