package guchi.the.hasky.queue;

public interface Queue <E> {
    void enqueue(E element);
    E dequeue();
    E peek();
    boolean isEmpty();
    boolean contains(E element);
    int size();
    void clear();

}
