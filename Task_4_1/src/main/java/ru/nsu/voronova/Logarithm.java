package ru.nsu.voronova;

public class Logarithm extends UnaryOperation {
  @Override
  public Number eval1(Double number) {
    return Math.log(number);
  }

  @Override
  public Number eval1(Complex number) {
    double numberReal = number.getReal();
    double numberImaginary = number.getImaginary();
    if (numberReal >= 0.0 && numberImaginary == 0.0) {
      return eval1(numberReal);
    }

    double resultReal = Math.log(Math.sqrt(numberReal * numberReal + numberImaginary * numberImaginary));
    double resultImaginary = Math.atan(numberImaginary / numberReal);

    return new Complex(resultReal, resultImaginary);
  }
}
