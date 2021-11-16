package ru.nsu.voronova;

public class ApplicationExecution {
  public static void main(String[] args) {
    NotebookApplication application = new NotebookApplication();
    String[] arguments1 = {"-add", "Java", "Лучший предмет на свете!"};
    application.run(arguments1);

    String[] arguments2 = {"-add", "JavaScript", "undefined"};
    application.run(arguments2);

    String[] arguments3 = {"-rm", "JavaScript"};
    application.run(arguments3);
  }
}
