package ru.nsu.voronova;

/**
 * This class allows specifying information about the student,
 * including their name, surname, specialty in which they are studying and their current semester.
 *
 * @author <a href="https://github.com/PeachMood">Ann Voronova</a>
 */
public class Student {
  private final String name;
  private final String surname;
  private final String speciality;
  private int currentSemester;

  /**
   * Constructs an instance of the Student class with the given parameters.
   *
   * @param name            - the name of the student.
   * @param surname         - the surname of the student.
   * @param speciality      - the speciality of the student.
   * @param currentSemester - the number of the semester in which the student is currently studying.
   */
  public Student(String name, String surname, String speciality, int currentSemester) {
    this.name = name;
    this.surname = surname;
    this.speciality = speciality;
    this.currentSemester = currentSemester;
  }

  /**
   * Allows to get the name of the student.
   *
   * @return name of the student.
   */
  public String getName() {
    return name;
  }

  /**
   * Allows to get the surname of the student.
   *
   * @return surname of the owner.
   */
  public String getSurname() {
    return surname;
  }

  /**
   * Allows to get the speciality, in which the student is studying.
   *
   * @return specialty name.
   */
  public String getSpeciality() {
    return speciality;
  }

  /**
   * Allows to get the number of the current semester, in which the student is studying.
   *
   * @return the number of the semester.
   */
  public int getCurrentSemester() {
    return currentSemester;
  }

  /**
   * Changes the number of semester, in which the student is currently studying.
   *
   * @param semester - the number of semester.
   */
  public void setCurrentSemester(int semester) {
    currentSemester = semester;
  }
}
