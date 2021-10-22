package ru.nsu.voronova;

import java.util.HashMap;
import java.util.Map;

/**
 * This class allows to create an analogue of the diploma received by students upon graduation from the university.
 * In addition, this class provides methods for adding and retrieving information about a diploma.
 *
 * @author <a href="https://github.com/PeachMood">Ann Voronova</a>
 */
public class Diploma {
  private QualifyingWork qualifyingWork;
  private Map<String, Subject> supplement;

  private double satisfactoryInRecordBook;
  private double excellentInSupplement;
  private double numberOfSubjects;

  /**
   * Constructs diploma without any information.
   */
  public Diploma() {
    qualifyingWork = null;
    supplement = new HashMap<>();
    satisfactoryInRecordBook = 0.0;
    excellentInSupplement = 0.0;
    numberOfSubjects = 0.0;
  }

  /**
   * Allows getting information about qualifying work in diploma. If qualifying work has not yet been added to the diploma, the method returns null.
   *
   * @return qualifying work.
   */
  public QualifyingWork getQualifyingWork() {
    return qualifyingWork;
  }

  /**
   * Adds qualifying work to diploma.
   *
   * @param topic   - the topic of the qualifying work.
   * @param grade   - the grade, which was specified for the qualifying work.
   * @param teacher - the surname of the scientific leader of the qualifying work.
   * @throws IncorrectGradeFormatException - if incorrect grade was specified for the qualifying work.
   */
  public void addQualifyingWork(String topic, Grade grade, String teacher) throws IncorrectGradeFormatException {
    if (qualifyingWork != null) return;
    if (grade == Grade.PASS || grade == Grade.FAIL) {
      throw new IncorrectGradeFormatException();
    }
    qualifyingWork = new QualifyingWork(topic, grade, teacher);
  }

  /**
   * Adds the result of attestation to the diploma supplement.
   * For the same subject, only the result of the last attestation is taken into attestation.
   *
   * @param name    - the name of the subject for which the certification was carried out.
   * @param grade   - the grade the student received.
   * @param teacher - the teacher who gave the grade.
   */
  public void addSubjectToSupplement(String name, Grade grade, String teacher) {
    if (grade == Grade.SATISFACTORY) {
      satisfactoryInRecordBook++;
    }
    if (!supplement.containsKey(name)) {
      numberOfSubjects++;
      if (grade == Grade.EXCELLENT) {
        excellentInSupplement++;
      }
    } else {
      Subject inSupplement = supplement.get(name);
      if (inSupplement.grade() == Grade.EXCELLENT) {
        excellentInSupplement--;
      }
      if (grade == Grade.EXCELLENT) {
        excellentInSupplement++;
      }
    }
    Subject subject = new Subject(name, grade, teacher);
    supplement.put(name, subject);
  }

  /**
   * Determines if the student can get an honors degree.
   *
   * @return <b>true</b>, if the student can get an honors degree, <b>false</b> otherwise.
   */
  public boolean isHonorsDegree() {
    if (satisfactoryInRecordBook != 0) return false;
    if (excellentInSupplement / numberOfSubjects < 0.75) return false;
    return qualifyingWork != null && qualifyingWork.grade() == Grade.EXCELLENT;
  }
}
