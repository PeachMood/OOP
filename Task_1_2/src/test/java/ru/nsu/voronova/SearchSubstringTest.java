package ru.nsu.voronova;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import java.io.FileReader;
import java.io.IOException;
import java.io.StringReader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Random;

class SearchSubstringTest {

  private String generateRandomText(String pattern, int numberOfPatterns, int textLength) {
    byte[] array = new byte[textLength];
    new Random().nextBytes(array);
    String randomString = new String(array, StandardCharsets.UTF_8);

    int stringLength = randomString.length();
    StringBuilder stringBuffer = new StringBuilder(randomString);
    for (int i = 0; i < numberOfPatterns; ++i) {
      int randomIndex = new Random().nextInt(stringLength);
      stringBuffer.insert(randomIndex, pattern.toCharArray());
    }

    return stringBuffer.toString();
  }

  private ArrayList<Integer> referenceSearch(String pattern, String text) {
    ArrayList<Integer> result = new ArrayList<>();
    int index = 0;
    while (index != -1) {
      index = text.indexOf(pattern, index);
      if (index != -1) {
        result.add(index);
        index++;
      }
    }
    return result;
  }

  @Test
  public void testSearch_emptyPattern() throws IOException {
    String pattern = "";
    String textString = "Have a good day!";
    ArrayList<Integer> actual = SearchSubstring.search(pattern, new StringReader(textString));
    assertNull(actual);
  }

  @Test
  public void testSearch_emptyString() throws IOException {
    String pattern = "Wish you all the best!";
    String textString = "";
    ArrayList<Integer> actual = SearchSubstring.search(pattern, new StringReader(textString));
    assertNull(actual);
  }

  @Test
  public void testSearch_smallString() throws IOException {
    String pattern = "빺";
    String textString = "滌빺呺뚖똼埉뤌빺ﺤ丕䓂鿦빺쉦艪빺";
    ArrayList<Integer> actual = SearchSubstring.search(pattern, new StringReader(textString));
    ArrayList<Integer> expected = referenceSearch(pattern, textString);
    Assertions.assertEquals(expected, actual);
  }

  @Test
  public void testSearch_middleString() throws IOException {
    String pattern = "ĩ۳ώʁԏݝ͑н֥ؓؓȫ";
    String text = generateRandomText(pattern, 10, 1000);
    ArrayList<Integer> actual = SearchSubstring.search(pattern, new StringReader(text));
    ArrayList<Integer> expected = referenceSearch(pattern, text);
    Assertions.assertEquals(expected, actual);
  }

  @Test
  public void testSearch_largeString() throws IOException {
    String pattern = "Ւޤͤ̈ޙؚևٳψιߜߣݕƍٳΗҋڲΙߑ";
    String text = generateRandomText(pattern, 100, 4000);
    ArrayList<Integer> actual = SearchSubstring.search(pattern, new StringReader(text));
    ArrayList<Integer> expected = referenceSearch(pattern, text);
    Assertions.assertEquals(expected, actual);
  }

  @Test
  public void testSearch_emptyFile() throws IOException {
    String pattern = "Happy New Year!";
    FileReader textFile = new FileReader("src/test/resources/emptyFile.txt");
    ArrayList<Integer> actual = SearchSubstring.search(pattern, textFile);
    assertNull(actual);
  }

  @Test
  public void testSearch_smallFile() throws IOException {
    String pattern = "튦⡱翏ꓫꢴ";
    FileReader textFile = new FileReader("src/test/resources/smallFile.txt");
    ArrayList<Integer> actual = SearchSubstring.search(pattern, textFile);
    String textString = Files.readString(Paths.get("src/test/resources/smallFile.txt"));
    ArrayList<Integer> expected = referenceSearch(pattern, textString);
    Assertions.assertEquals(expected, actual);
  }

  @Test
  public void testSearch_middleFile() throws IOException {
    String pattern = "賹闯줃쇜킘铙㖈턢栨ἤ";
    FileReader textFile = new FileReader("src/test/resources/middleFile.txt");
    ArrayList<Integer> actual = SearchSubstring.search(pattern, textFile);
    String textString = Files.readString(Paths.get("src/test/resources/middleFile.txt"));
    ArrayList<Integer> expected = referenceSearch(pattern, textString);
    Assertions.assertEquals(expected, actual);
  }

  @Test
  public void testSearch_largeFile() throws IOException {
    String pattern = "씌Aנӕɼ햧ȩܤʊ)ٸⅎ֧nHGD靝'݂WҰ&^̈߫kE1ȿֿ8DˊS̅t萳寶굳x湙۰'®4Ki'";
    FileReader textFile = new FileReader("src/test/resources/largeFile.txt");
    ArrayList<Integer> actual = SearchSubstring.search(pattern, textFile);
    String textString = Files.readString(Paths.get("src/test/resources/largeFile.txt"));
    ArrayList<Integer> expected = referenceSearch(pattern, textString);
    Assertions.assertEquals(expected, actual);
  }
}