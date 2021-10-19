package ru.nsu.voronova;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RecordBookTest {

  @Test
  public void testFirstRecordBook() throws DefectiveRecordBookException {
    RecordBook book = new RecordBook(202023, "Ann", "Voronova", "Informatics and computer engineering", 3);
    book.addExam(1, "Introduction to algebra and analysis", Grade.EXCELLENT, "Vaskevich");
    book.addExam(1, "Introduction to discrete mathematics", Grade.EXCELLENT, "Vlasov");
    book.addCredit(1, "Declarative programming", Grade.EXCELLENT, "Vlasov");
    book.addCredit(1, "Imperative programming", Grade.EXCELLENT, "Zaitsev");
    book.addCredit(1, "Physical education", Grade.PASS, "Oparin");

    book.addExam(2, "Introduction to algebra and analysis", Grade.GOOD, "Vaskevich");
    book.addExam(2, "Introduction to discrete mathematics", Grade.EXCELLENT, "Vlasov");
    book.addCredit(2, "Declarative programming", Grade.EXCELLENT, "Vlasov");
    book.addCredit(2, "Imperative programming", Grade.EXCELLENT, "Zaitsev");
    book.addCredit(2, "English", Grade.GOOD, "Khotskina");
    book.addCredit(2, "Physical education", Grade.PASS, "Oparin");

    double expected = 53.0 / 11.0;
    double actual = book.getAverageScore();
    assertEquals(expected, actual);
    assertFalse(book.hasHighScholarship());
    assertFalse(book.hasHonorsDegree());
  }
}