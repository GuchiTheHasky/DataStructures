package guchi.the.hasky.datastructures.list;

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
    private double growValue;

    public ArrayList() {
        this(DEFAULT_CAPACITY);
    }

    public ArrayList(int initCapacity) {
        this(initCapacity, DEFAULT_GROW_FACTOR);
    }

    public ArrayList(int initCapacity, double riseValue) {
        if (initCapacity < 0) {
            throw new IndexOutOfBoundsException(initCapacityErrorMessage(initCapacity));
        } else {
            array = (T[]) new Object[initCapacity];
            this.growValue = riseValue;
        }
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

    private void grow() {
        if (size == 0) {
            array = (T[]) new Object[(int) (DEFAULT_CAPACITY)];
        } else if (size == 1) {
            T[] tempArray = (T[]) new Object[(int) (size * 2)];
            tempArray[0] = array[0];
            array = tempArray;
        } else {
            T[] tempArray = (T[]) new Object[(int) (size * growValue)];
            System.arraycopy(array, 0, tempArray, 0, size);
            array = tempArray;
        }
    }

    private void validateAddIndex(int index) {
        if (index < 0 || index > size) {
            throw new ArrayIndexOutOfBoundsException(indexAddErrorMessage(index));
        }
    }

    private void validateIndex(int index) {
        if (index < 0 || index > size - 1) {
            throw new ArrayIndexOutOfBoundsException(indexErrorMessage(index));
        }
    }

    private String initCapacityErrorMessage(int index) {
        return String.format("Error, index: %d, can't be less than \"0\".", index);
    }

    private String indexAddErrorMessage(int index) {
        return String.format("Error, index: %d;\nIndex can't be less than => " +
                "\"0\" or more than => \"%d\".", index, size);
    }

    private String indexErrorMessage(int index) {
        return String.format("Error, index: %d;\nIndex can't be less than => " +
                "\"0\" or more than => \"%d\".", index, size - 1);
    }

    @Override
    public Iterator<T> iterator() {
        return new MyIterator();
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

