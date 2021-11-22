package ru.nsu.voronova;

import java.util.*;
import java.util.stream.Stream;

public class Notebook {
  private final String title;
  private Stack<Note> notebook;

  public Notebook(String title) {
    this.title = title;
    this.notebook = new Stack<>();
  }

  public void addNote(Note note) {
    if (note == null) {
      throw new NullPointerException();
    }
    notebook.push(note);
  }

  public void addNote(String title, String content) {
    Note note = new Note(title, content);
    addNote(note);
  }

  public void addNotes(Note[] notes) {
    if (notes == null) {
      return;
    }
    for (Note note : notes) {
      addNote(note);
    }
  }

  public void removeNote(String title) {
    Stack<Note> newNotebook = new Stack<>();
    int notebookSize = notebook.size();
    for (int i = 0; i < notebookSize; ++i) {
      Note note = notebook.pop();
      if (!note.getTitle().equals(title)) {
        newNotebook.push(note);
      }
    }
    notebook = newNotebook;
  }

  public String getTitle() {
    return title;
  }

  public Note[] getAllNotes() {
    return notebook.toArray(new Note[0]);
  }

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

  public Note[] getNotesByDate(Date after) {
    Stream<Note> notebookStream = Arrays.stream(getAllNotes());
    return notebookStream
            .filter(note -> (note.getCreationDate().after(after) || note.getCreationDate().equals(after)))
            .toArray(Note[]::new);
  }

  public Note[] getNotesByDate(Date after, Date before) {
    Stream<Note> notebookStream = Arrays.stream(getAllNotes());
    return notebookStream
            .filter(note -> (note.getCreationDate().after(after) || note.getCreationDate().equals(after)))
            .filter(note -> (note.getCreationDate().before(before) || note.getCreationDate().equals(before)))
            .toArray(Note[]::new);
  }

  public Note[] getNotesByDateAndKeywords(Date after, Date before, String[] keywords) {
    Note[] byKeywords = getNotesByKeywords(keywords);
    Stream<Note> notebookStream = Arrays.stream(byKeywords);
    return notebookStream
            .filter(note -> (note.getCreationDate().after(after) || note.getCreationDate().equals(after)))
            .filter(note -> (note.getCreationDate().before(before) || note.getCreationDate().equals(before)))
            .toArray(Note[]::new);
  }

  @Override
  public String toString() {
    StringBuilder notebook = new StringBuilder("---" + title + "---\n");
    Note[] notes = getAllNotes();
    for (Note note : notes) {
      notebook.append(note).append("\n").append("\n");
    }
    return notebook.toString();
  }
}
