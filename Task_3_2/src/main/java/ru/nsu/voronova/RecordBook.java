package ru.nsu.voronova;

public class RecordBook {
  private final int recordBookNumber;
  private final Student student;
  private Semester[] semesters;
  private Diploma diploma;

  private final int LAST_SEMESTER_NUMBER = 8;
  private double averageScore;

  public RecordBook(int recordBookNumber, String studentName, String studentSurname, String studentSpeciality, int currentSemester) throws DefectiveRecordBookException {
    if (recordBookNumber < 0) {
      throw new DefectiveRecordBookException("Record book record book number cannot be negative. ");
    }
    if (studentName == null || studentName.equals("")) {
      throw new DefectiveRecordBookException("You have not provided the student's name. ");
    }
    if (studentSurname == null || studentSurname.equals("")) {
      throw new DefectiveRecordBookException("You have not provided the student's surname. ");
    }
    if (studentSpeciality == null || studentSpeciality.equals("")) {
      throw new DefectiveRecordBookException("You have not provided the student's speciality. ");
    }
    if (currentSemester <= 0) {
      throw new DefectiveRecordBookException("Semester number cannot be less or equal than zero. ");
    }
    if (currentSemester > LAST_SEMESTER_NUMBER) {
      int studyPeriod = LAST_SEMESTER_NUMBER / 2;
      throw new DefectiveRecordBookException("The student's study period is " + studyPeriod + " year" + (studyPeriod == 1 ? "" : "s") + ".Probably you graduated from university and enrolled again.");
    }
    this.recordBookNumber = recordBookNumber;
    this.student = new Student(studentName, studentSurname, studentSpeciality, currentSemester);
    this.semesters = new Semester[LAST_SEMESTER_NUMBER];
    this.diploma = new Diploma();
    this.averageScore = 0.0;
  }

  public int getRecordBookNumber() {
    return recordBookNumber;
  }

  public String getStudentName() {
    return student.getName();
  }

  public String getStudentSurname() {
    return student.getSurname();
  }

  public String getStudentSpeciality() {
    return student.getSpeciality();
  }

  public int getCurrentSemester() {
    return student.getCurrentSemester();
  }

  public void setCurrentSemester(int semesterNumber) {
    if (semesterNumber <= 0) {
      System.out.println("Semester number cannot be less or equal than zero.");
      return;
    }
    if (semesterNumber > LAST_SEMESTER_NUMBER) {
      int studyPeriod = LAST_SEMESTER_NUMBER / 2;
      System.out.println("The student's study period is " + studyPeriod + " year" + (studyPeriod == 1 ? "" : "s") + ".Probably you graduated from university and enrolled again.");
      return;
    }
    student.setCurrentSemester(semesterNumber);
  }

  public QualifyingWork getQualifyingWork() {
    QualifyingWork qualifyingWork = diploma.getQualifyingWork();
    if (qualifyingWork == null) {
      System.out.println("You have not yet written a qualifying work.");
    }
    return qualifyingWork;
  }

  public double getAverageScore() {
    return averageScore;
  }

  private double calculateAverageScore() {
    double sumOfGrades = 0.0;
    double numberOfSubjects = 0.0;
    for (Semester semester : semesters) {
      if (semester != null) {
        sumOfGrades += semester.getSumOfGrades();
        numberOfSubjects += semester.getNumberOfSubjects();
      }
    }
    return sumOfGrades / numberOfSubjects;
  }

  public void addCredit(int semesterNumber, String credit, Grade grade, String teacher) {
    if (semesterNumber > student.getCurrentSemester()) {
      System.out.println("You have completed the current semester, please replace the current semester with a new one.");
      return;
    }
    semesterNumber--;
    if (semesters[semesterNumber] == null) {
      semesters[semesterNumber] = new Semester(semesterNumber + 1);
    }
    semesters[semesterNumber].addAttestation(credit, grade, teacher);
    diploma.addSubjectToSupplement(credit, grade, teacher);
    averageScore = calculateAverageScore();
  }

  public void addExam(int semester, String exam, Grade grade, String teacher) {
    if (grade == Grade.PASS || grade == Grade.FAIL) {
      System.out.println("Incorrect grade format.");
      return;
    }
    addCredit(semester, exam, grade, teacher);
  }

  public void addQualifyingWork(String topic, Grade grade, String teacher) {
    diploma.addQualifyingWork(topic, grade, teacher);
  }

  public boolean hasHonorsDegree() {
    return diploma.isHonorsDegree();
  }

  public boolean hasHighScholarship() {
    int currentSemester = student.getCurrentSemester() - 1;
    if (currentSemester < 2) return true;
    Semester semester1 = semesters[currentSemester - 2];
    Semester semester2 = semesters[currentSemester - 1];
    if (semester1 == null || semester2 == null) {
      System.out.println("No information about past semesters.");
      return false;
    }
    double result1 = semester1.getSumOfGrades() / semester1.getNumberOfSubjects();
    double result2 = semester2.getSumOfGrades() / semester2.getNumberOfSubjects();
    return result1 == 5.0 && result2 == 5.0;
  }
}
