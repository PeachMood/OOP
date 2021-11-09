package ru.nsu.voronova.operation.operations;

import ru.nsu.voronova.number.Complex;
import ru.nsu.voronova.operation.arity.UnaryOperation;

public class Logarithm extends UnaryOperation {
  @Override
  public Number eval1(Double number) {
    return Math.log(number);
  }

  @Override
  public Number eval1(Complex number) {
    double realPart = number.getRealPart();
    double imaginaryPart = number.getImaginaryPart();
    if (realPart >= 0.0 && imaginaryPart == 0.0) {
      return eval1(realPart);
    }

    double resultRealPart = Math.log(Math.sqrt(realPart * realPart + imaginaryPart * imaginaryPart));
    double resultImaginaryPart = Math.atan(imaginaryPart / realPart);
    return new Complex(resultRealPart, resultImaginaryPart);
  }
}
