package ru.nsu.voronova;

import java.io.*;
import java.util.*;
import java.lang.*;

public class ZAlgorithm {

  private static int createFile(String fileName) throws IOException {
    File file = new File(fileName);
    if (!file.exists()) {
      file.createNewFile();
      return 1;
    }
    return 0;
  }

  private static void ZFunction(String pattern, BufferedReader text) throws Exception {
    int patternLength = pattern.length();
    int[] zValues = new int[patternLength];

    int left = 0;
    int right = 0;
    for (int i = 1; i < patternLength; ++i) {
      zValues[i] = (right > i) ? Math.min(zValues[i - left], right - i) : 0;
      while (i + zValues[i] < patternLength && pattern.charAt(zValues[i]) == pattern.charAt(i + zValues[i])) {
        zValues[i]++;
      }
      if (i + zValues[i] > right) {
        left = i;
        right = i + zValues[i];
      }
    }

    char symbol;
    CircularArrayList<Character> buffer = new CircularArrayList<>(patternLength);
    for (int i = 0; i < patternLength; ++i) {
      symbol = (char) text.read();
      if (symbol == (char)-1) {
        System.out.println("The required substring cannot be shorter than the original string");
        buffer.insert(symbol);
        return;
      }
    }

    int currentZValue;
    System.out.print("{ ");
    for (int i = 1; (symbol = (char) text.read()) != (char)-1; ++i) {
      if (i != 1) {
        buffer.insert(symbol);
      }

      currentZValue = (right > i) ? Math.min(zValues[i - left], right - i) : 0;
      while (currentZValue < buffer.size() && pattern.charAt(currentZValue) == buffer.get(currentZValue)) {
        currentZValue++;
      }
      if (i + currentZValue > right) {
        left = i;
        right = i + currentZValue;
      }
      if (currentZValue == patternLength) {
        System.out.print(i + " ");
      }
      buffer.removeOldest();
    }
    System.out.print( '}');
  }

  public static void matchPattern(String pattern, String fileWithText, String outputFile) throws Exception {
    if (pattern.length() == 0) {
      System.out.println("Pattern is an empty string. An empty string is a substring of any string.");
      return;
    }

    String encoding = "UTF-8";
    BufferedReader text;
    try {
      text = new BufferedReader(new InputStreamReader(new FileInputStream(fileWithText), encoding));
    } catch (FileNotFoundException | UnsupportedEncodingException e) {
      System.out.println(e.getMessage());
      return;
    }

    ZFunction(pattern, text);

    if (createFile(outputFile) == 0) return;
    BufferedWriter output;
    try {
      output = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(outputFile), encoding));
      output.write('!');
    } catch (FileNotFoundException | UnsupportedEncodingException e) {
      System.out.println(e.getMessage());
      return;
    }

    output.close();
    text.close();
  }

  public static void main(String[] args) throws Exception {
    matchPattern("Hello", "input.txt", "output.txt");
  }
}


class CircularArrayList<Character> extends AbstractList<Character> implements RandomAccess {

  private final int n; // buffer length
  private final List<Character> buf; // a List implementing RandomAccess
  private int leader = 0;
  private int size = 0;


  public CircularArrayList(int capacity) {
    n = capacity + 1;
    buf = new ArrayList<>(Collections.nCopies(n, null));
  }

  private int wrapIndex(int i) {
    int m = i % n;
    if (m < 0) { // modulus can be negative
      m += n;
    }
    return m;
  }

  @Override
  public int size() {
    return this.size;
  }

  @Override
  public Character get(int i) {
    if (i < 0 || i >= n - 1) throw new IndexOutOfBoundsException();

    if (i > size()) throw new NullPointerException("Index is greater than size.");

    return buf.get(wrapIndex(leader + i));
  }

  @Override
  public Character set(int i, Character e) {
    if (i < 0 || i >= n - 1) {
      throw new IndexOutOfBoundsException();
    }
    if (i == size()) // assume leader's position as invalid (should use insert(e))
      throw new IndexOutOfBoundsException("The size of the list is " + size() + " while the index was " + i
              + ". Please use insert(e) method to fill the list.");
    return buf.set(wrapIndex(leader - size + i), e);
  }

  public void insert(Character e) {
    int s = size();
    buf.set(wrapIndex(leader), e);
    leader = wrapIndex(++leader);
    buf.set(leader, null);
    if (s == n - 1)
      return; // we have replaced the eldest element.
    this.size++;

  }

  @Override
  public void clear() {
    int cnt = wrapIndex(leader - size());
    for (; cnt != leader; cnt = wrapIndex(++cnt))
      this.buf.set(cnt, null);
    this.size = 0;
  }

  public void removeOldest() {
    int i = wrapIndex(leader + 1);

    for (; ; i = wrapIndex(++i)) {
      if (buf.get(i) != null) break;
      if (i == leader)
        throw new IllegalStateException("Cannot remove element."
                + " CircularArrayList is empty.");
    }

    this.size--;
  }

  @Override
  public String toString() {
    int i = wrapIndex(leader - size());
    StringBuilder str = new StringBuilder(size());

    for (; i != leader; i = wrapIndex(++i)) {
      str.append(buf.get(i));
    }
    return str.toString();
  }
}
