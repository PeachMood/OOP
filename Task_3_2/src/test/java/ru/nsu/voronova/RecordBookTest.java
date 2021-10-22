package ru.nsu.voronova;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RecordBookTest {

  @Test
  public void testRecordBook_throwsException(){
    assertThrows(
            DefectiveRecordBookException.class,
            () -> new RecordBook(-5, "Ann", "Voronova", "Informatics and computer engineering", 3));
    assertThrows(
            DefectiveRecordBookException.class,
            () -> new RecordBook(202023, "", "Voronova", "Informatics and computer engineering", 3));
    assertThrows(
            SemesterNumberOutOfBoundsException.class,
            () -> new RecordBook(202023, "Ann", "Voronova", "Informatics and computer engineering", -10));
    assertThrows(
            SemesterNumberOutOfBoundsException.class,
            () -> new RecordBook(202023, "Ann", "Voronova", "Informatics and computer engineering", 20000));
  }

  @Test
  public void testAverageScore() throws RecordBookException {
    double expected;
    double actual;

    RecordBook book = new RecordBook(202023, "Ann", "Voronova",  "Informatics and computer engineering", 3);
    book.addExam(1, "Introduction to algebra and analysis", Grade.EXCELLENT, "Vaskevich");
    expected = 5.0;
    actual = book.getAverageScore();
    assertEquals(expected, actual);

    book.addExam(1, "Introduction to discrete mathematics", Grade.EXCELLENT, "Vlasov");
    book.addCredit(1, "Declarative programming", Grade.EXCELLENT, "Vlasov");
    book.addExam(2, "Introduction to algebra and analysis", Grade.GOOD, "Vaskevich");
    book.addExam(3, "OS", Grade.SATISFACTORY, "Saint papa");
    expected = 22.0 / 5.0;
    actual = book.getAverageScore();
    assertEquals(expected, actual);

    book.addCredit(1, "Imperative programming", Grade.EXCELLENT, "Zaitsev");
    book.addCredit(2, "Physical education", Grade.FAIL, "Oparin");
    book.addCredit(2, "English", Grade.GOOD, "Khotskina");
    book.addExam(3, "Philosophy", Grade.UNSATISFACTORY, "Aristotle");
    expected = 35.0 / 9.0;
    actual = book.getAverageScore();
    assertEquals(expected, actual);
  }

  @Test
  public void testGetHonorsDegree() throws RecordBookException {
    RecordBook book1 = new RecordBook(1994, "Kim", "Namjoon", "BTS", 8);
    assertFalse(book1.hasHonorsDegree());

    book1.addExam(1,"Dancing", Grade.EXCELLENT, "Jhope");
    book1.addExam(2,"Dancing", Grade.GOOD, "Jhope");
    book1.addExam(3,"Dancing", Grade.EXCELLENT, "Jhope");
    book1.addCredit(1, "Singing", Grade.EXCELLENT, "V");
    book1.addCredit(2, "Singing", Grade.EXCELLENT, "V");
    book1.addCredit(3, "Singing", Grade.GOOD, "V");
    book1.addExam(1, "Smiling", Grade.EXCELLENT, "Jimin");
    book1.addExam(1, "Reading", Grade.EXCELLENT, "Sugar");
    book1.addQualifyingWork("Map of the Soul: 7", Grade.EXCELLENT, "Billboard");
    assertTrue(book1.hasHonorsDegree());

    RecordBook book2 = new RecordBook(1993, "Kim", "Seok-jin", "BTS", 8);
    book1.addExam(1,"Dancing", Grade.EXCELLENT, "Jhope");
    book1.addExam(2,"Dancing", Grade.EXCELLENT, "Jhope");
    book1.addCredit(1, "Singing", Grade.SATISFACTORY, "V");
    book1.addCredit(2, "Singing", Grade.EXCELLENT, "V");
    book1.addCredit(3, "Singing", Grade.EXCELLENT, "V");
    book1.addExam(1, "Smiling", Grade.EXCELLENT, "Jimin");
    book1.addExam(1, "Reading", Grade.EXCELLENT, "Sugar");
    book1.addQualifyingWork("Map of the Soul: 7", Grade.EXCELLENT, "Billboard");
    assertFalse(book2.hasHonorsDegree());

    RecordBook book3 = new RecordBook(1993, "Park", "Jimin", "BTS", 8);
    book1.addExam(1,"Dancing", Grade.EXCELLENT, "Jhope");
    book1.addExam(2,"Smiling", Grade.EXCELLENT, "Jhope");
    book1.addCredit(1, "Singing", Grade.EXCELLENT, "V");
    book1.addCredit(2, "Reading", Grade.EXCELLENT, "V");
    book1.addQualifyingWork("Map of the Soul: 7", Grade.GOOD, "Billboard");
    assertFalse(book3.hasHonorsDegree());
  }

  @Test
  public void hasHighScholarship() throws RecordBookException {
    RecordBook book1 = new RecordBook(1994, "Kim", "Namjoon", "BTS", 5);
    book1.addExam(1,"Dancing", Grade.SATISFACTORY, "Jhope");
    book1.addCredit(1, "Drawing", Grade.FAIL, "Kim Seok-jin");
    book1.addExam(3,"Dancing", Grade.EXCELLENT, "Jhope");
    book1.addExam(4,"Smiling", Grade.EXCELLENT, "Jhope");
    book1.addCredit(3, "Singing", Grade.EXCELLENT, "V");
    book1.addCredit(4, "Reading", Grade.EXCELLENT, "V");
    assertTrue(book1.hasHighScholarship());

    RecordBook book2 = new RecordBook(1994, "Kim", "Seok-jin", "BTS", 3);
    book2.addExam(2,"Dancing", Grade.EXCELLENT, "Jhope");
    book2.addExam(2,"Smiling", Grade.SATISFACTORY, "Jhope");
    book2.addCredit(2, "Singing", Grade.EXCELLENT, "V");
    book2.addCredit(3, "Reading", Grade.EXCELLENT, "V");
    book2.addExam(3, "Smiling", Grade.EXCELLENT, "Jhope");
    assertFalse(book2.hasHighScholarship());
  }
}