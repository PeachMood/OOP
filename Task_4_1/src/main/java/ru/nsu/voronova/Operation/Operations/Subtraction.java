package ru.nsu.voronova.Operation.Operations;

import ru.nsu.voronova.Number.Complex;
import ru.nsu.voronova.Operation.Arity.BinaryOperation;

public class Subtraction extends BinaryOperation {
  @Override
  public Number eval2(Double number1, Double number2) {
    return new Addition().eval2(number1, -number2);
  }

  @Override
  public Number eval2(Double number1, Complex number2) {
    double realPart = -number2.getRealPart();
    double imaginaryPart = -number2.getImaginaryPart();
    return new Addition().eval2(number1, new Complex(realPart, imaginaryPart));
  }

  @Override
  public Number eval2(Complex number1, Double number2) {
    return new Addition().eval2(number1, -number2);
  }

  @Override
  public Number eval2(Complex number1, Complex number2) {
    double realPart = -number2.getRealPart();
    double imaginaryPart = -number2.getImaginaryPart();
    return new Addition().eval2(number1, new Complex(realPart, imaginaryPart));
  }
}
