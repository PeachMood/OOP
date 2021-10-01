package ru.nsu.voronova;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;

public class SearchSubstring {
  public static boolean isEmptyString(BufferedReader reader) throws IOException {
    if (reader.markSupported()) {
      reader.mark(1);
    }

    if (reader.read() == -1) {
      reader.reset();
      return true;
    }
    reader.reset();
    return false;
  }

  public static int getStringLength(BufferedReader reader, int maxStringLength) throws IOException {
    if (reader.markSupported()) {
      reader.mark(maxStringLength);
    }
    int length = 0;
    while (reader.read() != -1) length++;
    reader.reset();
    return length;
  }

  private static ArrayList<Integer> zFunction(BufferedReader pattern, BufferedReader text) throws IOException {
    if (isEmptyString(pattern)) return null;

    if (isEmptyString(text)) return null;

    final int maxStringLength = 2048;
    final int patternLength = getStringLength(pattern, maxStringLength);
    char[] patternBuffer = new char[patternLength];

    int readCount = pattern.read(patternBuffer, 0, patternLength);

    int bufferSize = 6 * patternLength;
    char[] buffer = Arrays.copyOf(patternBuffer, bufferSize);

    int shift = 0;
    int left = 0;
    int right = 0;
    int[] z = new int[patternLength];
    ArrayList<Integer> result = new ArrayList<>();
    for (int i = 1; readCount != -1; ++i) {
      int currentZ = (right > i) ? Math.min(z[i - left], right - i) : 0;

      int index = i - shift;
      if (index == bufferSize || i == patternLength) {
        shift += readCount;
        index -= readCount;
        readCount = text.read(buffer, 0, bufferSize);
        bufferSize = readCount;
        if (readCount != -1) {
          buffer = Arrays.copyOfRange(buffer, 0, readCount);
        }
      }

      while (currentZ < patternLength && index + currentZ < bufferSize && patternBuffer[currentZ] == buffer[index + currentZ]) {
        currentZ++;
        if (i >= patternLength && index + currentZ == bufferSize) {
          int afterIndex = bufferSize - index;
          buffer = Arrays.copyOfRange(buffer, index, bufferSize + index);
          shift += readCount;
          readCount = text.read(buffer, afterIndex, index);
          bufferSize = afterIndex;

          if (readCount != -1) {
            bufferSize += readCount;
          }

          shift -= afterIndex;
          index = i - shift;
        }
      }

      if (i < patternLength) {
        z[index] = currentZ;
      }

      if (i + currentZ > right) {
        left = i;
        right = i + currentZ;
      }

      if (i >= patternLength && currentZ == patternLength) {
        result.add(i - patternLength);
      }
    }
    return result;
  }

  public static ArrayList<Integer> search(Reader patternReader, Reader textReader) throws IOException {
    BufferedReader pattern = new BufferedReader(patternReader);
    BufferedReader text = new BufferedReader(textReader);

    ArrayList<Integer> result = zFunction(pattern, text);

    pattern.close();
    text.close();
    return result;
  }

  public static void main(String[] args) throws IOException {
    SearchSubstring.search(new FileReader("pattern1.txt"), new FileReader("text1.txt"));
  }
}

