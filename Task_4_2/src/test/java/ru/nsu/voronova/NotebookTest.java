package ru.nsu.voronova;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class NotebookTest {
  private Notebook notebook;

  @BeforeEach
  public void initialize() {
    notebook = new Notebook("Test");
  }

  @Test
  @DisplayName("Exception adding null note")
  public void addNote_throwsNullPointerException() {
    assertThrows(NullPointerException.class, () -> notebook.addNote(null));
  }

  @Test
  public void addNote() {
    String title = "Title 1";
    String content = "Content 1";
    notebook.addNote(title, content);

    Note[] notes = notebook.getAllNotes();
    Note note = new Note(title, content);
    assertEquals(note, notes[0]);
  }

  @Test
  @DisplayName("Adding several notes to a notebook")
  public void addNotes() {
    Note[] expected = new Note[2];
    expected[0] = new Note("Title 2", "Content 2");
    expected[1] = new Note("Title 3", "Content 3");
    notebook.addNotes(expected);

    Note[] actual = notebook.getAllNotes();
    assertArrayEquals(actual, expected);
  }

  @Test
  @DisplayName("Deleting note from a notebook")
  public void removeNote() {
    String title = "Title 4";
    String content = "Content 4";
    notebook.addNote(title, content);
    notebook.removeNote(title);

    Note[] notes = notebook.getAllNotes();
    assertEquals(0, notes.length);
  }

  @Test
  @DisplayName("Getting notebook title")
  public void getTitle() {
    assertEquals("Test", notebook.getTitle());
  }

  @Test
  @DisplayName("Retrieving all notes from a notebook")
  public void getAllNotes() {
    Note[] expected = new Note[2];
    expected[0] = new Note("Title 5", "Content 5");
    expected[1] = new Note("Title 6", "Content 6");
    notebook.addNotes(expected);
    notebook.addNote("Title 7", "Content 7");
    notebook.addNote("Title 8", "Content 8");
    notebook.removeNote("Title 7");
    notebook.removeNote("Title 8");

    Note[] actual = notebook.getAllNotes();
    assertArrayEquals(expected, actual);
  }

  @Test
  @DisplayName("Exception retrieving by keywords from null array")
  public void getNotesByKeywords_throwsNullPointerException() {
    assertThrows(NullPointerException.class, () -> notebook.getNotesByKeywords(null));
  }

  @Test
  @DisplayName("Retrieving all notes from a notebook containing words from the list in the title")
  public void getNotesByKeywords() {
    Note[] expected = new Note[2];
    expected[0] = new Note("Title 9", "Content 9");
    expected[1] = new Note("Title 10", "Content 10");
    notebook.addNotes(expected);
    notebook.addNote("Undefined", "Undefined");

    Note[] actual = notebook.getNotesByKeywords(new String[]{"Title"});
    assertArrayEquals(expected, actual);
  }

  @Test
  @DisplayName("Retrieving all notes from a notebook, created after specified time")
  public void getNotesByDate() {
    Note[] expected = new Note[3];
    expected[0] = new Note("Title 11", "Content 11");
    expected[1] = new Note("Title 12", "Content 12");
    expected[2] = new Note("Title 13", "Content 13");
    notebook.addNotes(expected);

    Note[] actual = notebook.getNotesByDate(expected[0].getCreationDate());
    assertArrayEquals(expected, actual);
  }

  @Test
  @DisplayName("Retrieving all notes from a notebook, created between specified time range")
  public void testGetNotesByDate() throws InterruptedException {
    Note[] expected = new Note[2];
    expected[0] = new Note("Title 14", "Content 14");
    expected[1] = new Note("Title 15", "Content 15");
    notebook.addNotes(expected);
    Thread.sleep(1000);
    notebook.addNote("Title 16", "Content 16");
    Date before = expected[0].getCreationDate();
    Date after = expected[1].getCreationDate();
    Note[] actual = notebook.getNotesByDate(before, after);
    assertArrayEquals(expected, actual);
  }

  @Test
  @DisplayName("Retrieving all notes from a notebook by date and keywords")
  public void getNotesByDateAndKeywords() throws InterruptedException {
    Note[] expected = new Note[2];
    expected[0] = new Note("Title 17", "Content 17");
    expected[1] = new Note("Title 18", "Content 18");
    notebook.addNotes(expected);
    notebook.addNote("Undefined", "Undefined");
    Thread.sleep(1000);
    notebook.addNote("Title 19", "Content 19");
    Date before = expected[0].getCreationDate();
    Date after = expected[1].getCreationDate();
    Note[] actual = notebook.getNotesByDateAndKeywords(before, after, new String[]{"Title"});
    assertArrayEquals(expected, actual);
  }
}