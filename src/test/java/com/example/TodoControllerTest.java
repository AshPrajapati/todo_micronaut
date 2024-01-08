package com.example;

import static io.micronaut.http.HttpRequest.*;
import static org.assertj.core.api.Assertions.assertThat;

import io.micronaut.http.client.HttpClient;
import io.micronaut.http.client.annotation.Client;
import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import jakarta.inject.Inject;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@MicronautTest
class TodoControllerTest {

  @Client("/todos")
  @Inject
  private HttpClient httpClient;

  @Test
  @DisplayName("should create todo")
  void create_todo() {
    CreateTodoDto todoBody = new CreateTodoDto("first todo");
    Todo todo = httpClient.toBlocking().retrieve(POST("/create", todoBody), Todo.class);
    assertThat(todo).isNotNull();
    assertThat(todo).isInstanceOf(Todo.class);
    assertThat(todo.getId()).isNotNull();
    assertThat(todo.getTodoText()).isEqualTo("first todo");
  }

  @Test
  @DisplayName("should return all todos")
  void get_all_todos() {
    TodosWrapper todosWrapper = httpClient.toBlocking().retrieve(GET("/"), TodosWrapper.class);
    assertThat(todosWrapper).isNotNull();
    assertThat(todosWrapper.getTodos().size()).isGreaterThanOrEqualTo(0);
  }

  @Test
  @DisplayName("should return todo by id")
  void get_todo_by_id() {
    Todo todo = httpClient.toBlocking().retrieve(GET("/102"), Todo.class);
    assertThat(todo).isNotNull();
    assertThat(todo.getId()).isEqualTo(102);
  }

  @Test
  @DisplayName("should delete todo")
  void delete_todo() {
    Todo todo = httpClient.toBlocking().retrieve(DELETE("/100"), Todo.class);
    assertThat(todo).isNotNull();
    assertThat(todo.getId()).isEqualTo(100);
  }

  @Test
  @DisplayName("update todo")
  void update_todo() {
    UpdateTodoDto updateTodoDto = new UpdateTodoDto("updatedTodo");
    Todo todo = httpClient.toBlocking().retrieve(PUT("/101", updateTodoDto), Todo.class);
    assertThat(todo).isNotNull();
    assertThat(todo.getId()).isEqualTo(101);
    assertThat(todo.getTodoText()).isEqualTo(updateTodoDto.getTodoTextToUpdate());
  }
}
