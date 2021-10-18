package ru.nsu.voronova;

public class DefectiveRecordBookException extends Exception {
  public DefectiveRecordBookException(String errorMessage) {
    super(errorMessage + "Please create another record book.");
  }
}