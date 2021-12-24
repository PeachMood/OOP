package ru.nsu.voronova.calculator;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.nsu.voronova.calculator.exceptions.CalculatorException;
import ru.nsu.voronova.calculator.exceptions.UnknownArgumentException;
import ru.nsu.voronova.number.Complex;
import ru.nsu.voronova.operation.exceptions.InvalidNumberArgumentsException;
import ru.nsu.voronova.operation.exceptions.OperationException;

import static org.junit.jupiter.api.Assertions.*;

class EngineeringCalculatorTest {
  private EngineeringCalculator calculator;

  @BeforeEach
  void init() {
    calculator = new EngineeringCalculator();
  }

  @Test
  void testCalculate_NullPointerException() {
    String expression = null;
    assertThrows(NullPointerException.class, () -> calculator.calculate(expression));
  }

  @Test
  void testCalculate_throwsUnknownArgumentException() {
    String expression = "&*%#";
    assertThrows(UnknownArgumentException.class, () -> calculator.calculate(expression));
  }

  @Test
  void testCalculate_throwsInvalidNumberArgumentsException() {
    String expression = "4 pow 1 3";
    assertThrows(InvalidNumberArgumentsException.class, () -> calculator.calculate(expression));
  }

  @Test
  void testCalculate_doubleAddition() throws CalculatorException, OperationException {
    String expression = "+ 1.5 1.3";
    String actual = calculator.calculate(expression).toString();
    String expected = "2.8";
    assertEquals(expected, actual);
  }

  @Test
  void testCalculate_complexAddition() throws CalculatorException, OperationException {
    String expression = "+ (1,2) (3,4)";
    String actual = calculator.calculate(expression).toString();
    String expected = new Complex(4, 6).toString();
    assertEquals(expected, actual);
  }

  @Test
  void testCalculate_doubleSubtraction() throws CalculatorException, OperationException {
    String expression = "- 4.0 2.0";
    String actual = calculator.calculate(expression).toString();
    String expected = "2.0";
    assertEquals(expected, actual);
  }

  @Test
  void testCalculate_complexSubtraction() throws CalculatorException, OperationException {
    String expression = "- (4,4) (1,2)";
    String actual = calculator.calculate(expression).toString();
    String expected = new Complex(3, 2).toString();
    assertEquals(expected, actual);
  }

  @Test
  void testCalculate_doubleMultiplication() throws CalculatorException, OperationException {
    String expression = "* 5.0 5.0";
    String actual = calculator.calculate(expression).toString();
    String expected = "25.0";
    assertEquals(expected, actual);
  }

  @Test
  void testCalculate_complexMultiplication() throws CalculatorException, OperationException {
    String expression = "* (2,2) (4,3)";
    String actual = calculator.calculate(expression).toString();
    String expected = new Complex(2, 14).toString();
    assertEquals(expected, actual);
  }

  @Test
  void testCalculate_doubleDivision() throws CalculatorException, OperationException {
    String expression = "/ 3.14 0.85";
    String actual = calculator.calculate(expression).toString();
    String expected = Double.toString(3.14 / 0.85);
    assertEquals(expected, actual);
  }

  @Test
  void testCalculate_complexDivision() throws CalculatorException, OperationException {
    String expression = "/ (2,2) (4,3)";
    String actual = calculator.calculate(expression).toString();
    String expected = new Complex(0.56, 0.08).toString();
    assertEquals(expected, actual);
  }

  @Test
  void testCalculate_doublePower() throws CalculatorException, OperationException {
    String expression = "pow 2.0 2.0";
    String actual = calculator.calculate(expression).toString();
    String expected = "4.0";
    assertEquals(expected, actual);
  }

  @Test
  void testCalculate_complexPower() throws CalculatorException, OperationException {
    String expression = "pow (5,4) (3,6)";
    String actual = calculator.calculate(expression).toString();
    String expected = new Complex(0.5260732563575677, 4.5506706765669485).toString();
    assertEquals(expected, actual);
  }

  @Test
  void testCalculate_doubleSinus() throws CalculatorException, OperationException {
    String expression = "sin 3.0";
    String actual = calculator.calculate(expression).toString();
    String expected = Double.toString(Math.sin(3.0));
    assertEquals(expected, actual);
  }

  @Test
  void testCalculate_complexSinus() throws CalculatorException, OperationException {
    String expression = "sin (5,7)";
    String actual = calculator.calculate(expression).toString();
    String expected = new Complex(-525.7945152216543, 155.53654985241286).toString();
    assertEquals(expected, actual);
  }


  @Test
  void testCalculate_doubleCosine() throws CalculatorException, OperationException {
    String expression = "cos 1.0";
    String actual = calculator.calculate(expression).toString();
    String expected = Double.toString(Math.cos(1.0));
    assertEquals(expected, actual);
  }

  @Test
  void testCalculate_complexCosine() throws CalculatorException, OperationException {
    String expression = "cos (6,6)";
    String actual = calculator.calculate(expression).toString();
    String expected = new Complex(193.6813601575594, 56.3617823598949).toString();
    assertEquals(expected, actual);
  }

  @Test
  void testCalculate_doubleLogarithm() throws CalculatorException, OperationException {
    String expression = "log 2.14";
    String actual = calculator.calculate(expression).toString();
    String expected = Double.toString(Math.log(2.14));
    assertEquals(expected, actual);
  }

  @Test
  void testCalculate_complexLogarithm() throws CalculatorException, OperationException {
    String expression = "log (4,4)";
    String actual = calculator.calculate(expression).toString();
    String expected = new Complex(1.7328679513998633, 0.7853981633974483).toString();
    assertEquals(expected, actual);
  }

  @Test
  void testCalculate_doubleSquareRoot() throws CalculatorException, OperationException {
    String expression = "sqrt 3.230847";
    String actual = calculator.calculate(expression).toString();
    String expected = Double.toString(Math.sqrt(3.230847));
    assertEquals(expected, actual);
  }

  @Test
  void testCalculate_complexSquareRoot() throws CalculatorException, OperationException {
    String expression = "sqrt (4,4)";
    String actual = calculator.calculate(expression).toString();
    String expected = new Complex(1.5537739740300374, 0.6435942529055827).toString();
    assertEquals(expected, actual);
  }

  @Test
  void testCalculate_doubleDegrees() throws CalculatorException, OperationException {
    String expression = "sin deg 30";
    String actual = calculator.calculate(expression).toString();
    String expected = Double.toString(Math.sin(Math.PI / 6.0));
    assertEquals(expected, actual);
  }

  @Test
  void testCalculate_complexDegrees() throws CalculatorException, OperationException {
    String expression = "deg (6,7)";
    String actual = calculator.calculate(expression).toString();
    String expected = new Complex(6.0 * (Math.PI / 180.0), 7.0 * (Math.PI / 180.0)).toString();
    assertEquals(expected, actual);
  }

  @Test
  void testCalculate_expression() throws CalculatorException, OperationException {
    String expression = "* sin + 3 9 (1,2)";
    String actual = calculator.calculate(expression).toString();
    String expected = new Complex(-0.5365729180004349, -1.0731458360008699).toString();
    assertEquals(expected, actual);
  }
}