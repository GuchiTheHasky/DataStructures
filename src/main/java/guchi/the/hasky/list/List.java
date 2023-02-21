package guchi.the.hasky.list;

import java.io.IOException;

public interface List<T> extends Iterable<T> {
    void add(T element);
    void add(T element, int index) throws IndexOutOfBoundsException;
    T remove(int index) throws IndexOutOfBoundsException;
    T get(int index) throws IndexOutOfBoundsException;
    void set(T element, int index) throws IndexOutOfBoundsException;
    void clear();
    int size();
    boolean isEmpty();
    boolean contains(T element);
    int indexOf(T element);
    int lastIndexOf(T element);
    String toString();

}
