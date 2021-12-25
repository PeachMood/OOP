package ru.nsu.voronova;

import java.util.*;

/**
 * A general tree implementation. Provides methods for storing data and its modification.
 * Implements {@link Collection} interface.
 *
 * @param <E> - the type of elements in the tree.
 */
public class Tree<E> implements Collection<E> {
    private E root;
    private final List<Tree<E>> children;

    /**
     * Creates a tree of zero size and without children.
     */
    public Tree() {
        children = new ArrayList<>();
    }

    /**
     * Gets a subtree with specified value in the root for the tree.
     *
     * @param element - the value contained in the root of the searched subtree.
     * @return a subtree if it is contained in the original tree.
     * @throws NoSuchElementException if the original tree does not contain a subtree with specified value.
     */
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

    /**
     * Returns the number of children in the tree including root.
     *
     * @return the size of the tree.
     */
    @Override
    public int size() {
        int size = 0;
        for (Tree<E> child : children) {
            size += child.size();
        }
        return this.root != null ? size + 1 : size;
    }

    /**
     * Determines if the tree is empty.
     *
     * @return true if the tree does not have any children and a root, false - otherwise.
     */
    @Override
    public boolean isEmpty() {
        return size() == 0;
    }

    /**
     * Determines if the tree contains an element with specified value.
     *
     * @param object - The value of the searched element.
     * @return true if the tree contains the searched element, false - otherwise.
     */
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

    /**
     * Provides a class for iterating through the elements of the tree.
     *
     * @return a class implementing the {@link Iterator} interface.
     */
    @Override
    public Iterator<E> iterator() {
        return depthFirstSearchIterator();
    }

    /**
     * Provides a class for iterating through tree elements based on a depth-first search algorithm.
     *
     * @return a class implementing the {@link Iterator} interface.
     */
    public Iterator<E> depthFirstSearchIterator() {
        return new Iterator<>() {
            private final Set<Tree<E>> visited = new HashSet<>();
            private final Stack<Tree<E>> stack = new Stack<>();
            private final int size = size();
            private int count = 0;

            /**
             * Returns true if next would return an element rather than throwing an exception.
             *
             * @return true if the iteration has more elements.
             */
            @Override
            public boolean hasNext() {
                return count < size;
            }

            /**
             * Returns the next element in the iteration.
             *
             * @return the next element in the iteration.
             * @throws NoSuchElementException – if the iteration has no more elements.
             */
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

    /**
     * Provides a class for iterating through tree elements based on a breadth-first search algorithm.
     *
     * @return a class implementing the {@link Iterator} interface.
     */
    public Iterator<E> breadthFirstSearchIterator() {
        return new Iterator<>() {
            private final Set<Tree<E>> visited = new HashSet<>();
            private final Queue<Tree<E>> queue = new ArrayDeque<>();
            private final int size = size();
            private int count = 0;

            /**
             * Returns true if next would return an element rather than throwing an exception.
             *
             * @return true if the iteration has more elements.
             */
            @Override
            public boolean hasNext() {
                return count < size;
            }

            /**
             * Returns the next element in the iteration.
             *
             * @return the next element in the iteration.
             * @throws NoSuchElementException – if the iteration has no more elements.
             */
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

    /**
     * Returns an array containing all the elements of the tree.
     *
     * @return an array with all the elements from the tree.
     */
    @Override
    public Object[] toArray() {
        int index = 0;
        Object[] array = new Object[this.size()];
        for (E element : this) {
            array[index++] = element;
        }
        return array;
    }

    /**
     * Returns an array containing all the elements in the tree.
     * The runtime type of the returned array is that of the specified array.
     * If the tree fits in the specified array, it is returned therein.
     * Otherwise, a new array is allocated with the runtime type of the specified array and the size of the tree.
     *
     * @param array – the array into which the elements of the tree are to be stored.
     * @throws ClassCastException – if the runtime type of the specified array is not a supertype of the runtime type of every element in the tree.
     *                            NullPointerException – if the specified array is null.
     */
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

    /**
     * Adds a new element as the child of the root.
     *
     * @param element - the element should be added.
     * @return - true if the tree changed as a result of the call.
     */
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

    /**
     * Adds a new element as the child of the specified element.
     *
     * @param element - the element should be added.
     * @param parent  - the tree node to which the new element should be added.
     * @return - true if the tree changed as a result of the call.
     */
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

    /**
     * Removes subtree with specified element as a root from the tree, if it is present.
     *
     * @param object - root of subtree to be removed from the tree.
     * @return true if a subtree was removed as a result of this call, false - otherwise.
     */
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

    /**
     * Returns true if the tree contains all the elements in the specified collection.
     *
     * @param collection - collection to be checked for containment in the tree.
     * @return true if the tree contains all the elements in the specified collection.
     */
    @Override
    public boolean containsAll(Collection<?> collection) {
        for (Object object : collection) {
            if (!contains(object)) {
                return false;
            }
        }
        return true;
    }

    /**
     * Adds all the elements in the specified collection to the tree.
     *
     * @param collection - collection containing elements to be added to the tree.
     * @return true if the tree changed as a result of the call.
     */
    @Override
    public boolean addAll(Collection<? extends E> collection) {
        for (E element : collection) {
            if (!add(element)) {
                return false;
            }
        }
        return true;
    }

    /**
     * Removes all the subtrees, which contain as the root elements from the specified collection.
     *
     * @param collection - collection containing elements to be removed from this collection.
     * @return true if this collection changed as a result of the call
     */
    @Override
    public boolean removeAll(Collection<?> collection) {
        for (Object element : collection) {
            if (!remove(element)) {
                return false;
            }
        }
        return true;
    }

    /**
     * Retains only the elements in the tree that are contained in the specified collection.
     * Removes from this collection all of its subtrees, which contain elements, that are not contained in the specified collection, as the root.
     *
     * @param collection - collection containing elements to be retained in the tree.
     * @return true if this collection changed as a result of the call.
     */
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

    /**
     * Removes all the elements from the tree.
     * The collection will be empty after this method returns.
     */
    @Override
    public void clear() {
        root = null;
        children.clear();
    }
}
