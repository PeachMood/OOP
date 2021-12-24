package ru.nsu.voronova.operation.arity;

public enum Arity {
  UNARY(1),
  BINARY(2);

  private final int argumentsNumber;

  Arity(int parameterNumber) {
    this.argumentsNumber = parameterNumber;
  }

  public int getArgumentsNumber() {
    return argumentsNumber;
  }
}
