package ru.nsu.voronova;

public class BadNumberTypeException extends OperationException {
  public BadNumberTypeException(){
    super();
  }

  public BadNumberTypeException(String errorMessage) {
    super(errorMessage);
  }
}
