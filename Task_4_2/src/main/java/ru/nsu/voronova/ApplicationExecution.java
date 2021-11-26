package ru.nsu.voronova;

/**
 * ApplicationExecution is a class with main method, which allows starting application.
 */
public class ApplicationExecution {
  /**
   * Get arguments from command line and pass them to run method from {@link NotebookApplication}
   *
   * @param args - arguments from command line.
   */
  public static void main(String[] args) {
    NotebookApplication application = new NotebookApplication();
    application.run(args);
  }
}
