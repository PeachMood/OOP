package ru.nsu.voronova;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.EmptyStackException;
import java.util.Iterator;

import static org.junit.jupiter.api.Assertions.*;

class MyStackTest {

  private MyStack<Integer> stack;

  private void pushElements(MyStack<Integer> stack, int count) {
    for (int i = 0; i < count; ++i) {
      stack.push(i);
    }
  }

  @BeforeEach
  public void initStack() {
    stack = new MyStack<>();
  }

  @Test
  void testPush_throwsException() {
    assertThrows(IllegalArgumentException.class, () -> stack.push(null));
  }

  @Test
  void testPush_numberOfElements() {
    int expectedCount = 70;
    pushElements(this.stack, expectedCount);
    int actualCount = stack.count();
    assertEquals(expectedCount, actualCount);
  }

  @Test
  void testPush_orderOfElements() {
    int count = 200;
    pushElements(this.stack, count);

    for (int i = count - 1; i >= 0; --i) {
      int expectedElement = i;
      int actualElement = stack.pop();
      assertEquals(expectedElement, actualElement);
    }
  }

  @Test
  void testPushStack_numberOfElements() {
    int expectedCount = 70;
    MyStack<Integer> temp = new MyStack<>();
    pushElements(temp, expectedCount);
    stack.pushStack(temp);
    int actualCount = stack.count();
    assertEquals(expectedCount, actualCount);
  }

  @Test
  void testPushStack_orderOfElements() {
    int count = 200;
    MyStack<Integer> temp = new MyStack<>();
    pushElements(temp, count);

    stack.pushStack(temp);

    for (int i = count - 1; i >= 0; --i) {
      int expectedElement = i;
      int actualElement = stack.pop();
      assertEquals(expectedElement, actualElement);
    }
  }

  @Test
  void testPop_throwsException() {
    assertThrows(EmptyStackException.class, stack::pop);
  }

  @Test
  void testPop_numberOfElements() {
    int count = 70;
    pushElements(this.stack, count);

    for (int i = count - 1; i >= 0; --i) {
      stack.pop();
      int expectedCount = i;
      int actualCount = stack.count();
      assertEquals(expectedCount, actualCount);
    }
  }

  @Test
  void testPop_orderOfElements() {
    int count = 200;
    pushElements(this.stack, count);

    for (int i = count - 1; i >= 0; --i) {
      int expectedElement = i;
      int actualElement = stack.pop();
      assertEquals(expectedElement, actualElement);
    }
  }

  @Test
  void testPopStack_throwsException() {
    assertThrows(EmptyStackException.class, () -> stack.popStack(1));
  }

  @Test
  void testPopStack_numberOfElements() {
    int count = 70;
    pushElements(this.stack, count);
    stack.popStack(count);
    int expectedCount = 0;
    int actualCount = stack.count();
    assertEquals(expectedCount, actualCount);
  }

  @Test
  void testPopStack_orderOfElements() {
    int count = 200;
    pushElements(this.stack, count);
    MyStack<Integer> result = stack.popStack(count);
    for (int i = count - 1; i >= 0; --i) {
      int expectedElement = i;
      int actualElement = result.pop();
      assertEquals(expectedElement, actualElement);
    }
  }

  @Test
  void testCount() {
    for (int i = 0; i < 70; ++i) {
      stack.push(i);
      int expectedCount = i + 1;
      int actualCount = stack.count();
      assertEquals(expectedCount, actualCount);
    }
  }

  @Test
  void testIterator_hasNext() {
    Iterator<Integer> iterator = stack.iterator();
    assertFalse(iterator.hasNext());

    stack.push(1);
    iterator = stack.iterator();
    assertTrue(iterator.hasNext());
  }

  @Test
  void testIterator_next() {
    int count = 200;
    pushElements(this.stack, count);
    Iterator<Integer> iterator = stack.iterator();
    for (int i = 0; i < count; ++i) {
      int expectedNext = i;
      int actualNext = iterator.next();
      assertEquals(expectedNext, actualNext);
    }
  }
}