package guchi.the.hasky.datastructures.map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

public class HashMapTest {
    HashMap<String, String> myMap;

    @BeforeEach
    void init() {
        myMap = new HashMap<>();
        myMap.put("Guchi", "Hasky");
        myMap.put("Archi", "Labrador");
        myMap.put("Dina", "Staf");
    }

    @Test
    @DisplayName("Test, initial capacity throw exception.")
    public void testInitialCapacityThrowException() {
        Throwable thrown = assertThrows(IllegalArgumentException.class, () -> {
            HashMap<Integer, Integer> inMap = new HashMap<>(-1);
        });
        assertNotNull(thrown.getMessage());
    }

    @Test
    @DisplayName("Test, default Grow Factor throw Exception.")
    public void testDefaultGrowFactorThrowException() {
        Throwable thrown = assertThrows(IllegalArgumentException.class, () -> {
            HashMap<Integer, Integer> inMap = new HashMap<>(0, -1);
        });
        assertNotNull(thrown.getMessage());
    }

    @Test
    @DisplayName("Test, default Load Factor throw Exception.")
    public void testDefaultLoadFactorThrowException() {
        Throwable thrown = assertThrows(IllegalArgumentException.class, () -> {
            HashMap<Integer, Integer> inMap = new HashMap<>(0, 2, 0);
        });
        assertNotNull(thrown.getMessage());
    }

    @Test
    @DisplayName("Test, put entry return null if already contains key & check size.")
    public void testPutEntryIfMapAlreadyContainsKeyAndCheckSize() {
        assertNull(myMap.put("Noris", "Staf"));
        assertEquals(4, myMap.size());
    }

    @Test
    @DisplayName("Test, put entry if map already contains key return previous value.")
    public void testPutEntryIfMapAlreadyContainsKeyGetPreviousValue() {
        String previousFirstValue = myMap.put("Guchi", "Shepherd");
        String previousSecondValue = myMap.put("Archi", "Shepherd");
        String previousThirdValue = myMap.put("Dina", "Shepherd");

        assertEquals("Hasky", previousFirstValue);
        assertEquals("Labrador", previousSecondValue);
        assertEquals("Staf", previousThirdValue);
    }

    @Test
    @DisplayName("Test, get different values from map.")
    public void testGetFirstValueFromMap() {
        String expectedFirst = "Hasky";
        String actualFirst = myMap.get("Guchi");
        assertEquals(expectedFirst, actualFirst);

        String expectedSecond = "Labrador";
        String actualSecond = myMap.get("Archi");
        assertEquals(expectedSecond, actualSecond);

        String expectedThird = "Staf";
        String actualThird = myMap.get("Dina");
        assertEquals(expectedThird, actualThird);
    }

    @Test
    @DisplayName("Test, get null if map hasn't key.")
    public void testTryGetValueWithIncorrectKey() {
        String actual = myMap.get("Adam");
        assertNull(actual);
    }

    @Test
    @DisplayName("Test, map contains entry true.")
    public void testMapContainsEntryTrue() {
        assertTrue(myMap.containsKey("Guchi"));
        assertTrue(myMap.containsKey("Archi"));
        assertTrue(myMap.containsKey("Dina"));
    }

    @Test
    @DisplayName("Test, map contains entry false.")
    public void testMapContainsEntryFalse() {
        assertFalse(myMap.containsKey("Hasky"));
        assertFalse(myMap.containsKey("Staf"));
    }

    @Test
    @DisplayName("Test, remove first entry & check size.")
    public void testRemoveFirstEntryCheckSize() {
        String removedValue = myMap.remove("Guchi");

        assertEquals(2, myMap.size());
        assertEquals("Hasky", removedValue);
    }

    @Test
    @DisplayName("Test, remove middle entry & check size.")
    public void testRemoveMiddleEntryCheckSize() {
        String removedValue = myMap.remove("Guchi");

        assertEquals(2, myMap.size());
        assertEquals("Hasky", removedValue);
    }

    @Test
    @DisplayName("Test, remove last entry & check size.")
    public void testRemoveLastEntryCheckSize() {
        String removedValue = myMap.remove("Guchi");

        assertEquals(2, myMap.size());
        assertEquals("Hasky", removedValue);
    }

