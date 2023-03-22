package guchi.the.hasky.datastructures.list;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @author GuchiTheHasky
 * @since 2023
 */

public abstract class AbstractListTest {

    List<String> list;

    @BeforeEach
    void init() {
        list = getList();
    }

    abstract List<String> getList();

    @Test
    @DisplayName("Test, add some elements in list and check size.")
    public void addElementsAndCheckSizeOfList() {
        list.add("Gimli");
        list.add("Legolas");
        list.add("Gandalf");
        assertEquals(3, list.size());

        list.add("Aragorn");
        list.add("Frodo");
        assertEquals(5, list.size());
    }

    @Test
    @DisplayName("Test, add an element under the first index, to the array with values and check size.")
    public void addElementInFirstIndexOfListAndCheckSize() {
        list.add("W");
        list.add("O");
        list.add("W");

        list.add("S", 0);

        assertEquals("S", list.get(0));
        assertEquals(4, list.size());
    }


    @Test
    @DisplayName("Test, add an element in the middle of list and check size.")
    public void addElementByIndexInTheMiddleOfListAndCheckSize() {
        list.add("Gimli");
        list.add("Legolas");
        list.add("Gandalf");
        list.add("Aragorn");
        list.add("Frodo", 1);

        assertEquals("Frodo", list.get(1));
        assertEquals(5, list.size());
    }

    @Test
    @DisplayName("Test, add element in list with out of bounds index.")
    public void addElementOutOfBoundsInListAndCheckSize() {
        list.add("Scooby");
        list.add("dooby");
        list.add("doo");

        Throwable thrown = assertThrows(IndexOutOfBoundsException.class, () -> {
            list.add("Scooby doo", 4);
        });

        assertNotNull(thrown.getMessage());
        assertEquals(list.size(), 3);
    }


    @Test
    @DisplayName("Test, add element in list with less than zero index.")
    public void addElementWithIndexLessThanZeroAndCheckSize() {
        list.add("Scooby");
        list.add("dooby");
        list.add("doo");

        Throwable thrown = assertThrows(IndexOutOfBoundsException.class, () -> {
            list.add("Scooby doo", -1);
        });

        assertNotNull(thrown.getMessage());
        assertEquals(list.size(), 3);
    }

    @Test
    @DisplayName("Test, add element in the end of list.")
    public void addElementInTheEndOfListAndCheckSize() {
        list.add("Scooby");
        list.add("dooby");
        list.add("doo", list.size());

        assertEquals("doo", list.get(2));
        assertEquals(3, list.size());
    }

    @Test
    @DisplayName("Test, remove first element from list and check size.")
    public void removeFirstElementFromListAndCheckSize() {
        list.add("Severus");
        list.add("Albus");
        list.add("Percival");
        list.add("Wulfric");
        list.add("Brian");
        list.add("Dumbledore");

        list.remove(0);

        assertEquals("Albus", list.get(0));
        assertEquals(5, list.size());
    }

    @Test
    @DisplayName("Test, remove last element from list and check size.")
    public void removeLastElementFromListAndCheckSize() {
        list.add("Albus");
        list.add("Percival");
        list.add("Wulfric");
        list.add("Brian");
        list.add("Dumbledore");
        list.add("Severus");

        list.remove(5);

        assertEquals("Dumbledore", list.get(4));
        assertEquals(5, list.size());
    }

    @Test
    @DisplayName("Test, remove middle element from list.")
    public void removeMiddleElementFromListAndCheckSize() {
        list.add("Albus");
        list.add("Percival");
        list.add("Severus");
        list.add("Wulfric");
        list.add("Brian");
        list.add("Dumbledore");
        list.remove(2);

        assertEquals("Wulfric", list.get(2));
        assertEquals(5, list.size());
    }

    @Test
    @DisplayName("Test, remove from out of bounds index.")
    public void removeElementFromListWithOutOfBoundsIndexAndCheckSize() {
        list.add("Albus");
        list.add("Percival");
        list.add("Severus");
        list.add("Wulfric");
        list.add("Brian");
        list.add("Dumbledore");

        Throwable thrown = assertThrows(IndexOutOfBoundsException.class, () -> {
            list.remove(6);
        });

        assertNotNull(thrown.getMessage());
        assertEquals(6, list.size());
    }

    @Test
    @DisplayName("Test, remove element with index less than zero.")
    public void removeElementFromListWithIndexLessThanZeroAndCheckSize() {
        list.add("Albus");
        list.add("Percival");
        list.add("Severus");
        list.add("Wulfric");
        list.add("Brian");
        list.add("Dumbledore");

        Throwable thrown = assertThrows(IndexOutOfBoundsException.class, () -> {
            list.remove(-1);
        });

        assertNotNull(thrown.getMessage());
        assertEquals(6, list.size());
    }

