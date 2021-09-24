package ru.nsu.voronova;

/**
 * HeapSort is a class with the  method, which is used to
 * perform pyramid sorting of an array.
 *
 * @author Ann Voronova.
 */

public class HeapSort {

  private static void heap(int[] array, int size, int vertex) {
    int maximumValue = vertex;
    int leftChild = 2 * vertex + 1;
    int rightChild = 2 * vertex + 2;

    if (leftChild < size && array[leftChild] > array[maximumValue])
      maximumValue = leftChild;

    if (rightChild < size && array[rightChild] > array[maximumValue])
      maximumValue = rightChild;

    if (maximumValue != vertex) {
      int swap = array[vertex];
      array[vertex] = array[maximumValue];
      array[maximumValue] = swap;
      heap(array, size, maximumValue);
    }
  }

  /**
   * The main method, which can be applied for sorting numeric array.
   *
   * @param array - array to be sorted
   */
  public static void sort(int[] array) {
    int size = array.length;

    for (int i = size / 2 - 1; i >= 0; i--)
      heap(array, size, i);

    for (int i = size - 1; i > 0; i--) {
      int temp = array[0];
      array[0] = array[i];
      array[i] = temp;
      heap(array, i, 0);
    }
  }
}

