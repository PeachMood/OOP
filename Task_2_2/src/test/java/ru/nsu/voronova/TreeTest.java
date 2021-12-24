package ru.nsu.voronova;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class TreeTest {
    private Tree<String> tree;

    @BeforeEach
    public void initialization() {
        tree = new Tree<>();
    }

    @Test
    public void contains() {
        String[] array = new String[]{"a", "b", "c"};
        for (String element : array) {
            tree.add(element);
            assertTrue(tree.contains(element));
        }
        assertFalse(tree.contains("d"));
    }

    @Test
    public void size() {
        String[] array = new String[]{"a", "b", "c"};
        for (int i = 0; i < array.length; ++i) {
            tree.add(array[i]);
            assertEquals(i + 1, tree.size());
        }
        tree.remove("a");
        assertEquals(0, tree.size());
    }

    @Test
    public void isEmpty() {
        assertEquals(0, tree.size());
    }

    @Test
    public void add1_testReturnValue() {
        assertTrue(tree.add("a"));
        assertFalse(tree.add("a"));
    }

    @Test
    public void add1_testAddingSubtrees() {
        String[] array = new String[]{"a", "b", "c"};
        for (int i = 0; i < array.length; ++i) {
            tree.add(array[i]);
            assertEquals(i + 1, tree.size());
            assertTrue(tree.contains(array[i]));
        }
    }

    @Test
    public void add2_testReturnValue() {
        tree.add("a");
        tree.add("b");
        assertTrue(tree.add("c", "b"));
        assertFalse(tree.add("c", "b"));
        assertFalse(tree.add("d", "f"));
    }

    @Test
    public void add2_testAddingSubtrees() {
        tree.add("a");
        tree.add("b");
        String[] array = new String[]{"c", "d", "f", "g"};
        for (int i = 0; i < array.length; ++i) {
            tree.add(array[i], "b");
            assertEquals(i + 3, tree.size());
            assertTrue(tree.contains(array[i]));
        }
    }

    @Test
    public void remove_testReturnValue() {
        tree.add("a");
        assertTrue(tree.remove("a"));
        assertFalse(tree.remove("a"));
    }

    @Test
    public void remove_testSubtreesRemoval() {
        tree.add("a");
        tree.add("b");
        tree.add("c", "b");
        String[] array = new String[]{"d", "f", "g"};
        for (String element : array) {
            tree.add(element, "c");
        }
        tree.remove("c");
        for (String element : array) {
            assertFalse(tree.contains(element));
        }
        assertFalse(tree.contains("c"));
        assertEquals(2, tree.size());
    }

    @Test
    public void get_throwsNoSuchElementException() {
        assertThrows(NoSuchElementException.class, () -> tree.get("a"));
    }

    @Test
    public void get() {
        tree.add("a");
        tree.add("b");
        tree.add("c", "b");
        String[] array = new String[]{"d", "f", "g"};
        for (String element : array) {
            tree.add(element, "c");
        }

        Tree<String> subtree = tree.get("c");
        for (String element : array) {
            assertTrue(subtree.contains(element));
        }
        assertTrue(subtree.contains("c"));
        assertEquals(array.length + 1, subtree.size());
    }

    @Test
    public void iterator_throwsNoSuchElementException() {
        Iterator<String> iterator = tree.iterator();
        assertThrows(NoSuchElementException.class, iterator::next);
    }

    @Test
    public void iterator() {
        tree.add("a");
        tree.add("b");
        tree.add("c", "b");
        String[] array = new String[]{"d", "f", "g"};
        for (String element : array) {
            tree.add(element, "c");
        }

        String[] allElements = {"a", "b", "c", "d", "f", "g"};
        List<String> list = Arrays.asList(allElements);
        int count = 0;
        for(String element : tree) {
            count++;
            list.contains(element);
        }
        assertEquals(tree.size(), count);
    }

    @Test
    public void toArray1_throwsNullPointerException() {
        String[] array = null;
        assertThrows(NullPointerException.class, () -> tree.toArray(array));
    }

    @Test
    public void toArray1() {
        String[] array = new String[] {"a", "b", "c"};
        tree.addAll(Arrays.asList(array));

        String[] toArray = new String[tree.size()];
        tree.toArray(toArray);
        assertEquals(toArray.length, tree.size());
        for(String element : array) {
            assertTrue(tree.contains(element));
        }
    }

    @Test
    public void containsAll() {
        String[] array = new String[] {"a", "b", "c"};
        assertFalse(tree.containsAll(Arrays.asList(array)));
        tree.add("a");
        tree.add("b");
        tree.add("c", "b");
        assertTrue(tree.containsAll(Arrays.asList(array)));
    }

    @Test
    public void addAll() {
        String[] array = new String[] {"a", "b", "c"};
        tree.addAll(Arrays.asList(array));
        assertEquals(array.length, tree.size());
        assertTrue(tree.containsAll(Arrays.asList(array)));
        assertFalse(tree.addAll(Arrays.asList(array)));
    }

    @Test
    public void toArray0() {
        tree.add("a");
        tree.add("b");
        tree.add("c", "b");
        Object[] array = tree.toArray();
        assertEquals(array.length, tree.size());
        for(Object object : array) {
            assertTrue(tree.contains(object));
        }
    }

    @Test
    public void removeAll() {
        String[] array = new String[] {"a", "b", "c" , "d"};
        tree.addAll(Arrays.asList(array));

        List<String> list = Arrays.asList(array);
        Collections.reverse(list);
        tree.removeAll(list);
        assertEquals(0, tree.size());
        for (String element : array) {
            assertFalse(tree.contains(element));
        }
    }

    @Test
    public void retainAll() {
        Tree<String> tree = new Tree<>();
        tree.add("a");
        tree.add("b");
        tree.add("c");

        List<String> list = new ArrayList<>();
        list.add("g");
        list.add("c");
        list.add("a");

        tree.retainAll(list);
        assertFalse(tree.contains("b"));
    }

    @Test
    public void clear() {
        String[] array = new String[] {"a", "b", "c", "f"};
        tree.addAll(Arrays.asList(array));
        tree.clear();
        assertTrue(tree.isEmpty());
        for (String element : array) {
            assertFalse(tree.contains(element));
        }
    }
}