    @Test
    @DisplayName("Test, get first element from list.")
    public void getFirstElementFromListAndCheckSize() {
        list.add("Albus");
        list.add("Percival");
        list.add("Wulfric");
        list.add("Brian");
        list.add("Dumbledore");

        assertEquals("Albus", list.get(0));
        assertEquals(5, list.size());
    }

    @DisplayName("Test, get last element from list.")
    @Test
    public void getLastElementFromListAndCheckSize() {
        list.add("Albus");
        list.add("Percival");
        list.add("Wulfric");
        list.add("Brian");
        list.add("Dumbledore");

        assertEquals("Dumbledore", list.get(4));
        assertEquals(5, list.size());
    }

    @DisplayName("Test, get middle element from list.")
    @Test
    public void getMiddleElementFromListAndCheckSize() {
        list.add("Albus");
        list.add("Percival");
        list.add("Wulfric");
        list.add("Brian");
        list.add("Dumbledore");

        assertEquals("Wulfric", list.get(2));
        assertEquals(5, list.size());
    }

    @Test
    @DisplayName("Test, try to get element with out of bounds index.")
    public void tryToGetElementFromListWithOutOfBoundsIndexAndCheckSize() {
        list.add("Albus");
        list.add("Percival");
        list.add("Wulfric");
        list.add("Brian");
        list.add("Dumbledore");

        Throwable thrown = assertThrows(IndexOutOfBoundsException.class, () -> {
            list.get(5);
        });

        assertNotNull(thrown.getMessage());
        assertEquals(5, list.size());
    }

    @Test
    @DisplayName("Test, try to get element with index less than zero.")
    public void tryToGetElementFromListWithIndexLessThanZeroAndCheckSize() {
        list.add("Albus");
        list.add("Percival");
        list.add("Wulfric");
        list.add("Brian");
        list.add("Dumbledore");

        Throwable thrown = assertThrows(IndexOutOfBoundsException.class, () -> {
            list.get(-1);
        });

        assertNotNull(thrown.getMessage());
        assertEquals(5, list.size());
    }

    @Test
    @DisplayName("Test, set first element in list & get previous element.")
    public void setFirstElementInListAndCheckSize() {
        list.add("Harry");
        list.add("Percival");
        list.add("Wulfric");
        list.add("Brian");
        list.add("Dumbledore");

        assertEquals("Harry", list.set("Albus", 0));

        assertEquals("Albus", list.get(0));
        assertEquals(5, list.size());
    }

    @Test
    @DisplayName("Test, set last element in list & get previous element.")
    public void setLastElementInListAndCheckSize() {
        list.add("Albus");
        list.add("Percival");
        list.add("Wulfric");
        list.add("Brian");
        list.add("Potter");

        assertEquals("Potter", list.set("Dumbledore", 4));

        assertEquals("Dumbledore", list.get(4));
        assertEquals(5, list.size());
    }

    @DisplayName("Test, set middle element in list & get previous element.")
    @Test
    public void setMiddleElementInListAndCheckSize() {
        list.add("Albus");
        list.add("Percival");
        list.add("Severus");
        list.add("Brian");
        list.add("Dumbledore");

        assertEquals("Severus", list.set("Wulfric", 2));

        assertEquals("Wulfric", list.get(2));
        assertEquals(5, list.size());
    }

    @DisplayName("Test, try to set element with index out of bounds.")
    @Test
    public void tryToSetElementFromListWithIndexOutOfBounds() {
        list.add("Albus");
        list.add("Percival");
        list.add("Wulfric");
        list.add("Brian");
        list.add("Dumbledore");

        Throwable thrown = assertThrows(IndexOutOfBoundsException.class, () -> {
            list.set("Potter", 5);
        });

        assertNotNull(thrown.getMessage());
        assertEquals(5, list.size());
    }

    @DisplayName("Test, try to set element with index less than zero.")
    @Test
    public void tryToSetElementFromListWithIndexLessThanZeroAndCheckSize() {
        list.add("Albus");
        list.add("Percival");
        list.add("Wulfric");
        list.add("Brian");
        list.add("Dumbledore");

        Throwable thrown = assertThrows(IndexOutOfBoundsException.class, () -> {
            list.set("Potter", -1);
        });

        assertNotNull(thrown.getMessage());
        assertEquals(5, list.size());
    }

    @Test
    @DisplayName("Test, delete all elements, size == 0.")
    public void clearAllElementsInListAndCheckSize() {
        list.add("Albus");
        list.add("Percival");
        list.add("Severus");
        list.add("Brian");
        list.add("Dumbledore");

        list.clear();
        assertEquals(0, list.size());
        Assertions.assertTrue(list.isEmpty());
    }

    @Test
    @DisplayName("Test, size after add and remove.")
    public void checkSizeAfterAddAndRemoveInList() {
        list.add("Albus");
        list.add("Percival");
        list.remove(1);

        list.add("Severus");
        list.add("Brian");
        list.add("Dumbledore");
        list.remove(2);

        assertEquals(3, list.size());
    }

