package ru.nsu.voronova;

/**
 * This class generalizes the classes of exceptions for the class {@link RecordBook}.
 *
 * @see NoQualifyingWorkException
 * @see IncorrectGradeFormatException
 * @see SemesterNumberOutOfBoundsException
 * @see DefectiveRecordBookException
 */
public class RecordBookException extends Exception {
  /**
   * Constructs an RecordBookException without detail message.
   */
  public RecordBookException() {
    super();
  }

  /**
   * Constructs an RecordBookException with detail message.
   *
   * @param errorMessage - the detail message.
   */
  public RecordBookException(String errorMessage) {
    super(errorMessage);
  }
}
