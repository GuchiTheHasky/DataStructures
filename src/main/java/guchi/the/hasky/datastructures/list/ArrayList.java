package guchi.the.hasky.datastructures.list;

import guchi.the.hasky.datastructures.annotation.DefaultModifierForTests;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.StringJoiner;

/**
 * @author GuchiTheHasky
 * @since 2023
 */


public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private static final double DEFAULT_GROW_FACTOR = 1.5;

    private T[] array;
    private int size;
    private final double growFactor;

    public ArrayList() {
        this(DEFAULT_CAPACITY);
    }

    public ArrayList(int initCapacity) {
        this(initCapacity, DEFAULT_GROW_FACTOR);
    }

    @SuppressWarnings("uncheked")
    public ArrayList(int initCapacity, double growFactor) {
        if (initCapacity < 0) {
            throw new IndexOutOfBoundsException(initCapacityErrorMessage(initCapacity));
        }
        this.array = (T[]) new Object[initCapacity];
        this.growFactor = growFactor;
    }

    @Override
    public void add(T element) {
        add(element, size);
    }

    @Override
    public void add(T element, int index) {
        validateAddIndex(index);
        grow();
        System.arraycopy(array, index, array, index + 1, size - index);
        array[index] = element;
        size++;
    }

    @Override
    public T remove(int index) {
        validateIndex(index);
        T element = array[index];
        System.arraycopy(array, index + 1, array, index, size - index - 1);
        array[size - 1] = null;
        size--;
        return element;
    }

    @Override
    public T get(int index) {
        validateIndex(index);
        assert array[index] != null;
        return array[index];
    }

    @Override
    public T set(T element, int index) {
        validateIndex(index);
        T resetElement = array[index];
        array[index] = element;
        return resetElement;
    }

    @Override
    public void clear() {
        for (int i = 0; i < size; i++) {
            array[i] = null;
        }
        size = 0;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public boolean contains(T element) {
        return indexOf(element) != -1;
    }

    @Override
    public int indexOf(T element) {
        for (int i = 0; i < size; i++) {
            if (Objects.equals(array[i], element)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public int lastIndexOf(T element) {
        for (int i = size - 1; i >= 0; i--) {
            if (Objects.equals(array[i], element)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public String toString() {
        StringJoiner joiner = new StringJoiner(", ", "[", "]");
        for (T element : this) {
            joiner.add(String.valueOf(element.toString()));
        }
        return joiner.toString();
    }

    @Override
    public Iterator<T> iterator() {
        return new MyIterator();
    }

    @DefaultModifierForTests
    T[] getArray() {
        return array;
    }

    private void grow() {
        if (array.length == 0 || array.length == 1) {
            array = (T[]) new Object[DEFAULT_CAPACITY / 2];
        } else if (size == array.length) {
            T[] tempArray = (T[]) new Object[(int) Math.ceil(size * growFactor) + 1];
            System.arraycopy(array, 0, tempArray, 0, size);
            array = tempArray;
        }
    }

    private void validateAddIndex(int index) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException(indexAddErrorMessage(index));
        }
    }

    private void validateIndex(int index) {
        if (index < 0 || index > size - 1) {
            throw new IndexOutOfBoundsException(indexErrorMessage(index));
        }
    }

    private String initCapacityErrorMessage(int capacity) {
        return String.format("Initial capacity: %d, \ncan't be less than \"0\".", capacity);
    }

    private String indexAddErrorMessage(int index) {
        return String.format("Index: %d;\nIndex must be between " +
                "\"0\" and \"%d\".", index, size);
    }

    private String indexErrorMessage(int index) {
        return String.format("Index: %d;\nIndex must be between " +
                "\"0\" and \"%d\".", index, size - 1);
    }

    private class MyIterator implements Iterator<T> {
        private int index;

        @Override
        public boolean hasNext() {
            return index < size;
        }

        @Override
        public T next() {
            if (!hasNext()) {
                throw new NoSuchElementException("No more elements.");
            }
            T element = array[index];
            index++;
            return element;
        }

        @Override
        public void remove() {
            if (index == 0) {
                throw new IllegalStateException("Nothing to remove.");
            }
            ArrayList.this.remove(--index);
        }
    }
}

