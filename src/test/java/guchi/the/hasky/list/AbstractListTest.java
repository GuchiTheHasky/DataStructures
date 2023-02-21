package guchi.the.hasky.list;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public abstract class AbstractListTest {

    List<String> list;

    @BeforeEach
    void init() {
        list = getList();
    }

    abstract List<String> getList();

    @DisplayName("Test, add some elements in list and check size.")
    @Test
    public void addElementsAndCheckSizeOfList() {
        list.add("Gimli");
        assertEquals(1, list.size());

        list.add("Legolas");
        assertEquals(2, list.size());

        list.add("Gandalf");
        assertEquals(3, list.size());

        list.add("Aragorn");
        assertEquals(4, list.size());

        list.add("Frodo");
        assertEquals(5, list.size());
    }

    @DisplayName("Test, add an element under the first index, to the array with values and check size.")
    @Test
    public void addElementInFirstIndexOfListAndCheckSize() {
        list.add("W");
        list.add("O");
        list.add("W");

        list.add("S", 0);

        assertEquals("S", list.get(0));
        assertEquals(list.size(), 4);
    }

    @DisplayName("Test, add an element in the middle of list and check size.")
    @Test
    public void addElementByIndexInTheMiddleOfListAndCheckSize() {
        list.add("Gimli");
        list.add("Legolas");
        list.add("Gandalf");
        list.add("Aragorn");
        list.add("Frodo", 1);

        assertEquals("Frodo", list.get(1));
        assertEquals(5, list.size());
    }

    @DisplayName("Test, add element in list with out of bounds index.")
    @Test
    public void addElementOutOfBoundsInListAndCheckSize() {
        list.add("Scooby");
        list.add("dooby");
        list.add("doo");

        Throwable thrown = assertThrows(IndexOutOfBoundsException.class, () -> {
            list.add("Scooby doo", 5);
        });

        assertNotNull(thrown.getMessage());
        assertEquals(list.size(), 3);
    }

    @DisplayName("Test, add element in list with less than zero index.")
    @Test
    public void addElementInIndexLessThanZeroAndCheckSize() {
        list.add("Scooby");
        list.add("dooby");
        list.add("doo");

        Throwable thrown = assertThrows(IndexOutOfBoundsException.class, () -> {
            list.add("Scooby doo", -5);
        });

        assertNotNull(thrown.getMessage());
        assertEquals(list.size(), 3);
    }

    @DisplayName("Test, add element in the end of list.")
    @Test
    public void addElementInTheEndOfListAndCheckSize(){
        list.add("Scooby");
        list.add("dooby");
        list.add("doo", list.size());

        assertEquals("doo", list.get(2));
        assertEquals(3, list.size());
    }

    @DisplayName("Test, remove first element from list and check size.")
    @Test
    public void removeFirstElementFromListAndCheckSize() {
        list.add("Severus");
        list.add("Albus");
        list.add("Percival");
        list.add("Wulfric");
        list.add("Brian");
        list.add("Dumbledore");

        list.remove(0);

        assertEquals("Albus", list.get(0));
        assertEquals(list.size(), 5);
    }

    @DisplayName("Test, remove last element from list and check size.")
    @Test
    public void removeLastElementFromListAndCheckSize() {
        list.add("Albus");
        list.add("Percival");
        list.add("Wulfric");
        list.add("Brian");
        list.add("Dumbledore");
        list.add("Severus");

        list.remove(5);

        assertEquals("Dumbledore", list.get(4));
        assertEquals(list.size(), 5);
    }

    @DisplayName("Test, remove last element from list.")
    @Test
    public void removeMiddleElementFromListAndCheckSize() {
        list.add("Albus");
        list.add("Percival");
        list.add("Severus");
        list.add("Wulfric");
        list.add("Brian");
        list.add("Dumbledore");
        list.remove(2);

        assertEquals("Wulfric", list.get(2));
        assertEquals(list.size(), 5);
    }

    @DisplayName("Test, remove from out of bounds index.")
    @Test
    public void removeElementFromListWithOutOfBoundsIndexAndCheckSize() {
        list.add("Albus");
        list.add("Percival");
        list.add("Severus");
        list.add("Wulfric");
        list.add("Brian");
        list.add("Dumbledore");

        Throwable thrown = assertThrows(IndexOutOfBoundsException.class, () -> {
            list.remove(11);
        });

        assertNotNull(thrown.getMessage());
        assertEquals(list.size(), 6);
    }

    @DisplayName("Test, remove element with index less than zero.")
    @Test
    public void removeElementFromListWithIndexLessThanZeroAndCheckSize() {
        list.add("Albus");
        list.add("Percival");
        list.add("Severus");
        list.add("Wulfric");
        list.add("Brian");
        list.add("Dumbledore");

        Throwable thrown = assertThrows(IndexOutOfBoundsException.class, () -> {
            list.remove(-11);
        });

        assertNotNull(thrown.getMessage());
        assertEquals(6, list.size());
    }

    @DisplayName("Test, get first element from list.")
    @Test
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
        assertEquals(list.size(), 5);

    }

    @DisplayName("Test, try to get element with out of bounds index.")
    @Test
    public void tryToGetElementFromListWithOutOfBoundsIndexAndCheckSize() {
        list.add("Albus");
        list.add("Percival");
        list.add("Wulfric");
        list.add("Brian");
        list.add("Dumbledore");

        Throwable thrown = assertThrows(IndexOutOfBoundsException.class, () -> {
            list.get(99);
        });

        assertNotNull(thrown.getMessage());
        assertEquals(5, list.size());
    }

    @DisplayName("Test, try to get element with index less than zero.")
    @Test
    public void tryToGetElementFromListWithIndexLessThanZeroAndCheckSize() {
        list.add("Albus");
        list.add("Percival");
        list.add("Wulfric");
        list.add("Brian");
        list.add("Dumbledore");

        Throwable thrown = assertThrows(IndexOutOfBoundsException.class, () -> {
            list.get(-100);
        });

        assertNotNull(thrown.getMessage());
        assertEquals(5, list.size());
    }

    @DisplayName("Test, set first element in list.")
    @Test
    public void setFirstElementInListAndCheckSize() {
        list.add("Harry");
        list.add("Percival");
        list.add("Wulfric");
        list.add("Brian");
        list.add("Dumbledore");

        list.set("Albus", 0);

        assertEquals("Albus", list.get(0));
        assertEquals(5, list.size());
    }

    @DisplayName("Test, set last element in list.")
    @Test
    public void setLastElementInListAndCheckSize() {
        list.add("Albus");
        list.add("Percival");
        list.add("Wulfric");
        list.add("Brian");
        list.add("Potter");

        list.set("Dumbledore", 4);

        assertEquals("Dumbledore", list.get(4));
        assertEquals(5, list.size());
    }

    @DisplayName("Test, set middle element in list.")
    @Test
    public void setMiddleElementInListAndCheckSize() {
        list.add("Albus");
        list.add("Percival");
        list.add("Severus");
        list.add("Brian");
        list.add("Dumbledore");

        list.set("Wulfric", 2);

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
            list.set("Potter", 111);
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
            list.set("Potter", -100);
        });

        assertNotNull(thrown.getMessage());
        assertEquals(5, list.size());
    }

    @DisplayName("Test, delete all elements, size == 0.")
    @Test
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

    @DisplayName("Test, size after add and remove.")
    @Test
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

    @DisplayName("Test, is empty List true.")
    @Test
    public void isEmptyListExpectTrue() {
        Assertions.assertTrue(list.isEmpty());
    }

    @DisplayName("Test, is empty List false.")
    @Test
    public void isEmptyListExpectFalse() {
        list.add("Albus");
        list.add("Percival");
        list.add("Severus");
        list.add("Brian");
        list.add("Dumbledore");
        Assertions.assertFalse(list.isEmpty());
    }

    @DisplayName("Test, is List contains element true.")
    @Test
    public void listContainsElementExpectTrue() {
        list.add("Albus");
        list.add("Percival");
        list.add("Severus");
        list.add("Brian");
        list.add("Dumbledore");

        assertTrue(list.contains("Percival"));
    }

    @DisplayName("Test, is List contains element false.")
    @Test
    public void listContainsElementExpectFalse() {
        list.add("Albus");
        list.add("Percival");
        list.add("Severus");
        list.add("Brian");
        list.add("Dumbledore");

        assertFalse(list.contains("Potter"));
    }

    @DisplayName("Test, get index of first element in list.")
    @Test
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

    @DisplayName("Test, get last index of.")
    @Test
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

    @DisplayName("Test, is toString() work correctly.")
    @Test
    public void printString() {
        list.add("Scooby");
        list.add("dooby");
        list.add("doo");
        String result = list.toString();
        assertEquals("[Scooby, dooby, doo]", result);
    }

}
