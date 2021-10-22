package ru.nsu.voronova;

import java.util.ArrayList;

/**
 * This class allows to create an analogue of the record book, which is issued to students upon admission to a
 * university.
 * Also, this class provides methods for obtaining and adding information to a grade book,
 * working with a diploma and intermediate certification.
 * <p>
 * If you like this project, please contact the author and get the author a job
 *
 * @author <a href="https://github.com/PeachMood">Ann Voronova</a>
 */
public class RecordBook {
  private final int recordBookNumber;
  private final Student student;
  private Semester[] semesters;
  private Diploma diploma;

  private final int LAST_SEMESTER_NUMBER = 8;
  private double averageScore;

  /**
   * Constructs record book with specified number and information about owner of this record book.
   *
   * @param recordBookNumber - the number of the record book.
   * @param student          - the owner of the record book.
   * @throws RecordBookException - if parameters were specified incorrectly.
   */
  public RecordBook(int recordBookNumber, Student student) throws RecordBookException {
    if (recordBookNumber < 0) {
      throw new DefectiveRecordBookException("Record book record book number cannot be negative. ");
    }
    if (student.getName() == null || student.getName().equals("")) {
      throw new DefectiveRecordBookException("You have not provided the student's name. ");
    }
    if (student.getSurname() == null || student.getSurname().equals("")) {
      throw new DefectiveRecordBookException("You have not provided the student's surname. ");
    }
    if (student.getSpeciality() == null || student.getSpeciality().equals("")) {
      throw new DefectiveRecordBookException("You have not provided the student's speciality. ");
    }
    if (student.getCurrentSemester() <= 0) {
      throw new SemesterNumberOutOfBoundsException("Semester number cannot be less or equal than zero.");
    }
    if (student.getCurrentSemester() > LAST_SEMESTER_NUMBER) {
      int studyPeriod = LAST_SEMESTER_NUMBER / 2;
      throw new SemesterNumberOutOfBoundsException("The student's study period is " + studyPeriod + " years " +
              ".Probably you graduated from university and enrolled again.");
    }
    this.recordBookNumber = recordBookNumber;
    this.student = student;
    this.semesters = new Semester[LAST_SEMESTER_NUMBER];
    this.diploma = new Diploma();
    this.averageScore = 0.0;
  }

  /**
   * Another constructor for the record book.
   *
   * @param recordBookNumber  - the number of the record book.
   * @param studentName       - the name of the owner of the book.
   * @param studentSurname    - the surname of the owner of the book.
   * @param studentSpeciality - the speciality of the owner of the book.
   * @param currentSemester   - the number of the semester in which the student is currently studying.
   * @throws RecordBookException - if parameters were specified incorrectly.
   */
  public RecordBook(int recordBookNumber, String studentName, String studentSurname, String studentSpeciality,
                    int currentSemester) throws RecordBookException {
    this(recordBookNumber, new Student(studentName, studentSurname, studentSpeciality, currentSemester));
  }

  /**
   * Allows to get the grade book number.
   *
   * @return the grade book number.
   */
  public int getRecordBookNumber() {
    return recordBookNumber;
  }

  /**
   * Allows to get the name of the owner of the book.
   *
   * @return name of the owner.
   */
  public String getStudentName() {
    return student.getName();
  }

  /**
   * Allows to get the surname of the owner of the book.
   *
   * @return surname of the owner.
   */
  public String getStudentSurname() {
    return student.getSurname();
  }

  /**
   * Allows to get the speciality of the owner of the book.
   *
   * @return speciality of the owner.
   */
  public String getStudentSpeciality() {
    return student.getSpeciality();
  }

  /**
   * Allows to get the number of semester in which the student is currently studying.
   *
   * @return the number of semester.
   */
  public int getCurrentSemester() {
    return student.getCurrentSemester();
  }

  /**
   * Allows to get all results of attestations for one subject.
   *
   * @param name - the name of the subject, for which results should be found.
   * @return - array, consisting of one subject with different results.
   */
  public Subject[] getSubjectResults(String name) {
    ArrayList<Subject> result = new ArrayList<>();
    for (Semester semester : semesters) {
      if (semester != null) {
        Subject subject = semester.getSubjectByName(name);
        if (subject != null) {
          result.add(subject);
        }
      }
    }
    return result.toArray(new Subject[0]);
  }

