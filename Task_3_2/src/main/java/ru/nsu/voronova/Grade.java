package ru.nsu.voronova;

/**
 * This class identifies all types of grades that a student can receive for attestation
 * and assigns them a certain number of points used to calculate the average score.
 *
 * @author <a href="https://github.com/PeachMood">Ann Voronova</a>
 */
public enum Grade {
  EXCELLENT(5.0),
  GOOD(4.0),
  SATISFACTORY(3.0),
  UNSATISFACTORY(2.0),
  PASS(5.0),
  FAIL(2.0);

  private final double value;

  /**
   * Constructs the grade.
   *
   * @param grade - the grade as a double number.
   */
  Grade(double grade) {
    this.value = grade;
  }

  /**
   * Returns the grade as a double number.
   *
   * @return grade number.
   */
  public double getValue() {
    return value;
  }
}
