package ru.nsu.voronova;

import org.apache.commons.cli.*;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;


public class NotebookApplication {
  private final Options options;
  private JSON json;

  public NotebookApplication() {
    options = new Options();
    Option title = Option.builder("t").hasArg().optionalArg(false).build();
    Option add = Option.builder("add").numberOfArgs(2).optionalArg(false).build();
    Option remove = Option.builder("remove").hasArg().optionalArg(false).build();
    Option show = Option.builder("show").hasArgs().optionalArg(true).build();
    options.addOption(title);
    options.addOption(add);
    options.addOption(remove);
    options.addOption(show);
  }

  private void add(Note note) throws IOException {
    Notebook notebook = new Notebook(json.getFileName());
    notebook.addNotes(json.read());
    notebook.addNote(note);
    json.write(notebook.getAllNotes());
  }

  private void remove(String title) throws IOException {
    Notebook notebook = new Notebook(json.getFileName());
    notebook.addNotes(json.read());
    notebook.removeNote(title);
    json.write(notebook.getAllNotes());
  }

  private void show() throws IOException {
    Notebook notebook = new Notebook(json.getFileName());
    Note[] notes = json.read();
    if (notes == null || notes.length == 0) {
      System.out.println("Could not find notes.");
      return;
    }
    notebook.addNotes(notes);
    System.out.println(notebook);
  }

  private void show(String date1, String date2) throws IOException, java.text.ParseException {
    SimpleDateFormat formatter = new SimpleDateFormat("yyyy.MM.dd HH:mm");
    Date before = formatter.parse(date1);
    Date after = formatter.parse(date2);

    Notebook notebook = new Notebook(json.getFileName());
    notebook.addNotes(json.read());
    Note[] notes = notebook.getNotesByDate(before, after);
    if (notes == null || notes.length == 0) {
      System.out.println("Could not find notes.");
      return;
    }
    Notebook filtered = new Notebook(json.getFileName());
    filtered.addNotes(notes);
    System.out.println(filtered);
  }

  private void show(String date1, String date2, String[] keywords) throws IOException, java.text.ParseException {
    SimpleDateFormat formatter = new SimpleDateFormat("yyyy.MM.dd HH:mm");
    Date before = formatter.parse(date1);
    Date after = formatter.parse(date2);

    Notebook notebook = new Notebook(json.getFileName());
    notebook.addNotes(json.read());
    Note[] notes = notebook.getNotesByDateAndKeywords(before, after, keywords);
    if (notes == null || notes.length == 0) {
      System.out.println("Could not find notes.");
      return;
    }
    Notebook filtered = new Notebook(json.getFileName());
    filtered.addNotes(notes);
    System.out.println(filtered);
  }

  private void parseLine(CommandLine line) throws IOException, java.text.ParseException {
    json = new JSON();
    if (line.hasOption("t")) {
      json = new JSON(line.getOptionValue("t"));
    }

    json.open();
    if (line.hasOption("add")) {
      String[] values = line.getOptionValues("add");
      Note note = new Note(values[0], values[1]);
      add(note);
    }

    if (line.hasOption("remove")) {
      remove(line.getOptionValue("remove"));
    }

    if (line.hasOption("show")) {
      String[] values = line.getOptionValues("show");
      if (values == null) {
        show();
        return;
      }
      if(values.length == 2) {
        show(values[0], values[1]);
      }
      show(values[0], values[1], Arrays.copyOfRange(values, 2, values.length));
    }
    json.close();
  }

  public void run(String[] args) throws IOException, ParseException, java.text.ParseException {
    CommandLineParser parser = new DefaultParser();
    CommandLine line = parser.parse(options, args);
    parseLine(line);
  }
}
