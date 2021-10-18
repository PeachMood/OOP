package ru.nsu.voronova;

public class Student {
  private final String name;
  private final String surname;
  private final String speciality;
  private int currentSemester;

  public Student(String name, String surname, String speciality, int currentSemester) {
    this.name = name;
    this.surname = surname;
    this.speciality = speciality;
    this.currentSemester = currentSemester;
  }

  public String getName() {
    return name;
  }

  public String getSurname() {
    return surname;
  }

  public String getSpeciality() {
    return speciality;
  }

  public int getCurrentSemester() {
    return currentSemester;
  }

  public void setCurrentSemester(int semester) {
    currentSemester = semester;
  }
}
