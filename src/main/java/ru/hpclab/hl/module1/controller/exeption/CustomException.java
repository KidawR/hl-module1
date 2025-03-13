package ru.hpclab.hl.module1.controller.exeption;

public class CustomException extends RuntimeException {
  public CustomException(String message) {
    super(message);
  }
}
