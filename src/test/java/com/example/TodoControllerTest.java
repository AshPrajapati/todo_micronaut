package com.example;

import static io.micronaut.http.HttpRequest.*;
import static org.assertj.core.api.Assertions.assertThat;

import io.micronaut.http.HttpResponse;
import io.micronaut.http.client.HttpClient;
import io.micronaut.http.client.annotation.Client;
import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import jakarta.inject.Inject;
import java.util.Date;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@MicronautTest
class TodoControllerTest {

  @Client("/todos")
  @Inject
  private HttpClient httpClient;

  private Todo todoCreated;

  @BeforeEach
  void setUp() {
    CreateTodoDto todoBody = new CreateTodoDto("todo created");
    todoCreated = httpClient.toBlocking().retrieve(POST("/create", todoBody), Todo.class);
  }

  @Test
  @DisplayName("should create todo")
  void create_todo() {
    CreateTodoDto todoBody = new CreateTodoDto("first todo");
    HttpResponse<Todo> todoResponse =
        httpClient.toBlocking().exchange(POST("/create", todoBody), Todo.class);
    assertThat(todoResponse.getStatus().getCode()).isEqualTo(201);
    Todo todo = todoResponse.body();
    assertThat(todo).isNotNull();
    assertThat(todo).isInstanceOf(Todo.class);
    assertThat(todo.getId()).isNotNull();
    assertThat(todo.getTodoText()).isEqualTo("first todo");
    assertThat(todo.getTodoDate()).isInstanceOf(Date.class);
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
    HttpResponse<Todo> todoResponse =
        httpClient.toBlocking().exchange(GET("/" + todoCreated.getId()), Todo.class);
    assertThat(todoResponse.getStatus().getCode()).isEqualTo(200);
    Todo todo = todoResponse.body();
    assertThat(todo).isNotNull();
    assertThat(todo.getId()).isEqualTo(todoCreated.getId());
    assertThat(todo).isInstanceOf(Todo.class);
    assertThat(todo.getTodoDate()).isInstanceOf(Date.class);
  }

  @Test
  @DisplayName("should delete todo")
  void delete_todo() {
    HttpResponse<Todo> todoResponse =
        httpClient.toBlocking().exchange(DELETE("/" + todoCreated.getId()), Todo.class);
    assertThat(todoResponse.getStatus().getCode()).isEqualTo(200);
    Todo todo = todoResponse.body();
    assertThat(todo).isNull();
  }

  @Test
  @DisplayName("update todo")
  void update_todo() {
    UpdateTodoDto updateTodoDto = new UpdateTodoDto("updatedTodo");
    HttpResponse<Todo> todoResponse =
        httpClient.toBlocking().exchange(PUT("/" + todoCreated.getId(), updateTodoDto), Todo.class);
    assertThat(todoResponse.getStatus().getCode()).isEqualTo(200);
    Todo todo = todoResponse.body();
    assertThat(todo).isNotNull();
    assertThat(todo.getId()).isEqualTo(todoCreated.getId());
    assertThat(todo.getTodoText()).isEqualTo(updateTodoDto.getTodoTextToUpdate());
    assertThat(todo.getTodoDate()).isInstanceOf(Date.class);

  }
}
