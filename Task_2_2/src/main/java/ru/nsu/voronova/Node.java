package ru.nsu.voronova;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Node<E> implements Iterable<Node<E>> {
  private E value;
  private int childrenNumber;
  private Node<E> parent;
  private List<Node<E>> children;

  public Node(E value, Node<E> parent) {
    this.value = value;
    this.childrenNumber = 0;
    this.parent = parent;
    this.children = new ArrayList<>();
  }

  public E getValue() {
    return value;
  }

  public Node<E> getChild(int index) {
    return children.get(index);
  }

  public int getChildrenNumber() {
    return childrenNumber;
  }

  public Node<E> getParent() {
    return parent;
  }

  public void addChild(Node<E> child) {
    children.add(child);
    childrenNumber++;
  }

  public void addChild(int index, Node<E> child) {
    children.set(index, child);
    childrenNumber++;
  }

  public void removeChild(Node<E> element) {
    children.remove(element);
  }

  public void removeChild(int index) {
    children.remove(index);
  }

  public void setValue(E value) {
    this.value = value;
  }

  @Override
  public Iterator<Node<E>> iterator() {
    return children.iterator();
  }
}
