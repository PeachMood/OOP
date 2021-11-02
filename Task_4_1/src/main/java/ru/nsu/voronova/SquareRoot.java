package ru.nsu.voronova;

import java.lang.Math;

public class SquareRoot extends UnaryOperation {
  @Override
  public Number eval1(Double number) {
    return Math.sqrt(number);
  }

  @Override
  public Number eval1(Complex number) {
    double numberReal = number.getReal();
    double numberImaginary = number.getImaginary();
    if (numberImaginary == 0) {
      return eval1(numberReal);
    }

    double squareRoot = Math.sqrt(numberReal * numberReal + numberImaginary * numberImaginary);
    double resultReal = Math.sqrt(squareRoot + numberReal) / 2.0;
    double resultImaginary = Math.sqrt(squareRoot - numberReal) / 2.0;
    if (numberImaginary < 0) {
      resultImaginary *= -1.0;
    }

    return new Complex(resultReal, resultImaginary);
  }
}
