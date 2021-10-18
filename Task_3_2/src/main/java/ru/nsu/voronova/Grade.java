package ru.nsu.voronova;

public enum Grade {
  EXCELLENT(5.0),
  GOOD(4.0),
  SATISFACTORY(3.0),
  UNSATISFACTORY(2.0),
  PASS(5.0),
  FAIL(2.0);

  private final double value;

  Grade(double grade) {
    this.value = grade;
  }

  public double getValue() {
    return value;
  }
}
