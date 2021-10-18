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
  final private static int GROW_VALUE = 2;

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
      stack = grow(GROW_VALUE * capacity);
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

  public void pushStack(MyStack<E> source) {
    final int amount = source.count();
    if (amount == 0) {
      return;
    }
    if (count + amount >= capacity) {
      stack = grow(GROW_VALUE * (count + amount));
    }
    System.arraycopy(source.stack, 0, stack, count, amount);
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