    @Test
    @DisplayName("Test, is empty List true.")
    public void isEmptyListExpectTrue() {
        Assertions.assertTrue(list.isEmpty());
    }

    @Test
    @DisplayName("Test, is empty List false.")
    public void isEmptyListExpectFalse() {
        list.add("Albus");
        list.add("Percival");
        list.add("Severus");
        list.add("Brian");
        list.add("Dumbledore");
        Assertions.assertFalse(list.isEmpty());
    }

    @Test
    @DisplayName("Test, is List contains element true.")
    public void listContainsElementExpectTrue() {
        list.add("Albus");
        list.add("Percival");
        list.add("Severus");
        list.add("Brian");
        list.add("Dumbledore");

        assertTrue(list.contains("Percival"));
    }

    @Test
    @DisplayName("Test, is List contains element false.")
    public void listContainsElementExpectFalse() {
        list.add("Albus");
        list.add("Percival");
        list.add("Severus");
        list.add("Brian");
        list.add("Dumbledore");

        assertFalse(list.contains("Potter"));
    }

    @Test
    @DisplayName("Test, get index of first element in list.")
    public void checkIndexOfWorksCorrectly() {
        list.add("Albus");
        list.add("Percival");
        list.add("Percival");
        list.add("Severus");
        list.add("Brian");
        list.add("Dumbledore");

        assertEquals(0, list.indexOf("Albus"));
        assertEquals(1, list.indexOf("Percival"));
        assertEquals(5, list.indexOf("Dumbledore"));
    }

    @Test
    @DisplayName("Test, get last index of.")
    public void checkLastIndexOfWorksCorrectly() {
        list.add("Albus");
        list.add("Percival");
        list.add("Percival");
        list.add("Severus");
        list.add("Brian");
        list.add("Dumbledore");

        assertEquals(0, list.lastIndexOf("Albus"));
        assertEquals(2, list.lastIndexOf("Percival"));
        assertEquals(5, list.lastIndexOf("Dumbledore"));
    }

    @Test
    @DisplayName("Test, is toString() work correctly.")
    public void turnListToStringAndPrintIt() {
        list.add("Scooby");
        list.add("dooby");
        list.add("doo");
        String result = list.toString();
        assertEquals("[Scooby, dooby, doo]", result);
    }

    @Test
    @DisplayName("Test: iterator has next element: false. ")
    public void checkOfIteratorHasNextElementFalse() {
        Iterator<String> iterator = list.iterator();
        assertFalse(iterator.hasNext());
    }

    @Test
    @DisplayName("Test: iterator method next(), works correctly: true.")
    public void testIteratorMethodNextElementWorkCorrectly() {
        list.add("Scooby");
        list.add("dooby");
        list.add("doo");

        Iterator<String> iterator = list.iterator();

        assertEquals("Scooby", iterator.next());
        assertEquals("dooby", iterator.next());
        assertEquals("doo", iterator.next());
    }

    @Test
    @DisplayName("Test, throw new NoSuchElementException in Iterator, method next().")
    public void testThrowNoSuchElementExceptionInIteratorMethodNext() {
        Iterator<String> iterator = list.iterator();
        Throwable thrown = assertThrows(NoSuchElementException.class, iterator::next);
        assertNotNull(thrown.getMessage());
    }

    @Test
    @DisplayName("Test: iterator method remove(), works correctly: true.")
    public void removeElementsFromListWithIterator() {
        list.add("Scooby");
        list.add("dooby");
        list.add("doo");

        Iterator<String> iterator = list.iterator();

        iterator.next();
        iterator.remove();
        assertEquals("dooby", list.get(0));
        System.out.println(list.toString());

        iterator.next();
        iterator.remove();
        assertEquals("doo", list.get(0));
        System.out.println(list.toString());

        iterator.next();
        iterator.remove();
        assertTrue(list.isEmpty());
    }

    @Test
    @DisplayName("Test, throw new IllegalStateException in Iterator method: remove().")
    public void testThrowIllegalStateExceptionInIteratorMethodRemove() {
        Iterator<String> iterator = list.iterator();
        Throwable thrown = assertThrows(IllegalStateException.class, iterator::remove);
        assertNotNull(thrown.getMessage());
    }

    @Test
    public void testListIteratorWorksCorrectly() {
        list.add("Scooby");
        list.add("dooby");
        list.add("doo");

        Iterator<String> iterator = list.iterator();
        assertTrue(iterator.hasNext());
        assertEquals("Scooby", iterator.next());
        assertTrue(iterator.hasNext());
        assertEquals("dooby", iterator.next());
        assertTrue(iterator.hasNext());
        assertEquals("doo", iterator.next());
    }
}
