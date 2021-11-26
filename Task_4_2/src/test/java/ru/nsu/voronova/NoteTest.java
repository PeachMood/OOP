package ru.nsu.voronova;

import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class NoteTest {
  @Test
  public void getTitle() {
    String title = "Title 1";
    String content = "Content 1";
    Note note = new Note(title, content);
    assertEquals(title, note.getTitle());
  }

  @Test
  public void getContent() {
    String title = "Title 2";
    String content = "Content 2";
    Note note = new Note(title, content);
    assertEquals(content, note.getContent());
  }

  @Test
  public void getCreationDate() {
    Note note = new Note("Title 3", "Content 3");
    Date date = new Date();
    assertEquals(date, note.getCreationDate());
  }

  @Test
  public void testEquals_thisObject() {
    Note note = new Note("Title 4", "Content 4");
    assertEquals(note, note);
  }

  @Test
  public void testEquals_differentTypes() {
    Note note = new Note("Title 5", "Content 5");
    Integer integer = 5;
    assertNotEquals(note, integer);
  }

  @Test
  public void testEquals_sameType() {
    String title = "Title 6";
    String content = "Content 6";
    Note note1 = new Note(title, content);
    Note note2 = new Note(title, content);
    assertEquals(note1, note2);
  }
}