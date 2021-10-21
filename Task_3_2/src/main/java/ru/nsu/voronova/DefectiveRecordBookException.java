package ru.nsu.voronova;

/**
 * Thrown to indicate that a field referenced in a errorMessage parameter has been assigned an invalid value.
 */
public class DefectiveRecordBookException extends RecordBookException {

  /**
   * Constructs an DefectiveRecordBookException with detail message.
   * @param errorMessage - the detail message.
   */
  public DefectiveRecordBookException(String errorMessage) {
    super(errorMessage + "Please create another record book.");
  }
}