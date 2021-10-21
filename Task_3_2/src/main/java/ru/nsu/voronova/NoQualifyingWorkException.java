package ru.nsu.voronova;

/**
 * Thrown to indicate if the qualifying work was not added to diploma.
 */
public class NoQualifyingWorkException extends RecordBookException {
  /**
   * Constructs an NoQualifyingWorkException without detail message.
   */
  public NoQualifyingWorkException() {
    super();
  }
}
