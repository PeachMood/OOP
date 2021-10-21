package ru.nsu.voronova;


import java.util.HashMap;
import java.util.Map;

/**
 * This class allows you to add and change information about the results of
 * intermediate attestation for the current semester.
 *
 * @author <a href="https://github.com/PeachMood">Ann Voronova</a>
 */
public class Semester {
  private final int semesterNumber;
  private Map<String, Subject> subjects;

  private double sumOfGrades;

  /**
   * Constructs an instance of the Semester class with its number.
   *
   * @param semesterNumber - the number of semester.
   */
  public Semester(int semesterNumber) {
    this.semesterNumber = semesterNumber;
    this.sumOfGrades = 0.0;
    this.subjects = new HashMap<>();
  }

  /**
   * Returns the number of semester.
   *
   * @return the number of semester.
   */
  public int getSemesterNumber() {
    return semesterNumber;
  }

  /**
   * Returns the number of subjects, that were added to this semester.
   *
   * @return the number of subjects.
   */
  public double getNumberOfSubjects() {
    return subjects.size();
  }

  /**
   * Returns the sum of grades, that student got in this semester. Used for calculation of the average score.
   *
   * @return the sum of grades.
   */
  public double getSumOfGrades() {
    return sumOfGrades;
  }

  /**
   * Returns the result of the subject in this semester.
   *
   * @param name - the name of the subject.
   * @return
   */
  public Subject getSubjectByName(String name) {
    return subjects.get(name);
  }

  /**
   * /**
   * Adds attestation results to the record book.
   *
   * @param name    - the name of the subject.
   * @param grade   - the grade the student received.
   * @param teacher - the teacher who gave the grade.
   */
  public void addAttestation(String name, Grade grade, String teacher) {
    if (subjects.containsKey(name)) return;
    sumOfGrades += grade.getValue();
    Subject subject = new Subject(name, grade, teacher);
    subjects.put(name, subject);
  }
}
