package ru.nsu.voronova.Operation.Operations;

import ru.nsu.voronova.Number.Complex;
import ru.nsu.voronova.Operation.Arity.UnaryOperation;
import ru.nsu.voronova.Operation.Exceptions.OperationException;

public class Degrees extends UnaryOperation {
  @Override
  public Number eval1(Double number) {
    return Math.toRadians(number);
  }

  @Override
  public Number eval1(Complex number) {
    return new Multiplication().eval2(Math.PI / 180, number);
  }
}
