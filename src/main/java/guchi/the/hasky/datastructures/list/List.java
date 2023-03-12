package guchi.the.hasky.datastructures.list;

public interface List<T> extends Iterable<T> {

    /**
     * Add new element in the end of List.
     */
    void add(T element);

    /**
     * Add new element in List by index
     * between [0, size - 1], otherwise throw new IndexOutOfBoundsException.
     */
    void add(T element, int index);

    /**
     * Remove element from list by index
     * between [0, size - 1], otherwise throw new IndexOutOfBoundsException.
     */
    T remove(int index);

    /**
     * Get element from list by index
     * between [0, size - 1], otherwise throw new IndexOutOfBoundsException.
     */
    T get(int index);

    /**
     * Set value of element by index
     * between [0, size - 1], otherwise throw new IndexOutOfBoundsException.
     */
    T set(T element, int index);

    /**
     * Delete all elements from List, size == 0.
     */
    void clear();

    /**
     * Return List size.
     */
    int size();

    /**
     * Return boolean is List empty, true/false.
     */
    boolean isEmpty();

    /**
     * Return boolean is List contains element, true/false.
     */
    boolean contains(T element);

    /**
     * Return first index of element from List.
     */
    int indexOf(T element);

    /**
     * Return last index of element from List.
     */
    int lastIndexOf(T element);

    /**
     * Turn List to String, in format: "[e, e, e]".
     */
    String toString();

}
