package com.example;

import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.*;

@Controller("/todos")
public class TodoController {
  private final TodoService todoService;

  public TodoController(TodoService todoService) {
    this.todoService = todoService;
  }

  @Post("/create")
  public HttpResponse<Todo> createTodo(@Body CreateTodoDto todoToCreate) {
    return HttpResponse.created(todoService.create(todoToCreate));
  }

  @Get("/")
  public TodosWrapper getAllTodos() {
    return todoService.getAllTodos();
  }

  @Get(value = "/{id}")
  public HttpResponse<Todo> getTodoById(@PathVariable Integer id) {
    return HttpResponse.ok(todoService.getTodoById(id));
  }

  @Delete(value = "/{id}")
  public HttpResponse<Void> deleteTodo(@PathVariable Integer id) {
    todoService.deleteTodo(id);
    return HttpResponse.ok();
  }

  @Put(value = "/{id}")
  public HttpResponse<Todo> updateTodo(
      @PathVariable Integer id, @Body UpdateTodoDto updateTodoDto) {
    return HttpResponse.ok(todoService.updateTodo(id, updateTodoDto.getTodoTextToUpdate()));
  }
}
