package ru.nsu.voronova;


import java.util.HashMap;

public class Semester {
  private final int semesterNumber;
  private HashMap<String, Subject> subjects;

  private double sumOfGrades;
  private double numberOfSubjects;

  public Semester(int semesterNumber) {
    this.semesterNumber = semesterNumber;
    this.numberOfSubjects = 0.0;
    this.sumOfGrades = 0.0;
    this.subjects = new HashMap<>();
  }

  public int getSemesterNumber() {
    return semesterNumber;
  }

  public double getNumberOfSubjects() {
    return numberOfSubjects;
  }

  public double getSumOfGrades() {
    return sumOfGrades;
  }

  public Subject getSubjectByName(String name) {
    Subject subject = null;
    if (subjects.containsKey(name)) {
      subject = subjects.get(name);
    }
    return subject;
  }

  public void addAttestation(String name, Grade grade, String teacher) {
    if (subjects.containsKey(name)) {
      System.out.println("You cannot overwrite the attestation results.");
      return;
    }
    numberOfSubjects++;
    sumOfGrades += grade.getValue();
    Subject subject = new Subject(name, grade, teacher);
    subjects.put(name, subject);
  }

}
