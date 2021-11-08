package ru.nsu.voronova.Operation.Exceptions;

public class OperationException extends Exception {
  public OperationException() {
    super();
  }

  public OperationException(String errorMessage) {
    super(errorMessage);
  }
}
