package ru.nsu.voronova.operation.operations;

import ru.nsu.voronova.number.Complex;
import ru.nsu.voronova.operation.arity.UnaryOperation;

public class Cosine extends UnaryOperation {
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
