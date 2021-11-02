package ru.nsu.voronova;

public class OperationException extends Exception {
  public OperationException() {
    super();
  }

  public OperationException(String errorMessage) {
    super(errorMessage);
  }
}
