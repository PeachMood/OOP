package ru.nsu.voronova;
import java.util.*;

/**
 * HeapSort is a class with the {@link #sort(int[])} method, which is used to
 * perform pyramid sorting of an array.
 * @author Ann Voronova.
 * */

public class HeapSort {

  /**
   * Fields that specify the array for storing data and the size of the heap.
   */
  private int[] heap;
  private int size;

  /**
   * Function for swapping vertices with given indices in the heap.
   * @param vertex1 - index of the first vertex.
   * @param vertex2 - index of the second vertex.
   */
  private void swap(int vertex1, int vertex2) {
    try {
      if (vertex1 != vertex2) {
        int temp = heap[vertex1];
        heap[vertex1] = heap[vertex2];
        heap[vertex2] = temp;
      }
    } catch (NullPointerException | ArrayIndexOutOfBoundsException exception) {
      System.out.println("swap: " + exception.getMessage());
    }
  }

  /**
   * Function for recursively raising a vertex in the heap until the order is restored.
   * @param vertex - index of the vertex.
   */
  private void siftUp(int vertex) {
    try {
      if (vertex != 0) {
        int parent = (vertex - 1) / 2;
        if (heap[parent] > heap[vertex]) {
          swap(vertex, parent);
          siftUp(parent);
        }
      }
    } catch (NullPointerException | ArrayIndexOutOfBoundsException exception) {
      System.out.println("siftUp: " + exception.getMessage());
    }
  }

  /**
   * Function for recursively dropping a vertex on the heap until order is restored.
   * @param vertex - index of the vertex.
   */
  private void siftDown(int vertex) {
    try {
      int leftChild = 2 * vertex + 1;
      int rightChild = 2 * vertex + 2;
      int minimumValue = vertex;
      if (leftChild < size && heap[minimumValue] > heap[leftChild]) {
        minimumValue = leftChild;
      }
      if (rightChild < size && heap[minimumValue] > heap[rightChild]) {
        minimumValue = rightChild;
      }
      if (minimumValue != vertex) {
        swap(minimumValue, vertex);
        siftDown(minimumValue);
      }
    } catch (NullPointerException | ArrayIndexOutOfBoundsException exception) {
      System.out.println("siftDown: " + exception.getMessage());
    }
  }

  /**
   * Function for adding new vertex. Restores heap order.
   * @param newValue - value of the new vertex, which should be added to the heap.
   */
  private void addVertex(int newValue) {
    try {
      heap = Arrays.copyOf(heap, size + 1);
      heap[size] = newValue;
      siftUp(size);
      size++;
    } catch (NullPointerException | ArrayIndexOutOfBoundsException exception) {
      System.out.println("addVertex " + exception.getMessage());
    }
  }

  /**
   * Constructor of a minimum heap.
   * @param array - array based on which a heap is created
   * */
  private HeapSort(int[] array) {
    try {
      heap = new int[]{};
      size = 0;
      for (int element : array) {
        addVertex(element);
      }
    } catch (NullPointerException | ArrayIndexOutOfBoundsException exception) {
      System.out.println("HeapSort constructor " + exception.getMessage());
    }
  }

  /**
   * Function for extracting vertex with the minimum value from the heap
   * @return vertex with the minimum value in the heap.
   * */
  private int extractMinimum() {
    try {
      int minimumValue = heap[0];
      size--;
      swap(0, size);
      siftDown(0);
      return minimumValue;
    } catch (NullPointerException exception) {
      System.out.println("extractMinimum: " + exception.getMessage());
    }
    return 0;
  }

  /**
   * The main method, which can be applied for sorting numeric array.
   * @param array - array to be sorted
   * @return sorted array
   */
  public static int[] sort(int[] array) {
    try {
      HeapSort newHeap = new HeapSort(array);
      int[] sortedArray = new int[newHeap.size];
      for (int i = 0; i < sortedArray.length; ++i) {
        sortedArray[i] = newHeap.extractMinimum();
      }
      return sortedArray;
    } catch (NullPointerException | ArrayIndexOutOfBoundsException exception) {
      System.out.println("sort: " + exception.getMessage());
    }
    return null;
  }
}

