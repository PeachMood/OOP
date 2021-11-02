package ru.nsu.voronova;


import java.util.List;
import java.util.Stack;

public class Calculator {
  private String[] operators = {"+", "-", "sin", "sqrt", "log"};
  private Stack<Number> operands;

  public Calculator() {
    operands = new Stack<>();
  }

  private Operation switchOperation(String operator) {
    if (operator.equals("+")) {
      return new Addition();
    }

    if (operator.equals("-")) {
      return new Subtraction();
    }

    if (operator.equals("sin")) {
      return new Sinus();
    }

    if (operator.equals("sqrt")) {
      return new SquareRoot();
    }

    if (operator.equals("log")) {
      return new Logarithm();
    }

    return null;
  }

  private boolean isOperator(String string) {
    for (String operator : operators) {
      if (string.equals(operator)) return true;
    }
    return false;
  }

  private Number parseNumber(String string) {
    if (string.contains("(")) {
      return Complex.parseComplex(string);
    }
    return Double.parseDouble(string);
  }

  private Number executeOperation(Operation operation) throws OperationException {
    int argumentsNumber = operation.getArity().getArgumentsNumber();
    if (argumentsNumber == 1) {
      Number number = operands.pop();
      return operation.eval(number);
    }
    if (argumentsNumber == 2) {
      Number number1 = operands.pop();
      Number number2 = operands.pop();
      return operation.eval(number1, number2);
    }
    return null;
  }

  public Number calculate(String expression) throws OperationException {
    if (expression == null) {
      throw new NullPointerException();
    }

    String[] atoms = expression.split("\\s+");
    for(int i = atoms.length - 1; i >= 0; --i) {
      Number result;
      String atom = atoms[i];
      if (isOperator(atom)) {
        Operation operation = switchOperation(atom);
        result = executeOperation(operation);
      } else {
        result = parseNumber(atom);
      }
      operands.push(result);
    }

    return operands.pop();
  }
}

