package guchi.the.hasky.datastructures.map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Method;
import java.util.Iterator;

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
    @DisplayName("Test, test put new entries & check size.")
    public void testPutNewEntriesAndCheckSize() {
        assertEquals(3, myMap.size());
    }

    @Test
    @DisplayName("Test, put entry if map already contains key & check size.")
    public void testPutEntryIfMapAlreadyContainsKeyAndCheckSize() {
        myMap.put("Dina", "Staf");

        assertEquals(3, myMap.size());
    }

    @Test
    @DisplayName("Test, put entry if map already contains key return previous value.")
    public void testPutEntryIfMapAlreadyContainsKeyPreviousValue() {
        String previousValue = myMap.put("Dina", "Shepherd");

        assertEquals("Staf", previousValue);
    }

    @Test
    @DisplayName("Test, get first value from map.")
    public void testGetFirstValueFromMap() {
        String expected = "Hasky";
        String actual = myMap.get("Guchi");
        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("Test, get middle value from map.")
    public void testGetMiddleValueFromMap() {
        String expected = "Labrador";
        String actual = myMap.get("Archi");
        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("Test, get last value from map.")
    public void testGetLastValueFromMap() {
        String expected = "Staf";
        String actual = myMap.get("Dina");
        assertEquals(expected, actual);
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
    public void testThrowNullPointerWhenKeyIsNull() throws NoSuchMethodException {
        // Не зміг змінити модифікатор доступу, пробував різні версії гуави, тому так...
        Method method = HashMap.class.getDeclaredMethod("validateKey", Object.class);
        method.setAccessible(true);

        Throwable thrown = assertThrows(IllegalArgumentException.class, () -> {
            method.invoke(myMap, null);
        });
        assertNotNull(thrown.getMessage());
    }

    @Test
    @DisplayName("Test, iterator has next entry true.")
    public void testIteratorHasNextEntryTrue() {
        Iterator<HashMap.Entry<String, String>> iterator = myMap.iterator();
        assertTrue(iterator.hasNext());
    }

    @Test
    @DisplayName("Test, iterator has next entry false.")
    public void testIteratorHasNextEntryFalse() {
        myMap = new HashMap<>();
        Iterator<HashMap.Entry<String, String>> iterator = myMap.iterator();
        assertFalse(iterator.hasNext());
    }

    @Test
    @DisplayName("Test, iterator woks correctly.")
    public void testIteratorWorksCorrectly() {
        HashMap<Integer, Integer> map = new HashMap<>();
        Iterator<HashMap.Entry<Integer, Integer>> iterator = map.iterator();

        map.put(111, 111);
        HashMap.Entry<Integer, Integer> first = iterator.next();
        assertEquals(111, first.getValue());

        iterator.remove();

        map.put(222, 222);
        HashMap.Entry<Integer, Integer> second = iterator.next();
        assertEquals(222, second.getValue());

        iterator.remove();
        assertEquals(0, map.size());
    }

    @Test
    @DisplayName("Test, iterator next throw NullPointerException if map is empty.")
    public void testIteratorNextThrowNullPointerExceptionIfMapIsEmpty() {
        HashMap<Integer, Integer> map = new HashMap<>();
        Throwable thrown = assertThrows(NullPointerException.class, () -> {
            Iterator<HashMap.Entry<Integer, Integer>> iterator = map.iterator();
            iterator.next();
        });

        assertNotNull(thrown.getMessage());
    }

    @Test
    @DisplayName("Test, iterator remove from empty map throw NullPointerException.")
    public void testIteratorRemoveFromEmptyMapThrowNullPointerException() {
        HashMap<Integer, Integer> map = new HashMap<>();
        Throwable thrown = assertThrows(NullPointerException.class, () -> {
            Iterator<HashMap.Entry<Integer, Integer>> iterator = map.iterator();
            iterator.remove();
        });

        assertNotNull(thrown.getMessage());
    }


}
