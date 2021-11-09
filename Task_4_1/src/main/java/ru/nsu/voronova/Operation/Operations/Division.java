package ru.nsu.voronova.Operation.Operations;

import ru.nsu.voronova.Number.Complex;
import ru.nsu.voronova.Operation.Arity.BinaryOperation;
import ru.nsu.voronova.Operation.Exceptions.OperationException;

public class Division extends BinaryOperation {
  @Override
  public Number eval2(Double number1, Double number2) {
    return number1 / number2;
  }

  @Override
  public Number eval2(Double number1, Complex number2) throws OperationException {
    return eval2(new Complex(number1, 0.0), number2);
  }

  @Override
  public Number eval2(Complex number1, Double number2) {
    double realPart = number1.getRealPart();
    double imaginaryPart = number1.getImaginaryPart();
    if (imaginaryPart == 0.0) {
      return eval2(realPart, number2);
    }
    return new Complex(realPart / number2, imaginaryPart / number2);
  }

  @Override
  public Number eval2(Complex number1, Complex number2) throws OperationException {
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

    double sumOfSquares = realPart2 * realPart2 + imaginaryPart2 * imaginaryPart2;
    double resultRealPart = (realPart1 * realPart2 + imaginaryPart1 * imaginaryPart2) / sumOfSquares;
    double resultImaginaryPart = (realPart2 * imaginaryPart1 - realPart1 * imaginaryPart2) / sumOfSquares;
    return new Complex(resultRealPart, resultImaginaryPart);
  }
}
