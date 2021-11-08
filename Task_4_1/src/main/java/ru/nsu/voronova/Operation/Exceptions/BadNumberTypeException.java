package ru.nsu.voronova.Operation.Exceptions;

public class BadNumberTypeException extends OperationException {
  public BadNumberTypeException() {
    super();
  }

  public BadNumberTypeException(String errorMessage) {
    super(errorMessage);
  }
}
