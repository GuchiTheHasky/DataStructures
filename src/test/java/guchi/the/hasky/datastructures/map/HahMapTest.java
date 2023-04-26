package guchi.the.hasky.datastructures.map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;
import static guchi.the.hasky.datastructures.map.HashMap.Entry;

public class HahMapTest {
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
        int initCapacity = -1;
        Throwable thrown = assertThrows(IllegalArgumentException.class, () -> {
            HashMap<Integer, Integer> inMap = new HashMap<>(initCapacity);
        });
        assertNotNull(thrown.getMessage());
        String message = "Error, wrong initial capacity: " + initCapacity +
                ".\nYou can't input value less than \"0\".";
        assertEquals(message, thrown.getMessage());
    }

    @Test
    @DisplayName("Test, default Grow Factor throw Exception.")
    public void testDefaultGrowFactorThrowException() {
        int growFactor = -1;
        Throwable thrown = assertThrows(IllegalArgumentException.class, () -> {
            HashMap<Integer, Integer> inMap = new HashMap<>(0, -1);
        });
        assertNotNull(thrown.getMessage());
        String message = "Error, wrong:\n" +
                " grow factor - " + growFactor +" || load factor - 0,8\n" +
                "You can't input value less than \"1\".";
        assertEquals(message, thrown.getMessage());
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
    @DisplayName("Test, iterator method hasNext() work correctly, true.")
    public void testIteratorHasNextEntryTrue() {
        Iterator<HashMap.Entry<String, String>> iterator = myMap.iterator();
        assertTrue(iterator.hasNext());
        assertTrue(iterator.hasNext());
        assertTrue(iterator.hasNext());
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
        Iterator<Entry<String, String>> iterator = testMap.iterator();
        assertFalse(iterator.hasNext());
    }

    @Test
    @DisplayName("Test, iterator next() throw exception if map is empty.")
    public void testIteratorNextThrowExceptionInEmptyMap() {
        Throwable thrown = assertThrows(NoSuchElementException.class, () -> {
            HashMap<String, String> testMap = new HashMap<>();
            Iterator<Entry<String, String>> iterator = testMap.iterator();
            iterator.next();
        });

        assertNotNull(thrown.getMessage());
    }

    @Test
    @DisplayName("Test, iterator next() throw exception in map with values.")
    public void testIteratorNextThrowExceptionInMapWithValues() {
        Throwable thrown = assertThrows(NoSuchElementException.class, () -> {
            Iterator<Entry<String, String>> iterator = myMap.iterator();
            iterator.next();
            iterator.next();
            iterator.next();
            iterator.next();
        });

        assertNotNull(thrown.getMessage());
    }

    @Test
    @DisplayName("Test, iterator method next() return correct values.")
    public void testIteratorWorksCorrectly() {
        Iterator<Entry<String, String>> iterator = myMap.iterator();
        if (iterator.hasNext()) {
            Entry<String, String> entry1 = iterator.next();
            String value1 = entry1.getValue();
            if (iterator.hasNext()) {
                Entry<String, String> entry2 = iterator.next();
                String value2 = entry2.getValue();
                if (iterator.hasNext()) {
                    Entry<String, String> entry3 = iterator.next();
                    String value3 = entry3.getValue();

                    assertEquals("Staf", value1);
                    assertEquals("Hasky", value2);
                    assertEquals("Labrador", value3);
                }
            }
        }
    }

    @Test
    @DisplayName("Test, iterator remove from empty map throw IllegalStateException.")
    public void testIteratorRemoveFromEmptyMapThrowIllegalStateException() {
        HashMap<Integer, Integer> map = new HashMap<>();
        Throwable thrown = assertThrows(IllegalStateException.class, () -> {
            Iterator<Entry<Integer, Integer>> iterator = map.iterator();
            iterator.remove();
        });
        assertNotNull(thrown.getMessage());
    }

    @Test
    @DisplayName("Test, iterator method remove works correctly & check size.")
    public void testIteratorRemoveAndCheckSize() {
        Iterator<Entry<String, String>> iterator = myMap.iterator();
        assertEquals(3, myMap.size());
        while (iterator.hasNext()) {
            iterator.next();
            iterator.remove();
        }
        assertFalse(iterator.hasNext());
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
