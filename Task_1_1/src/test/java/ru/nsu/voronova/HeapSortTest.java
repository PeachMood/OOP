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

  private void emptyArrayTest() {
    int[] actual = new int[] { };
    HeapSort.sort(actual);
    int[] expected = new int[] { };
    Assertions.assertArrayEquals(expected, actual);
  }

  private void unorderedArrayTest(int arraySize, int minimum, int maximum) {
    if (arraySize < 0 ) {
      System.out.println("Invalid array size " + arraySize);
      return;
    }

    int[] array;
    int[] expected;
    int[] actual;

    array = generateArray(arraySize, minimum, maximum);
    expected = Arrays.copyOf(array, array.length);
    actual = HeapSort.sort(array);
    Arrays.sort(expected);
    HeapSort.sort(actual);
    Assertions.assertArrayEquals(expected, actual);
  }

  private void orderedArrayTest(int arraySize, int minimum, int maximum) {
    if (arraySize < 0 ) {
      System.out.println("Invalid array size " + arraySize);
      return;
    }

    int[] array;
    int[] expected;
    int[] actual;

    array = new int[arraySize];
    Arrays.fill(array, randomInt(minimum, maximum));
    expected = Arrays.copyOf(array, array.length);
    actual = HeapSort.sort(array);
    HeapSort.sort(actual);
    Assertions.assertArrayEquals(expected, actual);
  }

  private void oneValueArrayTest(int arraySize, int minimum, int maximum) {
    if (arraySize < 0 ) {
      System.out.println("Invalid array size " + arraySize);
      return;
    }

    int[] array;
    int[] expected;
    int[] actual;

    array = generateArray(arraySize, minimum, maximum);
    Arrays.sort(array);
    expected = Arrays.copyOf(array, array.length);
    actual = HeapSort.sort(array);
    Assertions.assertArrayEquals(expected, actual);
  }

  @Test
  public void testSort() {

    int minimum;
    int maximum;

    // sorting an empty array
    emptyArrayTest();

    // sorting an array of size 2
    minimum = -100;
    maximum = 100;
    unorderedArrayTest(2, minimum, maximum);
    orderedArrayTest(2, minimum, maximum);
    oneValueArrayTest(2, minimum, maximum);

    minimum = -1000;
    maximum = 1000;
    unorderedArrayTest(2, minimum, maximum);
    orderedArrayTest(2, minimum, maximum);
    oneValueArrayTest(2, minimum, maximum);


    // sorting an array of size 3
    unorderedArrayTest(3, minimum, maximum);
    orderedArrayTest(3, minimum, maximum);
    oneValueArrayTest(3, minimum, maximum);

    minimum = -10000;
    maximum = 10000;
    unorderedArrayTest(3, minimum, maximum);
    orderedArrayTest(3, minimum, maximum);
    oneValueArrayTest(3, minimum, maximum);


    // sorting array of a random size
    unorderedArrayTest(randomInt(4, 10000), minimum, maximum);
    orderedArrayTest(randomInt(4, 10000), minimum, maximum);
    oneValueArrayTest(randomInt(4, 10000), minimum, maximum);

    minimum = -100000;
    maximum = 100000;
    unorderedArrayTest(randomInt(4, 100000), minimum, maximum);
    orderedArrayTest(randomInt(4, 100000), minimum, maximum);
    oneValueArrayTest(randomInt(4, 100000), minimum, maximum);

    minimum = -1000000;
    maximum = 1000000;
    unorderedArrayTest(randomInt(4, 100000), minimum, maximum);
    orderedArrayTest(randomInt(4, 100000), minimum, maximum);
    oneValueArrayTest(randomInt(4, 100000), minimum, maximum);

    unorderedArrayTest(randomInt(4, 100000), minimum, maximum);
    orderedArrayTest(randomInt(4, 100000), minimum, maximum);
    oneValueArrayTest(randomInt(4, 100000), minimum, maximum);
  }
}
