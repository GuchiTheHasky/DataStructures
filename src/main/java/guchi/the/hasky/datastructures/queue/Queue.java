package guchi.the.hasky.datastructures.queue;

public interface Queue <E> extends Iterable<E> {
    void enqueue(E element);
    E dequeue();
    E peek();
    boolean isEmpty();
    boolean contains(E element);
    int size();
    void clear();

}
