package ru.nsu.voronova.operation;

import ru.nsu.voronova.operation.operations.*;

public class OperationFabrication {
  public static Operation getOperation(String operator) {
    return switch (operator) {
      case "log" -> new Logarithm();
      case "sqrt" ->  new SquareRoot();
      case "sin" -> new Sinus();
      case "cos" -> new Cosine();
      case "deg" -> new Degrees();
      case "pow" -> new Power();
      case "+" -> new Addition();
      case "-" -> new Subtraction();
      case "*" -> new Multiplication();
      case "/" -> new Division();
      default -> null;
    };
  }
}
