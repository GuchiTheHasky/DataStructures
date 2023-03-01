package guchi.the.hasky.datastructures.list;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.StringJoiner;

/**
 * @author GuchiTheHasky
 * @since 2023
 */

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private static final double DEFAULT_RISE_VALUE = 1.5;

    private T[] array;
    private int size;
    private double riseValue;

    public ArrayList() {
        this(DEFAULT_CAPACITY);
    }

    public ArrayList(int initCapacity) {
        this(initCapacity, DEFAULT_RISE_VALUE);
    }

    public ArrayList(int initCapacity, double riseValue) {
        if (initCapacity < 0) {
            System.out.println("List size, can't be less than zero.");
        } else {
            array = (T[]) new Object[initCapacity];
            this.riseValue = riseValue;
        }
    }

    @Override
    public void add(T element) {
        add(element, size);
    }

    @Override
    public void add(T element, int index) {
        if (index >= 0 && index <= size) {
            rise();
            System.arraycopy(array, index, array, index + 1, size - index);
            array[index] = element;
            size++;
        } else {
            throw new ArrayIndexOutOfBoundsException(indexAddError(index));
        }
    }

    @Override
    public T remove(int index) {
        if (index >= 0 && index < size) {
            T element = array[index];
            System.arraycopy(array, index + 1, array, index, size - index - 1);
            array[size - 1] = null;
            size--;
            return element;
        } else {
            throw new ArrayIndexOutOfBoundsException(indexError(index));
        }
    }

    @Override
    public T get(int index) {
        if (index >= 0 && index <= size) {
            return array[index];
        } else {
            throw new ArrayIndexOutOfBoundsException(indexError(index));
        }
    }

    @Override
    public T set(T element, int index) {
        if (index >= 0 && index <= size) {
            T resetElement = array[index];
            array[index] = element;
            return resetElement;
        } else {
            throw new ArrayIndexOutOfBoundsException(indexError(index));
        }
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
            if (array[i].equals(element)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public int lastIndexOf(T element) {
        for (int i = size - 1; i >= 0; i--) {
            if (array[i].equals(element)) {
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

    private void rise() {
        if (array.length == size) {
            T[] tempArray = (T[]) new Object[(int) (size * riseValue)];
            System.arraycopy(array, 0, tempArray, 0, size);
            array = tempArray;
        }
    }

    private String indexAddError(int index) {
        return String.format("Error, index: %d;\nIndex less than => \"0\" or more than => \"%d\".", index, size);
    }

    private String indexError(int index) {
        return String.format("Error, index: %d;\nIndex less than => \"0\" or more than => \"%d\".", index, size - 1);
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
                throw new NoSuchElementException("The end.");
            } else {
                T element = array[index];
                index++;
                return element;
            }
        }

        @Override
        public void remove() {
            if (index == 0) {
                throw new IllegalStateException("Nothing to remove.");
            } else {
                ArrayList.this.remove(--index);
            }
        }

    }
}

