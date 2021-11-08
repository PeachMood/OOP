package ru.nsu.voronova.Operation;

import ru.nsu.voronova.Operation.Arity.Arity;
import ru.nsu.voronova.Operation.Exceptions.OperationException;

public abstract class Operation {
  private final Arity arity;

  public Operation(Arity arity) {
    this.arity = arity;
  }

  public Arity getArity() {
    return arity;
  }

  public abstract Number eval(Number... arguments) throws OperationException;
}
