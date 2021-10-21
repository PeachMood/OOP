package ru.nsu.voronova;

/**
 * Thrown to indicate if the grade for the semester was specified incorrectly.
 */
public class IncorrectGradeFormatException extends RecordBookException {
  /**
   * Constructs an IncorrectGradeFormatException without detail message.
   */
  public IncorrectGradeFormatException() {
    super();
  }
}
