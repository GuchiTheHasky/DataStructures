package guchi.the.hasky.queue;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static junit.framework.Assert.*;


public class ArrayQueueTest {
    ArrayQueue<String> queue = new ArrayQueue<>();
    @DisplayName("Test, add elements in queue and check size.")
    @Test
    public void enqueue(){
        queue.enqueue("Lukaku");
        queue.enqueue("Zlatan");
        queue.enqueue("Mudryk");

        assertEquals(3, queue.size());
    }
    @DisplayName("Test, remove elements from queue and check size.")
    @Test
    public void dequeue(){
        queue.enqueue("Lukaku");
        queue.enqueue("Zlatan");
        queue.enqueue("Mudryk");
        queue.dequeue();

        assertEquals(2, queue.size());
    }

    @DisplayName("Test, show element to delete.")
    @Test
    public void peek(){
        queue.enqueue("Lukaku");
        queue.enqueue("Zlatan");
        queue.enqueue("Mudryk");

        assertEquals("Lukaku", queue.peek());
    }
    @DisplayName("Test, is Empty True.")
    @Test
    public void isEmptyTrue(){
        assertTrue(queue.isEmpty());
    }
    @DisplayName("is Empty False.")
    @Test
    public void isEmptyFalse(){
        queue.enqueue("Lukaku");
        queue.enqueue("Zlatan");
        queue.enqueue("Mudryk");

        assertFalse(queue.isEmpty());
    }

    @DisplayName("Test, contains True.")
    @Test
    public void containsTrue(){
        queue.enqueue("Lukaku");
        queue.enqueue("Zlatan");
        queue.enqueue("Mudryk");

        assertTrue(queue.contains("Lukaku"));
    }
    @DisplayName("Test, contains False.")
    @Test
    public void containsFalse(){
        queue.enqueue("Lukaku");
        queue.enqueue("Zlatan");
        queue.enqueue("Mudryk");

        assertFalse(queue.contains("Ronaldo"));
    }





}
