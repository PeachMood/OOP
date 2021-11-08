package ru.nsu.voronova.Operation;

import ru.nsu.voronova.Operation.Operations.*;

public class OperationFabrication {
  private Operation operation;

  public OperationFabrication(String operator) {
    switch (operator) {
      case "log" -> operation = new Logarithm();
      case "sqrt" -> operation = new SquareRoot();
      case "sin" -> operation = new Sinus();
      case "cos" -> operation = new Cosinus();
      case "deg" -> operation = new Degrees();
      case "pow" -> operation = new Power();
      case "+" -> operation = new Addition();
      case "-" -> operation = new Subtraction();
      case "*" -> operation = new Multiplication();
      case "/" -> operation = new Division();
      default -> operation = null;
    }
  }

  public Operation getOperation() {
    return operation;
  }
}
