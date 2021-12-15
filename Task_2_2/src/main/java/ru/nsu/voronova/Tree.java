package ru.nsu.voronova;

import java.util.*;

public class Tree<E> implements Collection<E> {
    private int size;
    private Node<E> root;

    public Tree() {
        size = 0;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    private boolean containsElement(Node<E> root, E element) {
        Stack<Node<E>> visiting = new Stack<>();
        visiting.push(root);
        while (!visiting.isEmpty()) {
            Node<E> node = visiting.pop();
            if (node.getValue().equals(element)) {
                return true;
            }
            for (Node<E> child : node) {
                visiting.push(child);
            }
        }
        return false;
    }


    @Override
    @SuppressWarnings("unchecked")
    public boolean contains(Object object) {
        return containsElement(root, (E) object);
    }

    @Override
    public Object[] toArray() {
        return new Object[0];
    }

    @Override
    public <T> T[] toArray(T[] a) {
        return null;
    }

    private boolean addElement(Node<E> root, E element) {
        if (root.getChildrenNumber() == 0) {
            root.addChild(new Node<>(element, root));
            size++;
            return true;
        }
        for (Node<E> child : root) {
            if (addElement(child, element)) {
                return true;
            }
        }
        return false;
    }

    private boolean addElement(Node<E> root, E element, E parent) {
        if (root == null) {
            return false;
        }
        if (root.getValue().equals(parent)) {
            root.addChild(new Node<>(element, root));
            return true;
        }
        for (Node<E> child : root) {
            if (addElement(child, element, parent)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean add(E element) {
        if (size == 0) {
            root = new Node<>(element, root);
            size++;
            return true;
        }
        return addElement(root, element);
    }

    public boolean add(E element, E parent) {
        return addElement(root, element, parent);
    }

    private boolean removeElement(Node<E> root, E element) {
        if (root == null) {
            return false;
        }
        Node<E> childToRemove = null;
        for (Node<E> child : root) {
            if (child.getValue().equals(element)) {
                childToRemove = child;
                break;
            } else {
                if (removeElement(child, element)) {
                    return true;
                }
            }
        }
        if (childToRemove == null) {
            return false;
        }
        root.removeChild(childToRemove);
        return true;
    }

    @Override
    @SuppressWarnings("unchecked")
    public boolean remove(Object element) {
        return removeElement(root, (E) element);
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        return false;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        return false;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        return false;
    }

    @Override
    public boolean addAll(Collection<? extends E> c) {
        return false;
    }

    @Override
    public void clear() {
    }

    @Override
    public Iterator<E> iterator() {
        return null;
    }

    public static void main(String[] args) {
        Tree<String> tree = new Tree<>();
        tree.add("Hello");
        tree.add("World");
        tree.add("I");
        tree.add("love");
        tree.add("you!");
        System.out.println(tree.contains("Undefined"));
    }
}