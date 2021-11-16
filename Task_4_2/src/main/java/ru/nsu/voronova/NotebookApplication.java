package ru.nsu.voronova;

import org.apache.commons.cli.*;

import java.util.Stack;

public class NotebookApplication {
  private Options options;
  private final JSON json;

  public NotebookApplication() {
    initializeOptions();
    json = new JSON();
  }

  private void initializeOptions() {
    options = new Options();
    Option add = Option.builder("add").numberOfArgs(2).build();
    Option remove = Option.builder("rm").numberOfArgs(1).build();
    Option show = Option.builder("show").numberOfArgs(2).optionalArg(true).build();
    options.addOption(add);
    options.addOption(remove);
    options.addOption(show);
  }

  private void add(String title, String content) {
    if (json.fileExists()) {
      json.create();
    }
    Note note = new Note(title, content);
    Stack<Note> notebook = json.readFile();
    notebook.push(note);
    json.writeToFile(notebook);
  }

  private Stack<Note> removeNote(String title) {
    Stack<Note> notebook = json.readFile();
    Stack<Note> newNotebook = new Stack<>();
    for (int i = 0; i < notebook.size(); ++i) {
      Note note = notebook.pop();
      if (!note.getTitle().equals(title)) {
        newNotebook.push(note);
      }
    }
    return newNotebook;
  }

  private void remove(String title) {
    if (json.fileExists()) {
      System.err.println("You haven't created any notes yet.");
      return;
    }
    Stack<Note> notebook = removeNote(title);
    json.writeToFile(notebook);
  }

  public void run(String[] args) {
    CommandLineParser parser = new DefaultParser();
    try {
      CommandLine line = parser.parse(options, args);
      if (line.hasOption("add")) {
        add(args[1], args[2]);
      }
      if (line.hasOption("rm")) {
        remove(args[1]);
      }
    } catch (ParseException exception) {
      System.err.println("Operation failed. Reason: " + exception.getMessage());
    }
  }
}
