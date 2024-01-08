package com.example;

import java.util.Date;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class TodoServiceShould {
  private TodoRepository todoRepository = Mockito.mock(TodoRepository.class);

  private TodoService todoService = new TodoService(todoRepository);

  @Test
  void create_todo() {
    CreateTodoDto todoToCreate = new CreateTodoDto("first todo");
    Todo todo = todoService.create(todoToCreate);
    Todo currentTodo = new Todo(1, todoToCreate.getTodoText(), new Date());
    Mockito.when(todoRepository.create(todoToCreate)).thenReturn(currentTodo);
    Mockito.verify(todoRepository).create(todoToCreate);
  }

  @Test
  void get_all_todos() {
    TodosWrapper todos = todoService.getAllTodos();
    Mockito.verify(todoRepository).getAll();
  }

  @Test
  void get_todo_by_id() {
    String id = "100";
    Todo todo = todoService.getTodoById(id);
    Mockito.verify(todoRepository).getTodoById(id);
  }

  @Test
  void delete_todo() {
    String id = "100";
    Todo todo = todoService.deleteTodo(id);
    Mockito.verify(todoRepository).deleteTodo(id);
  }

  @Test
  void update_todo() {
    String id = "100";
    String todoToUpdate = "updated todo";
    Todo todo = todoService.updateTodo(id, todoToUpdate);
    Mockito.verify(todoRepository).updateTodo(id, todoToUpdate);
  }
}
