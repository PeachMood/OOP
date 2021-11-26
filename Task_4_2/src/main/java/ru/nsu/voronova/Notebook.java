package ru.nsu.voronova;

import java.util.*;
import java.util.stream.Stream;

/**
 * This class represents a notebook and provides methods for working with it.
 */
public class Notebook {
  private final String title;
  private final Stack<Note> notebook;

  /**
   * Creates an instance of the class Notebook.
   *
   * @param title - the title of the notebook.
   */
  public Notebook(String title) {
    this.title = title;
    this.notebook = new Stack<>();
  }

  /**
   * Adds note to a notebook.
   *
   * @param note - the note to add.
   */
  public void addNote(Note note) {
    if (note == null) {
      throw new NullPointerException();
    }
    notebook.push(note);
  }

  /**
   * Creates a note with specified title and content and adds it to a notebook.
   *
   * @param title   - the title of the note.
   * @param content - the content of the note.
   */
  public void addNote(String title, String content) {
    Note note = new Note(title, content);
    addNote(note);
  }

  /**
   * Adds several notes to the notebook.
   *
   * @param notes - the notes to add.
   */
  public void addNotes(Note[] notes) {
    if (notes == null) {
      return;
    }
    for (Note note : notes) {
      addNote(note);
    }
  }

  /**
   * Removes note with specified title from the notebook.
   *
   * @param title - the name of the note to be removed.
   */
  public void removeNote(String title) {
    notebook.removeIf(note -> note.getTitle().equals(title));
  }

  /**
   * Allows getting the title of the notebook.
   *
   * @return the title of the notebook.
   */
  public String getTitle() {
    return title;
  }

  /**
   * Returns all notes from the notebook.
   *
   * @return the array of all notes.
   */
  public Note[] getAllNotes() {
    return notebook.toArray(new Note[0]);
  }

  /**
   * Returns all notes, containing in the title specified keywords, from the notebook.
   *
   * @param keywords - words that should be in the titles of the searched notes.
   * @return the array of matching notes.
   */
  public Note[] getNotesByKeywords(String[] keywords) {
    if (keywords == null) {
      throw new NullPointerException();
    }
    Stream<Note> notebookStream = Arrays.stream(getAllNotes());
    for (String keyword : keywords) {
      notebookStream = notebookStream.filter(note -> note.getTitle().contains(keyword));
    }
    return notebookStream.toArray(Note[]::new);
  }

  /**
   * Returns all notes from a notebook, created after specified time.
   *
   * @param after - specified time for searching notes.
   * @return the array of matching notes.
   */
  public Note[] getNotesByDate(Date after) {
    Stream<Note> notebookStream = Arrays.stream(getAllNotes());
    return notebookStream
            .filter(note -> (note.getCreationDate().after(after) || note.getCreationDate().equals(after)))
            .toArray(Note[]::new);
  }

  /**
   * Returns all notes from a notebook, created in specified time range.
   *
   * @param after  - the beginning of the time range.
   * @param before - the beginning of the time range.
   * @return the array of matching notes.
   */
  public Note[] getNotesByDate(Date after, Date before) {
    Stream<Note> notebookStream = Arrays.stream(getAllNotes());
    return notebookStream
            .filter(note -> (note.getCreationDate().after(after) || note.getCreationDate().equals(after)))
            .filter(note -> (note.getCreationDate().before(before) || note.getCreationDate().equals(before)))
            .toArray(Note[]::new);
  }

  /**
   * Returns all notes from a notebook by date and keywords.
   *
   * @param after    - the beginning of the time range.
   * @param before   - the beginning of the time range.
   * @param keywords - words that should be in the titles of the searched notes.
   * @return the array of matching notes.
   */
  public Note[] getNotesByDateAndKeywords(Date after, Date before, String[] keywords) {
    Note[] byKeywords = getNotesByKeywords(keywords);
    Stream<Note> notebookStream = Arrays.stream(byKeywords);
    return notebookStream
            .filter(note -> (note.getCreationDate().after(after) || note.getCreationDate().equals(after)))
            .filter(note -> (note.getCreationDate().before(before) || note.getCreationDate().equals(before)))
            .toArray(Note[]::new);
  }

  /**
   * Returns the notebook as a string.
   *
   * @return the notebook as a string.
   */
  @Override
  public String toString() {
    StringBuilder notebook = new StringBuilder("---" + title + "---\n");
    Note[] notes = getAllNotes();
    for (Note note : notes) {
      notebook.append(note).append("\n------\n");
    }
    return notebook.toString();
  }
}
