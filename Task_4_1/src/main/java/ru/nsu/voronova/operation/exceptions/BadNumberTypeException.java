package ru.nsu.voronova.operation.exceptions;

public class BadNumberTypeException extends OperationException {
  public BadNumberTypeException() {
    super();
  }

  public BadNumberTypeException(String errorMessage) {
    super(errorMessage);
  }
}
