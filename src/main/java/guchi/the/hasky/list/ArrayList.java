package guchi.the.hasky.list;

import java.util.Objects;
import java.util.StringJoiner;

public class ArrayList<T> implements List<T> {
    private T[] array;
    private int size;
    private static final int DEFAULT_CAPACITY = 10;

    @SuppressWarnings("unchecked")
    public ArrayList(int initCapacity) {
        if (initCapacity <= 0) {
            throw new IllegalArgumentException();
        }
        this.array = (T[]) new Object [initCapacity];
    }

    public ArrayList() {
        this(DEFAULT_CAPACITY);
    }

    @Override
    public void add(T element) {
        add(element, size);
    }


    @Override
    public void add(T element, int index) {
        if (!Objects.isNull(element)) {
            Objects.checkIndex(index, size + 1);
            rise();
            System.arraycopy(array, index, array, index + 1, size - index);
            array[index] = element;
            size++;
        }
    }

    @Override
    public T remove(int index) {
        Objects.checkIndex(index, size);
        T element = array[index];
        System.arraycopy(array, index + 1, array, index, size - index - 1);
        size--;
        return element;
    }

    @Override
    public T get(int index) {
        Objects.checkIndex(index, size);
        return array[index];
    }

    @Override
    public void set(T element, int index) {
        Objects.checkIndex(index, size);
        array[index] = element;
    }

    @Override
    public void clear() {
        if (!isEmpty()) {
            for (int i = 0; i < size; i++) {
                array[i] = null;
            }
            size = 0;
        }
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
        if (Objects.isNull(element)){
            return -1;
        }
        for (int i = 0; i < size; i++) {
            if (array[i].equals(element)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public int lastIndexOf(T element) {
        if (Objects.isNull(element)){
            return -1;
        }
        for (int i = size - 1; i >= 0; i--) {
            if (array[i].equals(element)) {
                return i;
            }
        }
        return -1;
    }
    @Override
    public String toString(){
        StringJoiner joiner = new StringJoiner("," , "{", "}");
        for (int i = 0; i < size; i++) {
            joiner.add(array[i].toString());
        }
        return joiner.toString();
    }

    @SuppressWarnings("unchecked")
    private void rise() {
        if (array.length == size) {
            T[] tempArray = (T[])new Object[(int) (size * 1.5)]; //
            System.arraycopy(array, 0, tempArray, 0, size);
            array = tempArray;
        }
    }
}

