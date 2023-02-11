package guchi.the.hasky.list;

public interface List<T> {
    void add(T element);
    void add(T element, int index);
    T remove(int index);
    T get(int index);
    void set(T element, int index);
    void clear();
    int size();
    boolean isEmpty();
    boolean contains(T element);
    int indexOf(T element);
    int lastIndexOf(T element);
    String toString();

}
