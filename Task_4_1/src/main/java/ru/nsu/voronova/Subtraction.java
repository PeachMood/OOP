package ru.nsu.voronova;

public class Subtraction extends BinaryOperation {

  @Override
  public Number eval2(Double number1, Double number2) {
    return new Addition().eval2(number1, -number2);
  }

  @Override
  public Number eval2(Double number1, Complex number2) {
    double numberReal = -number2.getReal();
    double numberImaginary = -number2.getImaginary();
    return new Addition().eval2(number1, new Complex(numberReal, numberImaginary));
  }

  @Override
  public Number eval2(Complex number1, Double number2) {
    return new Addition().eval2(number1, -number2);
  }

  @Override
  public Number eval2(Complex number1, Complex number2) {
    double number2Real = -number2.getReal();
    double number2Imaginary = -number2.getImaginary();
    return new Addition().eval2(number1, new Complex(number2Real, number2Imaginary));
  }
}
