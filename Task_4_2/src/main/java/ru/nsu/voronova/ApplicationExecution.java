package ru.nsu.voronova;

import org.apache.commons.cli.ParseException;

import java.io.IOException;

public class ApplicationExecution {
  public static void main(String[] args) throws IOException, ParseException, java.text.ParseException {
    NotebookApplication application = new NotebookApplication();
    String[] arguments1 = {"-add", "Java", "Лучший предмет на свете!"};
    application.run(arguments1);

    String[] arguments2 = {"-add", "JavaScript", "undefined"};
    application.run(arguments2);

    String[] arguments3 = {"-add", "Python", "Современный"};
    application.run(arguments3);

    String[] arguments4 = {"-remove", "Python"};
    application.run(arguments4);

    String[] arguments5 = {"-add", "Kotlin", "The best", "-t" , "MyNotebook"};
    application.run(arguments5);

    String[] arguments6 = {"-show", "-t" , "MyNotebook"};
    application.run(arguments6);

    String[] arguments7 = {"-show", "2021.11.21 10:37", "2021.11.21 10:40", "Java"};
    application.run(arguments7);
  }
}
