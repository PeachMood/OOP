package ru.nsu.voronova;

import java.io.*;
import java.lang.*;
import org.apache.commons.io.FileUtils;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;

public class ZAlgorithm {

  private static int createFile(String fileName) throws IOException {
    File file = new File(fileName);
    if (!file.exists()) {
      file.createNewFile();
      return 1;
    }
    return 0;
  }

  private static void ZFunction(String pattern, String text) {
    int patternLength = pattern.length();
    int textLength = text.length();
    String string = pattern + "$" + text;
    int[] z = new int[patternLength + textLength + 1];
    int left = 0;
    int right = 0;
    for (int i = 1; i < string.length(); ++i) {
      z[i] = (right > i) ? Math.min(z[i - left], right - i) : 0;
      while (i + z[i] < string.length() && string.charAt(z[i]) == string.charAt(i + z[i])) {
        z[i]++;
      }
      if (i + z[i] > right) {
        left = i;
        right = i + z[i];
      }
      if (z[i] == patternLength) {
        int index = i - patternLength - 1;
        System.out.print(index + " ");
      }
    }
  }

  public static void matchPattern(String fileWithPattern, String fileWithText) throws IOException {
    String pattern = FileUtils.readFileToString(new File(fileWithPattern), StandardCharsets.UTF_8);
    String text = FileUtils.readFileToString(new File(fileWithText), StandardCharsets.UTF_8);

    int patternLength = pattern.length();
    int textLength = text.length();
    if (patternLength == 0) {
      System.out.println("Pattern is an empty string. An empty string is a substring of any string.");
      return;
    }
    if (textLength == 0) {
      System.out.println("The size of string can't be less than the size of its substring.");
      return;
    }
    ZFunction(pattern, text);
  }

  public static void main(String[] args) throws Exception {
    matchPattern("pattern.txt", "text.txt");
  }
}

