package ru.nsu.voronova.calculator;

import ru.nsu.voronova.calculator.exceptions.CalculatorException;
import ru.nsu.voronova.calculator.exceptions.UnknownArgumentException;
import ru.nsu.voronova.number.Complex;
import ru.nsu.voronova.operation.exceptions.InvalidNumberArgumentsException;
import ru.nsu.voronova.operation.Operation;
import ru.nsu.voronova.operation.exceptions.OperationException;
import ru.nsu.voronova.operation.OperationFabrication;

import java.util.Stack;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EngineeringCalculator implements Calculator{
  private Stack<Number> intermediateResults;

  public EngineeringCalculator() {
    intermediateResults = new Stack<>();
  }

  private boolean isNumber(String string) {
    Pattern isDouble = Pattern.compile("\\d+.?\\d*");
    Matcher matcher = isDouble.matcher(string);
    if (matcher.matches()) {
      return true;
    }

    Pattern isComplex = Pattern.compile("\\(\\d+\\.?\\d*,\\d+\\.?\\d*\\)");
    matcher = isComplex.matcher(string);
    return matcher.matches();
  }

  private Number parseNumber(String string) {
    if (string.contains("(")) {
      return Complex.parseComplex(string);
    }
    return Double.parseDouble(string);
  }

  private Number executeOperation(Operation operation) throws OperationException {
    return switch (operation.getArity()) {
      case UNARY -> operation.eval(intermediateResults.pop());
      case BINARY -> operation.eval(intermediateResults.pop(), intermediateResults.pop());
    };
  }

  @Override
  public Number calculate(String expression) throws CalculatorException, OperationException {
    if (expression == null) {
      throw new NullPointerException();
    }

    String[] atoms = expression.split("\\s+");
    for(int i = atoms.length - 1; i >= 0; --i) {
      Number intermediateResult;
      String atom = atoms[i];
      Operation operation = OperationFabrication.getOperation(atom);
      if (isNumber(atom)) {
        intermediateResult = parseNumber(atom);
      } else if (operation != null) {
        intermediateResult = executeOperation(operation);
      } else {
        throw new UnknownArgumentException();
      }
      intermediateResults.push(intermediateResult);
    }

    if (intermediateResults.size() != 1) {
      throw new InvalidNumberArgumentsException();
    }

    return intermediateResults.pop();
  }
}

