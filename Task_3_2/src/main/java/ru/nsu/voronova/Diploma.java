package ru.nsu.voronova;

import java.util.HashMap;

public class Diploma {
  private QualifyingWork qualifyingWork;
  private HashMap<String, Subject> supplement;

  private double satisfactoryInRecordBook;
  private double excellentInSupplement;
  private double numberOfSubjects;

  public Diploma() {
    qualifyingWork = null;
    supplement = new HashMap<>();
    satisfactoryInRecordBook = 0.0;
    excellentInSupplement = 0.0;
    numberOfSubjects = 0.0;
  }

  public QualifyingWork getQualifyingWork() {
    return qualifyingWork;
  }

  public void addQualifyingWork(String topic, Grade grade, String teacher) {
    if (qualifyingWork != null) {
      System.out.println("You cannot change an existing qualifying work.");
      return;
    }
    if (grade == Grade.PASS || grade == Grade.FAIL) {
      System.out.println("Incorrect grading format for the qualifying work.");
      return;
    }
    qualifyingWork = new QualifyingWork(topic, grade, teacher);
  }

  public void addSubjectToSupplement(String name, Grade grade, String teacher) {
    if (grade == Grade.SATISFACTORY) {
      satisfactoryInRecordBook++;
    }
    if (!supplement.containsKey(name)) {
      numberOfSubjects++;
    } else {
      Subject inSupplement = supplement.get(name);
      if (inSupplement.grade() != grade && grade == Grade.EXCELLENT) {
        excellentInSupplement++;
      }
    }
    Subject subject = new Subject(name, grade, teacher);
    supplement.put(name, subject);
  }

  public boolean isHonorsDegree() {
    if (satisfactoryInRecordBook == 0) return true;
    if (excellentInSupplement / numberOfSubjects >= 0.75) return true;
    return qualifyingWork != null && qualifyingWork.grade() == Grade.EXCELLENT;
  }
}
