package ru.nsu.voronova;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Arrays;

public class SearchSubstring {
  private static int[] zFunction(String pattern, String text) {
    int patternLength = pattern.length();
    int textLength = text.length();

    int stringLength = patternLength + textLength;
    String string = pattern + text;

    int left = 0;
    int right = 0;
    int[] z = new int[stringLength];
    for (int i = 1; i < stringLength; ++i) {
      z[i] = (right > i) ? Math.min(z[i - left], right - i) : 0;

      while (z[i] < patternLength && i + z[i] < stringLength && string.charAt(z[i]) == string.charAt(i + z[i])) {
        z[i]++;
      }

      if (i + z[i] > right) {
        left = i;
        right = i + z[i];
      }
    }

    return z;
  }

  public static ArrayList<Integer> search(String pattern, Reader textReader) throws IOException {
    int patternLength = pattern.length();
    if (patternLength == 0) return null;

    final int bufferSize = 6 * patternLength;
    char[] buffer = new char[bufferSize];

    BufferedReader text = new BufferedReader(textReader);
    int readCount = text.read(buffer, 0, bufferSize);
    int numberOfElements = readCount;
    if (readCount == -1) return null;

    int shift = 0;
    int[] z;
    ArrayList<Integer> result = new ArrayList<>();

    textProcessing:
    while (readCount != -1 && numberOfElements != 0) {
      z = zFunction(pattern, new String(buffer, 0, numberOfElements));

      for (int i = patternLength; i < z.length; ++i) {
        int indexInBuffer = i - patternLength;

        if (z[i] == patternLength) {
          result.add(indexInBuffer + shift);
        } else if (z[i] > 0 && bufferSize - indexInBuffer < patternLength) {
          int afterIndex = numberOfElements - indexInBuffer;
          buffer = Arrays.copyOfRange(buffer, indexInBuffer, bufferSize + indexInBuffer);
          shift += indexInBuffer;
          readCount = text.read(buffer, afterIndex, indexInBuffer);
          numberOfElements = afterIndex;
          if (readCount != -1) {
            numberOfElements += readCount;
          }
          continue textProcessing;
        }
      }

      shift += numberOfElements;
      readCount = text.read(buffer, 0, bufferSize);
      numberOfElements = readCount;
    }

    text.close();
    return result;
  }
}