  /**
   * Changes the number of semester, in which the student is currently studying.
   *
   * @param semesterNumber - the number of semester.
   * @throws SemesterNumberOutOfBoundsException- if the number of semester was specified incorrectly.
   */
  public void setCurrentSemester(int semesterNumber) throws SemesterNumberOutOfBoundsException {
    if (semesterNumber <= 0) {
      throw new SemesterNumberOutOfBoundsException("Semester number cannot be less or equal than zero.");
    }
    if (semesterNumber > LAST_SEMESTER_NUMBER) {
      int studyPeriod = LAST_SEMESTER_NUMBER / 2;
      throw new SemesterNumberOutOfBoundsException("The student's study period is " + studyPeriod + " years " +
              ".Probably you graduated from university and enrolled again.");
    }
    student.setCurrentSemester(semesterNumber);
  }

  /**
   * Allows getting information about qualifying work in diploma.
   *
   * @return qualifying work.
   * @throws NoQualifyingWorkException - if qualifying work has not yet been added to the diploma.
   */
  public QualifyingWork getQualifyingWork() throws NoQualifyingWorkException {
    QualifyingWork qualifyingWork = diploma.getQualifyingWork();
    if (qualifyingWork == null) {
      throw new NoQualifyingWorkException();
    }
    return qualifyingWork;
  }

  /**
   * Returns the average score for all intermediate attestations specified in the record book.
   *
   * @return average score.
   */
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

  /**
   * Adds the result of credit in the record book.
   *
   * @param semester - the number of semester during which the student passed the credit.
   * @param credit   - the name of the subject.
   * @param grade    - the grade the student received.
   * @param teacher  - the teacher who gave the grade.
   * @throws SemesterNumberOutOfBoundsException - if the number of semester was specified incorrectly.
   */
  public void addCredit(int semester, String credit, Grade grade, String teacher) throws SemesterNumberOutOfBoundsException {
    if (semester > student.getCurrentSemester()) {
      throw new SemesterNumberOutOfBoundsException("You cannot add an attestation for a semester that you have not " +
              "yet passed on.");
    }
    semester--;
    if (semesters[semester] == null) {
      semesters[semester] = new Semester(semester + 1);
    }
    semesters[semester].addAttestation(credit, grade, teacher);
    diploma.addSubjectToSupplement(credit, grade, teacher);
    averageScore = calculateAverageScore();
  }

  /**
   * Adds results of exam to the record book.
   *
   * @param semester - the number of semester during which the student passed the credit.
   * @param exam     - the name of the subject.
   * @param grade    - the grade the student received.
   * @param teacher  - the teacher who gave the grade.
   * @throws RecordBookException - if some parameters was specified incorrectly.
   */
  public void addExam(int semester, String exam, Grade grade, String teacher) throws RecordBookException {
    if (grade == Grade.PASS || grade == Grade.FAIL) {
      throw new IncorrectGradeFormatException();
    }
    addCredit(semester, exam, grade, teacher);
  }

  /**
   * @see Diploma#addQualifyingWork(String, Grade, String).
   */
  public void addQualifyingWork(String topic, Grade grade, String teacher) throws IncorrectGradeFormatException {
    diploma.addQualifyingWork(topic, grade, teacher);
  }

  /**
   * @see Diploma#isHonorsDegree().
   */
  public boolean hasHonorsDegree() {
    return diploma.isHonorsDegree();
  }

  /**
   * Determines if the student has high scholarship in the current semester.
   *
   * @return <b>true</b>, if the student has high scholarship, <b>false</b> otherwise.
   */
  public boolean hasHighScholarship() {
    int currentSemester = student.getCurrentSemester() - 1;
    if (currentSemester < 2) return false;
    Semester semester1 = semesters[currentSemester - 2];
    Semester semester2 = semesters[currentSemester - 1];
    if (semester1 == null || semester2 == null) return false;
    double result1 = semester1.getSumOfGrades() / semester1.getNumberOfSubjects();
    double result2 = semester2.getSumOfGrades() / semester2.getNumberOfSubjects();
    return result1 == 5.0 && result2 == 5.0;
  }
}
