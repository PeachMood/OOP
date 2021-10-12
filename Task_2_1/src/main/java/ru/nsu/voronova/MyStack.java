package ru.nsu.voronova;

import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.EmptyStackException;

public class MyStack<E> implements Iterable<E> {

  private E[] stack;
  private int count;
  private int capacity;
  final private static int DEFAULT_CAPACITY = 64;

  @SuppressWarnings("unchecked")
  public MyStack(int capacity) {
    this.count = 0;
    this.capacity = capacity;
    this.stack = (E[]) new Object[capacity];
  }

  public MyStack() {
    this(DEFAULT_CAPACITY);
  }

  private E[] grow(int newCapacity) {
    capacity = newCapacity;
    return Arrays.copyOf(stack, newCapacity);
  }

  public void push(E element) {
    if (element == null) {
      throw new IllegalArgumentException();
    }
    if (count == capacity) {
      final int newCapacity = 2 * capacity;
      stack = grow(newCapacity);
    }
    stack[count] = element;
    count++;
  }

  public E pop() {
    if (count == 0) {
      throw new EmptyStackException();
    }
    E value = stack[--count];
    stack[count] = null;
    return value;
  }

  @SuppressWarnings("unchecked")
  private E[] stackIntoArray(MyStack<E> stack) {
    final int amount = stack.count();
    E[] array = (E[]) new Object[amount];
    for (int i = amount - 1; i >= 0; --i) {
      array[i] = stack.pop();
    }
    return array;
  }

  public void pushStack(MyStack<E> src) {
    final int amount = src.count();
    if (amount == 0) {
      return;
    }
    E[] array = stackIntoArray(src);
    if (count + amount >= capacity) {
      final int newCapacity = 2 * (count + amount);
      stack = grow(newCapacity);
    }
    System.arraycopy(array, 0, stack, count, amount);
    count += amount;
  }

  public MyStack<E> popStack(int amount) {
    if (amount < 0) {
      throw new IllegalArgumentException();
    }
    if (amount > count) {
      throw new EmptyStackException();
    }
    MyStack<E> result = new MyStack<>();
    for (int i = count - amount; i < count; ++i) {
      result.push(this.stack[i]);
      this.stack[i] = null;
    }
    count -= amount;
    return result;
  }

  public int count() {
    return count;
  }

  @Override
  public Iterator<E> iterator() {
    return new Iterator<>() {

      private int index = 0;

      @Override
      public boolean hasNext() {
        return index != count;
      }

      @Override
      public E next() {
        if (!hasNext()) {
          throw new NoSuchElementException();
        }
        return stack[index++];
      }
    };
  }
}
