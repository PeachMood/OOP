
package ru.nsu.voronova;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;


import static org.junit.jupiter.api.Assertions.*;

class SearchSubstringTest {

  private static int getStringLength(Reader reader, int maxStringLength) throws IOException {
    if (reader.markSupported()) {
      reader.mark(maxStringLength);
    }
    int length = 0;
    while (reader.read() != -1) length++;
    reader.reset();
    return length;
  }

  private String readerToString(Reader reader) throws IOException {
    final int patternLength = getStringLength(reader, 2048);
    char[] buffer = new char[patternLength];
    reader.read(buffer, 0, patternLength);
    return String.valueOf(buffer);
  }

  private ArrayList<Integer> referenceSearch(Reader patternReader, Reader textReader) throws IOException {
    String pattern = readerToString(patternReader);
    final int patternLength = pattern.length();

    BufferedReader text = new BufferedReader(textReader);
    int bufferSize = 6 * patternLength;
    char[] buffer = new char[bufferSize];

    ArrayList<Integer> result = new ArrayList<>();

    int index;
    int shift = 0;
    int readCount = text.read(buffer, 0,  bufferSize);
    while ( readCount != -1) {
      String temp = String.valueOf(buffer);
      index = temp.indexOf(pattern);
      result.add(index - shift);
      int afterIndex = bufferSize - index;
      buffer = Arrays.copyOfRange(buffer, index, bufferSize + index);
      shift += readCount;
      readCount = text.read(buffer, afterIndex, index);
      bufferSize = afterIndex;
    }

    return result;
  }

  @Test
  public void testSearch_emptyPattern() throws IOException {
    String patternString = "";
    String textString = "Have a good day!";
    ArrayList<Integer> result = SearchSubstring.search(new StringReader(patternString), new StringReader(textString));
    assertNull(result);
  }

  @Test
  public void testSearch_emptyString() throws IOException {
    String patternString = "Wish you all the best!";
    String textString = "";
    ArrayList<Integer> result = SearchSubstring.search(new StringReader(patternString), new StringReader(textString));
    assertNull(result);
  }

  @Test
  public void testSearch_smallString() throws IOException {
    String patternString = "빺";
    String textString = "滌빺呺뚖똼埉뤌빺ﺤ丕䓂鿦빺쉦艪빺";
    ArrayList<Integer> result = SearchSubstring.search(new StringReader(patternString), new StringReader(textString));
    ArrayList<Integer> expected = referenceSearch(new StringReader(patternString), new StringReader(textString));
    Assertions.assertEquals(result, expected);
  }
}
