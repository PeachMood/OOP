package ru.nsu.voronova;

import java.util.*;

public class Tree<E> implements Collection<E> {
    private E root;
    private final List<Tree<E>> children;

    public Tree() {
        children = new ArrayList<>();
    }

    public Tree<E> get(E element) throws NoSuchElementException {
        Queue<Tree<E>> processing = new ArrayDeque<>();
        processing.offer(this);
        while (!processing.isEmpty()) {
            Tree<E> tree = processing.poll();
            for (Tree<E> child : tree.children) {
                if (element.equals(child.root)) {
                    return child;
                }
                processing.offer(child);
            }
        }
        throw new NoSuchElementException();
    }

    @Override
    public int size() {
        int size = 0;
        for (Tree<E> child : children) {
            size += child.size();
        }
        return this.root != null ? size + 1 : size;
    }

    @Override
    public boolean isEmpty() {
        return size() == 0;
    }

    @Override
    public boolean contains(Object object) {
        if (object.equals(root)) {
            return true;
        }

        for (Tree<E> child : children) {
            if (child.contains(object)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public Iterator<E> iterator() {
        return depthFirstSearchIterator();
    }

    public Iterator<E> depthFirstSearchIterator() {
        return new Iterator<>() {
            private final Set<Tree<E>> visited = new HashSet<>();
            private final Stack<Tree<E>> stack = new Stack<>();
            private final int size = size();
            private int count = 0;

            @Override
            public boolean hasNext() {
                return count < size;
            }

            @Override
            public E next() throws NoSuchElementException {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }

                Tree<E> next;
                if (stack.isEmpty()) {
                    next = Tree.this;
                    visited.add(next);
                } else {
                    next = stack.pop();
                }
                for (Tree<E> child : next.children) {
                    if (!this.visited.contains(child)) {
                        this.stack.push(child);
                        this.visited.add(child);
                    }
                }
                count++;
                return next.root;
            }
        };
    }

    public Iterator<E> breadthFirstSearchIterator() {
        return new Iterator<>() {
            private final Set<Tree<E>> visited = new HashSet<>();
            private final Queue<Tree<E>> queue = new ArrayDeque<>();
            private final int size = size();
            private int count = 0;

            @Override
            public boolean hasNext() {
                return count < size;
            }

            @Override
            public E next() throws NoSuchElementException, ConcurrentModificationException {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }

                Tree<E> next;
                if (queue.isEmpty()) {
                    next = Tree.this;
                    visited.add(next);
                } else {
                    next = queue.poll();
                }
                for (Tree<E> child : next.children) {
                    if (!this.visited.contains(child)) {
                        this.queue.offer(child);
                        this.visited.add(child);
                    }
                }
                count++;
                return next.root;
            }
        };
    }

    @Override
    public Object[] toArray() {
        int index = 0;
        Object[] array = new Object[this.size()];
        for (E element : this) {
            array[index++] = element;
        }
        return array;
    }

    @Override
    @SuppressWarnings("unchecked")
    public <T> T[] toArray(T[] array) throws NullPointerException, ClassCastException {
        if (array == null) {
            throw new NullPointerException();
        }

        int size = size();
        if (array.length < size) {
            array = (T[]) new Object[this.size()];
        }

        int index = 0;
        for (E element : this) {
            array[index++] = (T) element;
        }
        return array;
    }

    @Override
    public boolean add(E element) {
        if (contains(element)) {
            return false;
        }

        if (isEmpty()) {
            root = element;
            return true;
        }

        Tree<E> subtree = new Tree<>();
        subtree.add(element);
        children.add(subtree);
        return true;
    }

    public boolean add(E element, E parent) {
        if (contains(element)) {
            return false;
        }

        Stack<Tree<E>> processing = new Stack<>();
        processing.push(this);
        while (!processing.isEmpty()) {
            Tree<E> tree = processing.pop();
            if (parent.equals(tree.root)) {
                Tree<E> subtree = new Tree<>();
                subtree.add(element);
                tree.children.add(subtree);
                return true;
            }

            for (Tree<E> child : tree.children) {
                processing.push(child);
            }
        }
        return false;
    }

    @Override
    public boolean remove(Object object) {
        if (object.equals(root)) {
            clear();
            return true;
        }

        Queue<Tree<E>> processing = new ArrayDeque<>();
        processing.offer(this);
        while (!processing.isEmpty()) {
            Tree<E> tree = processing.poll();
            for (Tree<E> child : tree.children) {
                if (object.equals(child.root)) {
                    tree.children.remove(child);
                    return true;
                }
                processing.offer(child);
            }
        }
        return false;
    }

    @Override
    public boolean containsAll(Collection<?> collection) {
        for (Object object : collection) {
            if (!contains(object)) {
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean addAll(Collection<? extends E> collection) {
        for (E element : collection) {
            if (!add(element)) {
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean removeAll(Collection<?> collection) {
        for (Object element : collection) {
            if (!remove(element)) {
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean retainAll(Collection<?> collection) {
        int retained = size();
        Queue<Tree<E>> processing = new ArrayDeque<>();
        processing.offer(this);
        while (!processing.isEmpty()) {
            Tree<E> tree = processing.poll();
            for (Tree<E> child : tree.children) {
                if (!collection.contains(child.root)) {
                    retained--;
                    tree.children.remove(child);
                }
                processing.offer(child);
            }
        }
        return retained != size();
    }

    @Override
    public void clear() {
        root = null;
        children.clear();
    }
}
