package com.example;

import static org.mockito.Mockito.*;

import io.micronaut.http.HttpResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TodoControllerShould {
  private TodoService todoService;
  private TodoController todoController;

  @BeforeEach
  void setUp() {
    todoService = mock(TodoService.class);
    todoController = new TodoController(todoService);
  }

  @Test
  void create_todo() {
    CreateTodoDto todoToCreate = new CreateTodoDto("first todo");
    HttpResponse<Todo> todo = todoController.createTodo(todoToCreate);
    verify(todoService).create(todoToCreate);
  }

  @Test
  void return_all_todos() {
    TodosWrapper todosWrapper = todoController.getAllTodos();
    verify(todoService).getAllTodos();
  }

  @Test
  void return_todo_by_id() {
    Integer id = 100;
    HttpResponse<Todo> todo = todoController.getTodoById(id);
    verify(todoService).getTodoById(id);
  }

  @Test
  void delete_todo() {
    Integer id = 100;
    HttpResponse<Void> todo = todoController.deleteTodo(id);
    verify(todoService).deleteTodo(id);
  }

  @Test
  void update_todo() {
    Integer id = 100;
    UpdateTodoDto updateTodoDto = new UpdateTodoDto("todo updated");
    HttpResponse<Todo> todo = todoController.updateTodo(id, updateTodoDto);
    verify(todoService).updateTodo(id, updateTodoDto.getTodoTextToUpdate());
  }
}
