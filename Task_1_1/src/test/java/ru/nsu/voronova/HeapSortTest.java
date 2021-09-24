package ru.nsu.voronova;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

import java.util.*;

class HeapSortTest {

  private int randomInt(int minimum, int maximal) {
    Random random = new Random();
    return minimum + random.nextInt(maximal - minimum + 1);
  }

  private int[] generateArray(int size, int minimum, int maximum) {
    int[] temp = new int[size];
    for (int i = 0; i < size; ++i) {
      temp[i] = randomInt(minimum, maximum);
    }
    return temp;
  }

  @Test
  public void testSort_emptyArray() {
    int[] actual = new int[]{};
    HeapSort.sort(actual);
    int[] expected = new int[]{};
    Assertions.assertArrayEquals(expected, actual);
  }

  @Test
  public void testSort_unorderedArray() {
    int[] array;
    int[] expected;
    int[] actual;

    array = generateArray(1, -100, 100);
    expected = array.clone();
    actual = array.clone();
    HeapSort.sort(actual);
    Arrays.sort(expected);
    Assertions.assertArrayEquals(expected, actual);

    array = generateArray(2, -100, 100);
    expected = array.clone();
    actual = array.clone();
    HeapSort.sort(actual);
    Arrays.sort(expected);
    Assertions.assertArrayEquals(expected, actual);

    array = generateArray(3, -1000, 1000);
    expected = array.clone();
    actual = array.clone();
    HeapSort.sort(actual);
    Arrays.sort(expected);
    Assertions.assertArrayEquals(expected, actual);

    array = generateArray(3, -10000, 10000);
    expected = array.clone();
    actual = array.clone();
    HeapSort.sort(actual);
    Arrays.sort(expected);
    Assertions.assertArrayEquals(expected, actual);

    array = generateArray(randomInt(4, 10000), -10000, 10000);
    expected = array.clone();
    actual = array.clone();
    HeapSort.sort(actual);
    Arrays.sort(expected);
    Assertions.assertArrayEquals(expected, actual);

    array = generateArray(randomInt(4, 100000), -1000000, 1000000);
    expected = array.clone();
    actual = array.clone();
    HeapSort.sort(actual);
    Arrays.sort(expected);
    Assertions.assertArrayEquals(expected, actual);
  }

  @Test
  public void testSort_orderedArray() {
    int[] array;
    int[] expected;
    int[] actual;

    array = generateArray(2, -100, 100);
    Arrays.sort(array);
    expected = array.clone();
    actual = array.clone();
    HeapSort.sort(actual);
    Assertions.assertArrayEquals(expected, actual);

    array = generateArray(3, -1000, 1000);
    Arrays.sort(array);
    expected = array.clone();
    actual = array.clone();
    HeapSort.sort(actual);
    Assertions.assertArrayEquals(expected, actual);

    array = generateArray(3, -10000, 10000);
    Arrays.sort(array);
    expected = array.clone();
    actual = array.clone();
    HeapSort.sort(actual);
    Assertions.assertArrayEquals(expected, actual);

    array = generateArray(randomInt(4, 10000), -10000, 10000);
    Arrays.sort(array);
    expected = array.clone();
    actual = array.clone();
    HeapSort.sort(actual);
    Assertions.assertArrayEquals(expected, actual);

    array = generateArray(randomInt(4, 100000), -1000000, 1000000);
    Arrays.sort(array);
    expected = array.clone();
    actual = array.clone();
    HeapSort.sort(actual);
    Assertions.assertArrayEquals(expected, actual);
  }

  @Test
  public void testSort_oneValueArray() {
    int[] array;
    int[] expected;
    int[] actual;
    int size;

    array = new int[1];
    Arrays.fill(array, randomInt(-100, 100));
    expected = array.clone();
    actual = array.clone();
    HeapSort.sort(actual);
    Assertions.assertArrayEquals(expected, actual);

    array = new int[2];
    Arrays.fill(array, randomInt(-100, 100));
    expected = array.clone();
    actual = array.clone();
    HeapSort.sort(actual);
    Assertions.assertArrayEquals(expected, actual);

    size = randomInt(4, 10000);
    array = new int[size];
    Arrays.fill(array, randomInt(-100000, 100000));
    expected = array.clone();
    actual = array.clone();
    HeapSort.sort(actual);
    Assertions.assertArrayEquals(expected, actual);

    size = randomInt(4, 10000);
    array = new int[size];
    Arrays.fill(array, randomInt(-1000000, 1000000));
    expected = array.clone();
    actual = array.clone();
    HeapSort.sort(actual);
    Assertions.assertArrayEquals(expected, actual);
  }
}
