package com.example;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.example.exception.TodoNotFoundException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TodoServiceShould {
  private TodoRepository todoRepository;
  private TodoService todoService;
  private Todo todo1;
  private Todo todo2;

  @BeforeEach
  void setUp() {
    todo1 = new Todo(1, "todo1", new Date());
    todo2 = new Todo(2, "todo2", new Date());
    todoRepository = mock(TodoRepository.class);
    todoService = new TodoService(todoRepository);
  }

  @Test
  void create_todo() {
    CreateTodoDto todoToCreate = new CreateTodoDto("first todo");
    Todo currentTodo = new Todo(1, todoToCreate.getTodoText(), new Date());
    when(todoRepository.create(todoToCreate)).thenReturn(currentTodo);
    Todo todo = todoService.create(todoToCreate);
    verify(todoRepository).create(todoToCreate);
    assertThat(todo).isEqualTo(currentTodo);
  }

  @Test
  void get_all_todos() {
    List<Todo> expectedAllTodos = new ArrayList<Todo>(List.of(todo1, todo2));
    when(todoRepository.getAll()).thenReturn(expectedAllTodos);
    TodosWrapper todos = todoService.getAllTodos();
    verify(todoRepository).getAll();
    assertThat(todos.getTodos()).isEqualTo(expectedAllTodos);
  }

  @Test
  void get_todo_by_id() {
    Integer id = todo1.getId();
    when(todoRepository.getTodoById(id)).thenReturn(Optional.ofNullable(todo1));
    Todo foundTodo = todoService.getTodoById(id);
    verify(todoRepository).getTodoById(id);
    assertThat(foundTodo).isEqualTo(todo1);
  }

  @Test
  void delete_todo() {
    Integer id = todo1.getId();
    when(todoRepository.getTodoById(id)).thenReturn(Optional.ofNullable(todo1));
    when(todoRepository.deleteTodo(anyInt())).thenReturn(todo1);
    Todo deleteTodo = todoService.deleteTodo(id);
    verify(todoRepository).getTodoById(id);
    verify(todoRepository).deleteTodo(id);
    assertThat(deleteTodo).isEqualTo(todo1);
  }

  @Test
  void update_todo() {
    Integer id = todo1.getId();
    String todoToUpdate = "updated todo";
    when(todoRepository.getTodoById(id)).thenReturn(Optional.ofNullable(todo1));
    Todo expectedTodo = new Todo(id, todoToUpdate, todo1.getTodoDate());
    when(todoRepository.updateTodo(id, todoToUpdate)).thenReturn(expectedTodo);
    Todo updatedTodo = todoService.updateTodo(id, todoToUpdate);
    verify(todoRepository).getTodoById(id);
    verify(todoRepository).updateTodo(id, todoToUpdate);
    assertThat(updatedTodo).isEqualTo(expectedTodo);
  }

  @Test
  void should_throw_exception_if_todo_not_exist() {
    Integer id = 23;
    when(todoRepository.getTodoById(id)).thenReturn(Optional.ofNullable(null));
    assertThatThrownBy(() -> todoService.getTodoById(id))
        .isInstanceOf(TodoNotFoundException.class)
        .hasMessage("todo not found with id" + id);
  }

  @Test
  void should_throw_exception_if_todo_not_exist_on_delete() {
    Integer id = 23;
    when(todoRepository.getTodoById(id)).thenReturn(Optional.ofNullable(null));
    assertThatThrownBy(() -> todoService.deleteTodo(id))
        .isInstanceOf(TodoNotFoundException.class)
        .hasMessage("todo not found with id" + id);
  }

  @Test
  void should_throw_exception_if_todo_not_exist_on_update() {
    Integer id = 23;
    when(todoRepository.getTodoById(id)).thenReturn(Optional.ofNullable(null));
    assertThatThrownBy(() -> todoService.updateTodo(id, "update todo"))
        .isInstanceOf(TodoNotFoundException.class)
        .hasMessage("todo not found with id" + id);
  }
}
