package ru.nsu.voronova;

/**
 * Thrown to indicate if the number of the semester was specified as negative or too big number.
 */
public class SemesterNumberOutOfBoundsException extends RecordBookException {
  /**
   * Constructs an SemesterNumberOutOfBoundsException with detail message.
   *
   * @param errorMessage - the detail message.
   */
  public SemesterNumberOutOfBoundsException(String errorMessage) {
    super(errorMessage);
  }
}
