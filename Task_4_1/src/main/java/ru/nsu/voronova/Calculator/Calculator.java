package ru.nsu.voronova.Calculator;

import ru.nsu.voronova.Calculator.Exceptions.CalculatorException;
import ru.nsu.voronova.Operation.Exceptions.OperationException;

public interface Calculator {
  Number calculate(String expression) throws CalculatorException, OperationException;
}
