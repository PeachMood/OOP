package ru.nsu.voronova;

public abstract class Operation {
  private final Arity arity;

  public Operation(Arity arity) {
    this.arity = arity;
  }

  public Arity getArity() {
    return arity;
  }

  public abstract Number eval(Number ...arguments) throws OperationException;
}
