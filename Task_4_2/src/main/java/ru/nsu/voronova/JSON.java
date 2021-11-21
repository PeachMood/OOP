package ru.nsu.voronova;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.*;

public class JSON {
  private final String DEFAULT_FILE_NAME = "Notebook";
  private final File file;
  private final String fileName;
  private BufferedReader fileReader;
  private BufferedWriter fileWriter;

  public JSON() {
    this.fileName = DEFAULT_FILE_NAME;
    this.file = new File(this.fileName + ".json");
  }

  public JSON(String fileName) {
    this.fileName = fileName;
    this.file = new File(this.fileName + ".json");
  }

  public String getFileName() {
    return fileName;
  }

  public boolean exists() {
    return file.exists();
  }

  public void open() throws IOException {
    if (!exists()) {
      file.createNewFile();
    }
    fileReader = new BufferedReader(new FileReader(file));
  }

  private String readAllLines(BufferedReader reader) throws IOException {
    StringBuilder content = new StringBuilder();
    String currentLine;
    while ((currentLine = reader.readLine()) != null) {
      content.append(currentLine).append("\n");
    }
    return content.toString();
  }

  public Note[] read() throws IOException {
    String content = readAllLines(fileReader);
    if (content.equals("")) {
      return null;
    }
    Gson gson = new GsonBuilder().setPrettyPrinting().create();
    return gson.fromJson(content, Note[].class);
  }

  public void write(Note[] notes) throws IOException {
    fileWriter = new BufferedWriter(new FileWriter(file, false));
    Gson gson = new GsonBuilder().setPrettyPrinting().create();
    String content = gson.toJson(notes, Note[].class);
    fileWriter.append(content);
    fileWriter.close();
  }

  public void close() throws IOException {
    fileReader.close();
  }
}

