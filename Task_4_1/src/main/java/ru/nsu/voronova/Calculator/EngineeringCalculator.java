package ru.nsu.voronova.Calculator;

import ru.nsu.voronova.Calculator.Exceptions.CalculatorException;
import ru.nsu.voronova.Calculator.Exceptions.UnknownArgumentException;
import ru.nsu.voronova.Number.Complex;
import ru.nsu.voronova.Operation.Arity.Arity;
import ru.nsu.voronova.Operation.Exceptions.InvalidNumberArgumentsException;
import ru.nsu.voronova.Operation.Operation;
import ru.nsu.voronova.Operation.Exceptions.OperationException;
import ru.nsu.voronova.Operation.OperationFabrication;

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

  private Number executeOperation(Operation operation) throws CalculatorException, OperationException {
    Arity arity = operation.getArity();
    Number result;
    switch (arity) {
      case UNARY -> result = operation.eval(intermediateResults.pop());
      case BINARY -> result = operation.eval(intermediateResults.pop(), intermediateResults.pop());
      default -> throw new UnknownArgumentException();
    }
    return result;
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
      Operation operation = new OperationFabrication(atom).getOperation();
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

    Number result = intermediateResults.pop();
    return result;
  }
}