    @Test
    @DisplayName("Test, try remove entry if map doesn't contains key % check size.")
    public void testRemoveEntryIfKeyIsNotValid() {
        myMap.remove("Noris");
        assertEquals(3, myMap.size());
    }

    @Test
    @DisplayName("Test, throw null pointer exception when key is null.")
    public void testThrowNullPointerWhenKeyIsNull() {
        Throwable thrown = assertThrows(NullPointerException.class, () -> {
            myMap.validateKey(null);
        });
        assertNotNull(thrown.getMessage());
    }

    @Test
    @DisplayName("Test, iterator has next entry true.")
    public void testIteratorHasNextEntryTrue() {
        Iterator<HashMap.Entry<String, String>> iterator = myMap.iterator();
        assertTrue(iterator.hasNext());
        iterator.next();
        assertTrue(iterator.hasNext());
        iterator.next();
        assertTrue(iterator.hasNext());
        iterator.next();
        assertFalse(iterator.hasNext());
    }

    @Test
    @DisplayName("Test, iterator has next entry return false if map is empty.")
    public void testIteratorHasNextEntryFalse() {
        HashMap<String, String> testMap = new HashMap<>();
        Iterator<HashMap.Entry<String, String>> iterator = testMap.iterator();
        assertFalse(iterator.hasNext());
    }

    @Test
    @DisplayName("Test, iterator next() throw exception if map is empty.")
    public void testIteratorNextThrowExceptionInEmptyMap() {
        Throwable thrown = assertThrows(NoSuchElementException.class, () -> {
            HashMap<String, String> testMap = new HashMap<>();
            Iterator<HashMap.Entry<String, String>> iterator = testMap.iterator();
            iterator.next();
        });

        assertNotNull(thrown.getMessage());
    }

    @Test
    @DisplayName("Test, iterator next() throw exception in map with values.")
    public void testIteratorNextThrowExceptionInMapWithValues() {
        Throwable thrown = assertThrows(NoSuchElementException.class, () -> {
            Iterator<HashMap.Entry<String, String>> iterator = myMap.iterator();
            iterator.next();
            iterator.next();
            iterator.next();
            iterator.next();
        });

        assertNotNull(thrown.getMessage());
    }

    @Test
    @DisplayName("Test, iterator method next() return values.")
    public void testIteratorWorksCorrectly() {
        Iterator<HashMap.Entry<String, String>> iterator = myMap.iterator();
        assertEquals("Staf", iterator.next().getValue());
        assertEquals("Hasky", iterator.next().getValue());
        assertEquals("Labrador", iterator.next().getValue());
    }

    @Test
    @DisplayName("Test, iterator remove from empty map throw IllegalStateException.")
    public void testIteratorRemoveFromEmptyMapThrowIllegalStateException() {
        HashMap<Integer, Integer> map = new HashMap<>();
        Throwable thrown = assertThrows(IllegalStateException.class, () -> {
            Iterator<HashMap.Entry<Integer, Integer>> iterator = map.iterator();
            iterator.remove();
        });
        assertNotNull(thrown.getMessage());
    }

    @Test
    @DisplayName("Test, iterator remove & check size.")
    public void testIteratorRemoveAndCheckSize() {
        Iterator<HashMap.Entry<String, String>> iterator = myMap.iterator();
        assertEquals(3, myMap.size());
        iterator.next();
        iterator.remove();
        assertEquals(2, myMap.size());
        iterator.next();
        iterator.remove();
        assertEquals(1, myMap.size());
        iterator.next();
        iterator.remove();
        assertEquals(0, myMap.size());
    }

    @Test
    @DisplayName("Test, toString works correctly.")
    public void testToStringWorksCorrectly() {
        String actualFirst = myMap.toString("Guchi");
        String actualSecond = myMap.toString("Archi");
        String actualThird = myMap.toString("Dina");
        String expectedFirst = "Hasky";
        String expectedSecond = "Labrador";
        String expectedThird = "Staf";

        assertEquals(expectedFirst, actualFirst);
        assertEquals(expectedSecond, actualSecond);
        assertEquals(expectedThird, actualThird);
    }

}
