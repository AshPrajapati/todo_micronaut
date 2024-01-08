package com.example;

import io.micronaut.http.annotation.*;

@Controller("/todos")
public class TodoController {
  private final TodoService todoService;

  public TodoController(TodoService todoService) {

    this.todoService = todoService;
  }

  @Post("/create")
  public Todo createTodo(@Body CreateTodoDto todoToCreate) {
    return todoService.create(todoToCreate);
  }

  @Get("/")
  public TodosWrapper getAllTodos() {
    return todoService.getAllTodos();
  }

  @Get(value = "/{id}")
  public Todo getTodoById(@PathVariable String id) {
    return todoService.getTodoById(id);
  }

  @Delete(value = "/{id}")
  public Todo deleteTodo(@PathVariable String id) {
    return todoService.deleteTodo(id);
  }

  @Put(value = "/{id}")
  public Todo updateTodo(@PathVariable String id, @Body UpdateTodoDto updateTodoDto) {
    return todoService.updateTodo(id, updateTodoDto.getTodoTextToUpdate());
  }
}
