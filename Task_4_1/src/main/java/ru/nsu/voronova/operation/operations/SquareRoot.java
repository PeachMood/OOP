package ru.nsu.voronova.operation.operations;

import ru.nsu.voronova.number.Complex;
import ru.nsu.voronova.operation.arity.UnaryOperation;

import java.lang.Math;

public class SquareRoot extends UnaryOperation {
  @Override
  public Number eval1(Double number) {
    return Math.sqrt(number);
  }

  @Override
  public Number eval1(Complex number) {
    double realPart = number.getRealPart();
    double imaginaryPart = number.getImaginaryPart();
    if (imaginaryPart == 0) {
      return eval1(realPart);
    }

    double squareRoot = Math.sqrt(realPart * realPart + imaginaryPart * imaginaryPart);
    double resultRealPart = Math.sqrt(squareRoot + realPart) / 2.0;
    double resultImaginaryPart = Math.sqrt(squareRoot - realPart) / 2.0;
    if (imaginaryPart < 0) {
      resultImaginaryPart *= -1.0;
    }

    return new Complex(resultRealPart, resultImaginaryPart);
  }
}
