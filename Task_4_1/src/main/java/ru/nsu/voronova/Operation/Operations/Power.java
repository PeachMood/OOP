package ru.nsu.voronova.Operation.Operations;

import ru.nsu.voronova.Number.Complex;
import ru.nsu.voronova.Operation.Arity.BinaryOperation;
import ru.nsu.voronova.Operation.Exceptions.OperationException;

public class Power extends BinaryOperation {

  @Override
  public Number eval2(Double number1, Double number2) {
    return Math.pow(number1, number2);
  }

  @Override
  public Number eval2(Double number1, Complex number2) {
    double realPart = number2.getRealPart();
    double imaginaryPart = number2.getImaginaryPart();
    if (imaginaryPart == 0.0) {
      return eval2(number1, realPart);
    }

    double argument = number2.getArgument();
    double resultRealPart = Math.pow(number1, realPart) * Math.cos(argument);
    double resultImaginaryPart = Math.pow(number1, realPart) * Math.sin(argument);
    return new Complex(resultRealPart, resultImaginaryPart);
  }

  @Override
  public Number eval2(Complex number1, Double number2) {
    double realPart = number1.getRealPart();
    double imaginaryPart = number1.getImaginaryPart();
    if (imaginaryPart == 0.0) {
      return eval2(realPart, number2);
    }

    double modulus = number1.getModulus();
    double argument = number1.getArgument();
    double resultRealPart = Math.pow(modulus, number2) * Math.cos(number2 * argument);
    double resultImaginaryPart = Math.pow(modulus, number2) * Math.sin(number2 * argument);
    return new Complex(resultRealPart, resultImaginaryPart);
  }

  @Override
  public Number eval2(Complex number1, Complex number2) throws OperationException {
    double realPart1 = number1.getRealPart();
    double imaginaryPart1 = number1.getImaginaryPart();
    double realPart2 = number2.getRealPart();
    double imaginaryPart2 = number2.getImaginaryPart();
    if (imaginaryPart1 == 0.0 && imaginaryPart2 == 0.0) {
      return eval2(realPart1, realPart2);
    }
    if (imaginaryPart1 == 0.0) {
      return eval2(realPart1, number2);
    }
    if (imaginaryPart2 == 0.0) {
      return eval2(number1, realPart2);
    }

    double modulus1 = number1.getModulus();
    double argument1 = number1.getArgument();
    Number firstFactor = number2;
    Number secondFactor = new Complex(Math.log(modulus1), argument1);
    Number power = new Multiplication().eval(firstFactor, secondFactor);
    return eval(Math.exp(1.0), power);
  }
}
