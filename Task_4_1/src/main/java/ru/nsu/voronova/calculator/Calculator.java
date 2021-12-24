package ru.nsu.voronova.calculator;

import ru.nsu.voronova.calculator.exceptions.CalculatorException;
import ru.nsu.voronova.operation.exceptions.OperationException;

public interface Calculator {
  Number calculate(String expression) throws CalculatorException, OperationException;
}
