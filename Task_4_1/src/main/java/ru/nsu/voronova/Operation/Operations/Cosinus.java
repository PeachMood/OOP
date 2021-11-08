package ru.nsu.voronova.Operation.Operations;

import ru.nsu.voronova.Number.Complex;
import ru.nsu.voronova.Operation.Arity.UnaryOperation;

public class Cosinus extends UnaryOperation {
  @Override
  public Number eval1(Double number) {
    return Math.cos(number);
  }

  @Override
  public Number eval1(Complex number) {
    double realPart = number.getRealPart();
    double imaginaryPart = number.getImaginaryPart();
    if (imaginaryPart == 0.0) {
      return eval1(realPart);
    }

    double resultRealPart = Math.cos(realPart) * Math.cosh(imaginaryPart);
    double resultImaginaryPart = -Math.sin(realPart) * Math.sinh(imaginaryPart);
    return new Complex(resultRealPart, resultImaginaryPart);
  }
}
