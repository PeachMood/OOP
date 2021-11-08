package ru.nsu.voronova.Operation.Operations;

import ru.nsu.voronova.Number.Complex;
import ru.nsu.voronova.Operation.Arity.BinaryOperation;
import ru.nsu.voronova.Operation.Exceptions.OperationException;

public class Multiplication extends BinaryOperation {
  @Override
  public Number eval2(Double number1, Double number2) {
    return number1 * number2;
  }

  @Override
  public Number eval2(Double number1, Complex number2) {
    double realPart = number2.getRealPart();
    double imaginaryPart = number2.getImaginaryPart();
    if (imaginaryPart == 0.0) {
      return eval2(number1, realPart);
    }
    return new Complex(number1 * realPart, number1 * imaginaryPart);
  }

  @Override
  public Number eval2(Complex number1, Double number2) {
    return eval2(number2, number1);
  }

  @Override
  public Number eval2(Complex number1, Complex number2) {
    double realPart1 = number1.getRealPart();
    double imaginaryPart1 = number1.getImaginaryPart();
    double realPart2 = number2.getRealPart();
    double imaginaryPart2 = number2.getImaginaryPart();
    if (imaginaryPart1 == 0.0 && imaginaryPart2 == 0) {
      return eval2(realPart1, realPart2);
    }
    if (imaginaryPart1 == 0.0) {
      return eval2(realPart1, number2);
    }
    if (imaginaryPart2 == 0.0) {
      return eval2(realPart2, number1);
    }

    double resultRealPart = realPart1 * imaginaryPart1 - realPart2 * imaginaryPart2;
    double resultImaginaryPart = realPart1 * imaginaryPart2 + realPart2 * imaginaryPart1;
    return new Complex(resultRealPart, resultImaginaryPart);
  }
}
