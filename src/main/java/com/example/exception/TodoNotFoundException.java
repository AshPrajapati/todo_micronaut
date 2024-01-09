package com.example.exception;

import com.fasterxml.jackson.annotation.JsonProperty;

public class TodoNotFoundException extends RuntimeException {
  @JsonProperty(value = "message")
  private final String message;

  public TodoNotFoundException(String message) {
    this.message = message;
  }

  @Override
  public String getMessage() {
    return message;
  }
}
