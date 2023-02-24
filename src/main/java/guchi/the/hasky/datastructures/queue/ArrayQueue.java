package guchi.the.hasky.datastructures.queue;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

public class ArrayQueue<E> implements Queue<E> {

    private E[] array;
    private int size;
    private static final int DEFAULT_CAPACITY = 10;

    public ArrayQueue() {
        this(DEFAULT_CAPACITY);
    }

    @SuppressWarnings("unchecked")
    public ArrayQueue(int initCapacity) {
        if (initCapacity <= 0) {
            throw new IllegalArgumentException();
        }
        this.array = (E[]) new Object[initCapacity];
    }

    @Override
    public void enqueue(E element) {
        if (!Objects.isNull(element)) {
            rise();
            array[size] = element;
            size++;
        }
    }

    @Override
    public E dequeue() {
        if (isEmpty()) {
            throw new NoSuchElementException("Queue is empty.");
        }
        E element = array[0];
        System.arraycopy(array, 1, array, 0, size - 1);
        size--;

        return element;
    }

    @Override
    public E peek() {
        if (isEmpty()) {
            throw new NoSuchElementException("Queue is empty.");
        }
        return array[0];
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public boolean contains(E element) {
        if (!isEmpty()) {
            for (int i = 0; i < size; i++) {
                if (element.equals(array[i])) {
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void clear() {
        for (int i = 0; i < size; i++) {
            array[i] = null;
        }
        size = 0;
    }

    @SuppressWarnings("unchecked")
    private void rise() {
        if (array.length == size) {
            E[] tempArray = (E[]) new Object[(int) (size * 1.5)];
            System.arraycopy(array, 0, tempArray, 0, size);
            array = tempArray;
        }
    }

    @Override
    public Iterator<E> iterator() {
        return null;
    }
}
