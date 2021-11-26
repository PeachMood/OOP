package ru.nsu.voronova;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.*;

/**
 * NotebookWriter class allows reading notes from JSON files and writing them in a suitable format to JSON files.
 */
public class NotebookWriter {
  private final String DEFAULT_FILE_NAME = "Notebook";
  private final File file;
  private final String fileName;
  private BufferedReader fileReader;

  /**
   * Creates an instance of the class NotebookWriter and sets the default file name for storing notes.
   */
  public NotebookWriter() {
    this.fileName = DEFAULT_FILE_NAME;
    this.file = new File(this.fileName + ".json");
  }

  /**
   * Creates an instance of the class NotebookWriter and sets the specified file name for storing notes.
   *
   * @param fileName - the name of the file for storing notes.
   */
  public NotebookWriter(String fileName) {
    this.fileName = fileName;
    this.file = new File(this.fileName + ".json");
  }

  /**
   * Allows getting the name of the file associated with this writer.
   *
   * @return the file name.
   */
  public String getFileName() {
    return fileName;
  }

  /**
   * Checks for the existence of a file associated with this writer.
   *
   * @return <b>true</b> if file exists, <b>false</b> otherwise.
   */
  public boolean exists() {
    return file.exists();
  }

  /**
   * Creates, if necessary, a file for storing notes with the specified name {@link #fileName} and opens it for reading.
   *
   * @throws IOException there are problems if there are problems when working with the file.
   */
  public void open() throws IOException {
    if (!exists()) {
      file.createNewFile();
    }
    fileReader = new BufferedReader(new FileReader(file));
  }

  /**
   * Reads all text from the file.
   *
   * @param reader - buffered reader associated with the file.
   * @return read text from the file.
   * @throws IOException there are problems if there are problems when reading from the file.
   */
  private String readAllLines(BufferedReader reader) throws IOException {
    StringBuilder content = new StringBuilder();
    String currentLine;
    while ((currentLine = reader.readLine()) != null) {
      content.append(currentLine).append("\n");
    }
    return content.toString();
  }

  /**
   * Reads all notes from the file.
   *
   * @return the array of read notes.
   * @throws IOException there are problems if there are problems when reading notes.
   */
  public Note[] read() throws IOException {
    String content = readAllLines(fileReader);
    if (content.equals("")) {
      return null;
    }
    Gson gson = new GsonBuilder().setPrettyPrinting().create();
    return gson.fromJson(content, Note[].class);
  }

  /**
   * Writes notes from specified array to the file.
   *
   * @param notes - the array of notes to write.
   * @throws IOException there are problems if there are problems when writing notes to the file.
   */
  public void write(Note[] notes) throws IOException {
    BufferedWriter fileWriter = new BufferedWriter(new FileWriter(file, false));
    Gson gson = new GsonBuilder().setPrettyPrinting().create();
    String content = gson.toJson(notes, Note[].class);
    fileWriter.append(content);
    fileWriter.close();
  }

  /**
   * Closes buffered reader associated with the file.
   *
   * @throws IOException could not close buffered reader.
   */
  public void close() throws IOException {
    fileReader.close();
  }
}

