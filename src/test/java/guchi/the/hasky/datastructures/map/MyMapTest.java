package guchi.the.hasky.datastructures.map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


public class MyMapTest {
    MyMap<String, String> myMap;

    @BeforeEach
    void init() {
        myMap = new MyMap<>();
    }

    @Test
    public void testPut() {
        myMap.put("Guchi", "Hasky");
        myMap.put("Archi", "Labrador");
        myMap.put("Dina", "Staf");

        assertEquals(3, myMap.size());
    }

    @Test
    public void testPutDoubleValue() {
        myMap.put("Guchi", "Hasky");
        myMap.put("Archi", "Labrador");
        myMap.put("Dina", "Staf");
        myMap.put("Dina", "Staf");

        assertEquals(3, myMap.size());
    }

    @Test
    public void testGetFirst() {
        myMap.put("Guchi", "Hasky");
        myMap.put("Archi", "Labrador");
        myMap.put("Dina", "Staf");
        String expected = "Hasky";
        String actual = myMap.get("Guchi");
        assertEquals(expected, actual);
    }

    @Test
    public void testGetMiddle() {
        myMap.put("Guchi", "Hasky");
        myMap.put("Archi", "Labrador");
        myMap.put("Dina", "Staf");
        String expected = "Labrador";
        String actual = myMap.get("Archi");
        assertEquals(expected, actual);
    }

    @Test
    public void testGetLast() {
        myMap.put("Guchi", "Hasky");
        myMap.put("Archi", "Labrador");
        myMap.put("Dina", "Staf");
        String expected = "Staf";
        String actual = myMap.get("Dina");
        assertEquals(expected, actual);
    }

    @Test
    public void testContainsKeyTrue() {
        myMap.put("Guchi", "Hasky");
        myMap.put("Archi", "Labrador");
        myMap.put("Dina", "Staf");

        assertTrue(myMap.containsKey("Guchi"));
        assertTrue(myMap.containsKey("Archi"));
        assertTrue(myMap.containsKey("Dina"));
    }
    @Test
    public void testContainsKeyFalse() {
        myMap.put("Guchi", "Hasky");
        myMap.put("Archi", "Labrador");
        myMap.put("Dina", "Staf");

        assertFalse(myMap.containsKey("Hasky"));
        //assertFalse(myMap.containsKey("Labrador")); -1
        assertFalse(myMap.containsKey("Staf"));
    }

//    @Test
//    public void testRemove() {
//        myMap.put("Guchi", "Hasky");
//        myMap.put("Archi", "Labrador");
//        myMap.put("Dina", "Staf");
//        assertEquals(3, myMap.size());
//
//        myMap.remove("Guchi");
//        assertEquals(2, myMap.size());
//
//        myMap.remove("Archi");
//        assertEquals(1, myMap.size());
//
//        myMap.remove("Dina");
//        assertEquals(0, myMap.size());
//    }





}
