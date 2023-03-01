package guchi.the.hasky.datastructures.map;


import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class HashMapTest {

    @DisplayName("Test, put Entry<Integer, String> in HashMap & check size.")
    @Test
    public void putIntString() {
        HashMap<Integer, String> map = new HashMap<>();
        map.put(5, "Fifth");
        map.put(2, "Second");
        map.put(3, "Third");
        map.put(4, "Fourth");
        map.put(1, "First");


        assertEquals("First", map.get(1));
        assertEquals("Fifth", map.get(5));
        assertEquals("Third", map.get(3));

        assertEquals(5, map.size());
    }

    @DisplayName("Test, put Entry<String, String> in HashMap & check size.")
    @Test
    public void putStringString() {
        Map<String, String> map = new HashMap<>();
        map.put("A", "First");
        map.put("C", "Second");
        map.put("B", "Third");
        map.put("E", "Fourth");
        map.put("D", "Fifth");

        assertEquals("First", map.get("A"));
        assertEquals("Fifth", map.get("D"));
        assertEquals("Third", map.get("B"));

        assertEquals(5, map.size());
    }

    @DisplayName("Test, put the same key & check size.")
    @Test
    public void putTheSameKey() {
        Map<String, String> map = new HashMap<>();
        map.put("A", "First");
        map.put("C", "Second");
        map.put("A", "Third");
        map.put("E", "Fourth");
        map.put("A", "Fifth");

        assertEquals(3, map.size());
    }

    @DisplayName("Test, get entry from HashMap.")
    @Test
    public void get() {
        Map<Integer, String> map = new HashMap<>();
        map.put(1, "First");
        map.put(2, "Second");
        map.put(3, "Third");
        map.put(4, "Fourth");
        map.put(5, "Fifth");

        assertEquals("Second", map.get(2));
        assertEquals("Fourth", map.get(4));
    }

    @DisplayName("Test, contains key: True.")
    @Test
    public void containsTrue() {
        Map<Integer, String> map = new HashMap<>();
        map.put(1, "First");
        map.put(2, "Second");
        map.put(3, "Third");
        map.put(4, "Fourth");
        map.put(5, "Fifth");

        assertTrue(map.containsKey(2));
        assertTrue(map.containsKey(5));
        assertTrue(map.containsKey(1));
    }

    @DisplayName("Test, contains key: False.")
    @Test
    public void containsFalse() {
        Map<Integer, String> map = new HashMap<>();
        map.put(1, "First");
        map.put(2, "Second");
        map.put(3, "Third");
        map.put(4, "Fourth");
        map.put(5, "Fifth");

        assertFalse(map.containsKey(0));
        assertFalse(map.containsKey(-5));
        assertFalse(map.containsKey(9));
    }

}



