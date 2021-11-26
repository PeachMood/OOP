package ru.nsu.voronova;

import org.apache.commons.cli.*;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * NotebookApplication is a class for parsing command line and performing the specified operations on the notebook.
 * Uses JSON files for storing data.
 */
public class NotebookApplication {
  private final Options options;
  private NotebookWriter writer;

  /**
   * Creates an instance of the class NotebookApplication and describes operations for working with a notebook.
   */
  public NotebookApplication() {
    options = new Options();
    Option help = new Option("help", "print Notebook Application help information");
    Option title = Option
            .builder("title")
            .desc("apply operations for a notebook with the specified title")
            .hasArg().optionalArg(false)
            .argName("notebook")
            .build();
    Option add = Option
            .builder("add")
            .desc("add new note with specified title and content to a notebook")
            .numberOfArgs(2)
            .optionalArg(false)
            .argName("title> <content")
            .build();
    Option remove = Option
            .builder("remove")
            .desc("remove note with specified title from a notebook")
            .hasArg()
            .optionalArg(false)
            .argName("title")
            .build();
    Option show = Option
            .builder("show")
            .desc("show all notes from a notebook (without arguments) or show notes, that were created in specified " +
                    "time range and contain in title specified keywords")
            .hasArgs()
            .optionalArg(true)
            .argName("before> <after> <keywords")
            .build();

    options.addOption(help);
    options.addOption(title);
    options.addOption(add);
    options.addOption(remove);
    options.addOption(show);
  }

  /**
   * Displays a description of all the operations available in Notebook Application.
   */
  private void help() {
    HelpFormatter formatter = new HelpFormatter();
    formatter.printHelp("notebook.jar", options);
  }

  /**
   * Saves specified note in JSON file associated with a notebook.
   *
   * @param note - the note to save
   * @throws IOException if there are problems when working with the file.
   */
  private void add(Note note) throws IOException {
    Notebook notebook = new Notebook(writer.getFileName());
    notebook.addNote(note);
    notebook.addNotes(writer.read());
    writer.write(notebook.getAllNotes());
  }

  /**
   * Removes note by title from a JSON file associated with a notebook.
   *
   * @param title - the name of the note to be removed.
   * @throws IOException if there are problems when working with the file.
   */
  private void remove(String title) throws IOException {
    Notebook notebook = new Notebook(writer.getFileName());
    notebook.addNotes(writer.read());
    notebook.removeNote(title);
    writer.write(notebook.getAllNotes());
  }

  /**
   * Displays all notes from a JSON file associated with a notebook.
   *
   * @throws IOException if there are problems when working with the file.
   */
  private void show() throws IOException {
    Notebook notebook = new Notebook(writer.getFileName());
    Note[] notes = writer.read();
    if (notes == null || notes.length == 0) {
      System.out.println("Could not find notes.");
      return;
    }
    notebook.addNotes(notes);
    System.out.println(notebook);
  }

  /**
   * Displays all notes, created in specified time range, from a JSON file associated with a notebook.
   *
   * @param date1 - the beginning of the time range.
   * @param date2 - the end of the time range.
   * @throws IOException              if there are problems when working with the file.
   * @throws java.text.ParseException if the date was specified in the wrong format.
   */
  private void show(String date1, String date2) throws IOException, java.text.ParseException {
    SimpleDateFormat formatter = new SimpleDateFormat("yyyy.MM.dd HH:mm");
    Date before = formatter.parse(date1);
    Date after = formatter.parse(date2);

    Notebook notebook = new Notebook(writer.getFileName());
    notebook.addNotes(writer.read());
    Note[] notes = notebook.getNotesByDate(before, after);
    if (notes == null || notes.length == 0) {
      System.out.println("Could not find notes.");
      return;
    }
    Notebook filtered = new Notebook(writer.getFileName());
    filtered.addNotes(notes);
    System.out.println(filtered);
  }

  /**
   * Displays all notes by date and keywords from a JSON file associated with a notebook.
   *
   * @param date1    - the beginning of the time range.
   * @param date2    - the end of the time range.
   * @param keywords - words that should be included in the titles of the searched notes.
   * @throws IOException              if there are problems when working with the file.
   * @throws java.text.ParseException if the date was specified in the wrong format.
   */
  private void show(String date1, String date2, String[] keywords) throws IOException, java.text.ParseException {
    SimpleDateFormat formatter = new SimpleDateFormat("yyyy.MM.dd HH:mm");
    Date before = formatter.parse(date1);
    Date after = formatter.parse(date2);

    Notebook notebook = new Notebook(writer.getFileName());
    notebook.addNotes(writer.read());
    Note[] notes = notebook.getNotesByDateAndKeywords(before, after, keywords);
    if (notes == null || notes.length == 0) {
      System.out.println("Could not find notes.");
      return;
    }
    Notebook filtered = new Notebook(writer.getFileName());
    filtered.addNotes(notes);
    System.out.println(filtered);
  }

  /**
   * Parses command line and performs specified operations on the notebook.
   *
   * @param line - command line to parse.
   * @throws IOException              if there are problems when working with the file.
   * @throws java.text.ParseException if one of the arguments of an operation is in the wrong format.
   */
  private void parseLine(CommandLine line) throws IOException, java.text.ParseException {
    if (line.hasOption("help")) {
      help();
      return;
    }

    if (line.hasOption("title")) {
      writer = new NotebookWriter(line.getOptionValue("title"));
    } else {
      writer = new NotebookWriter();
    }

    writer.open();
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
      if (values.length == 2) {
        show(values[0], values[1]);
      }
      show(values[0], values[1], Arrays.copyOfRange(values, 2, values.length));
    }
    writer.close();
  }

  /**
   * Starts the Notebook Application and presents the arguments for parsing in an appropriate format, then passes them
   * to {@link #parseLine(CommandLine)} method.
   *
   * @param args - arguments from command line.
   */
  public void run(String[] args) {
    try {
      CommandLineParser parser = new DefaultParser();
      CommandLine line = parser.parse(options, args);
      parseLine(line);
    } catch (Exception exception) {
      help();
    }
  }
}
