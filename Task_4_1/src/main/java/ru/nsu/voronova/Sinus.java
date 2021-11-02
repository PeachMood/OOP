package ru.nsu.voronova;

public class Sinus extends UnaryOperation {
  @Override
  public Number eval1(Double number) {
    return Math.sin(number);
  }

  @Override
  public Number eval1(Complex number) {
    double numberReal = number.getReal();
    double numberImaginary = number.getImaginary();
    if (numberImaginary == 0) {
      return eval1(numberReal);
    }

    double resultReal = Math.sin(numberReal) * Math.cosh(numberImaginary);
    double resultImaginary = Math.cos(numberReal) * Math.sinh(numberImaginary);
    return new Complex(resultReal, resultImaginary);
  }
}
