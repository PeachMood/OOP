package ru.nsu.voronova;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Stack;

public class JSON {
  private final String FILE_NAME = "Notebook.json";

  public JSON() {
  }

  public boolean fileExists(){
    File file = new File(FILE_NAME);
    return !file.exists();
  }

  public void create() {
    try {
      File file = new File(FILE_NAME);
      file.createNewFile();
    } catch (IOException exception) {
      System.err.println("Could not create " + FILE_NAME + " file.");
    }
  }

  public Stack<Note> readFile() {
    if (fileExists()) {
      create();
    }

    String content = "";
    try {
      byte[] encoded = Files.readAllBytes(Paths.get(FILE_NAME));
      content = new String(encoded, StandardCharsets.UTF_8);
    } catch (IOException exception) {
      System.err.println("Could not read " + FILE_NAME + " file.");
    }

    if (content.equals("")) {
      System.err.println("Empty " + FILE_NAME + " file.");
      return null;
    }

    Gson gson = new GsonBuilder().setPrettyPrinting().create();
    Type notebookType = new TypeToken<Stack<Note>>(){}.getType();
    return gson.fromJson(content, notebookType);
  }

  public void writeToFile(Stack<Note> notebook) {
    if (fileExists()) {
      create();
    }

    Gson gson = new GsonBuilder().setPrettyPrinting().create();
    String content = gson.toJson(notebook);
    try {
      BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME, false));
      writer.append(content);
      writer.close();
    } catch (IOException exception) {
      System.err.println("Could not write to " + FILE_NAME + " file.");
    }
  }
}
