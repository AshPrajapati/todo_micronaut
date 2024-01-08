package com.example.exception;

public class TodoNotFoundException extends TodoException {
  public TodoNotFoundException(String message) {
    super(message);
  }
}
