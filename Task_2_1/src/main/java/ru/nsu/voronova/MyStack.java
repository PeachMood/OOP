package ru.nsu.voronova;

import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.EmptyStackException;

public class MyStack<E> implements Iterable<E> {

  private E[] stack;
  private int count;
  private int capacity;

  @SuppressWarnings("unchecked")
  public MyStack(int capacity) {
    this.count = 0;
    this.capacity = capacity;
    this.stack = (E[]) new Object[capacity];
  }

  public MyStack() {
    this(64);
  }

  private E[] grow(int newCapacity) {
    capacity = newCapacity;
    return Arrays.copyOf(stack, capacity);
  }

  public void push(E element) {
    if (element == null) {
      throw new IllegalArgumentException();
    }
    if (count == capacity) {
      stack = grow(2 * capacity);
    }
    stack[count] = element;
    count++;
  }

  public E pop() throws EmptyStackException {
    if (count == 0) {
      throw new EmptyStackException();
    }
    E value = stack[--count];
    stack[count] = null;
    return value;
  }

  @SuppressWarnings("unchecked")
  public void pushStack(MyStack<E> stack) {
    int size = stack.count();
    if (size != 0) {
      E[] temp = (E[]) new Object[size];
      for (int i = size - 1; i >= 0; --i) {
        temp[i] = stack.pop();
      }
      for (int i = 0; i < size; ++i) {
        this.push(temp[i]);
        stack.push(temp[i]);
      }
    }
  }

  @SuppressWarnings("unchecked")
  public MyStack<E> popStack(int count) {
    E[] temp = (E[]) new Object[count];
    for (int i = count - 1; i >= 0; --i) {
      temp[i] = this.pop();
    }

    MyStack<E> result = new MyStack<>();
    for (int i = 0; i < count; ++i) {
      result.push(temp[i]);
    }
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
