package ru.nsu.voronova;

public class Addition extends BinaryOperation {
  @Override
  public Number eval2(Double number1, Double number2) {
    return number1 + number2;
  }

  @Override
  public Number eval2(Double number1, Complex number2) {
    double number2Real = number2.getReal();
    double number2Imaginary = number2.getImaginary();
    if (number2Imaginary == 0) {
      return eval2(number1, number2Real);
    }

    double resultReal = number1 + number2Real;
    double resultImaginary = number2Imaginary;
    return new Complex(resultReal, resultImaginary);
  }

  @Override
  public Number eval2(Complex number1, Double number2) {
    return eval2(number2, number1);
  }

  @Override
  public Number eval2(Complex number1, Complex number2) {
    double number1Real = number1.getReal();
    double number1Imaginary = number1.getImaginary();
    double number2Real = number2.getReal();
    double number2Imaginary = number2.getImaginary();
    if (number1Imaginary == 0.0 && number2Imaginary == 0.0) {
      return eval2(number1Real, number2Real);
    }

    if (number1Imaginary == 0.0) {
      return eval2(number1Real, number2);
    }

    double resultReal = number1Real + number2Real;
    double resultImaginary = number1Imaginary + number2Imaginary;
    return new Complex(resultReal, resultImaginary);
  }
}
