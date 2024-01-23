package com.example;

import com.example.exception.TodoNotFoundException;
import jakarta.inject.Singleton;
import java.util.Date;

@Singleton
public class TodoService {
  private final TodoRepository todoRepository;

  public TodoService(TodoRepository todoRepository) {
    this.todoRepository = todoRepository;
  }

  public Todo create(CreateTodoDto todoToCreate) {
    Todo todo = new Todo();
    todo.setTodoText(todoToCreate.getTodoText());
    todo.setTodoDate(new Date());
    todo.setCategory(todoToCreate.getCategory());
    return todoRepository.save(todo);
  }

  public TodosWrapper getAllTodos() {
    return new TodosWrapper(todoRepository.findAll());
  }

  public Todo getTodoById(Integer id) {
    return todoRepository
        .findById(id)
        .orElseThrow(() -> new TodoNotFoundException("todo not found with id" + id));
  }

  public void deleteTodo(Integer id) {
    todoRepository
        .findById(id)
        .orElseThrow(() -> new TodoNotFoundException("todo not found with id" + id));
    todoRepository.deleteById(id);
  }

  public Todo updateTodo(Integer id, String todoToUpdate) {
    Todo foundTodo =
        todoRepository
            .findById(id)
            .orElseThrow(() -> new TodoNotFoundException("todo not found with id" + id));
    foundTodo.setTodoText(todoToUpdate);
    return todoRepository.update(foundTodo);
  }
}
