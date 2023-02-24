package guchi.the.hasky.datastructures.queue;


import java.util.Iterator;
import java.util.Objects;

public class LinkedQueue<E> implements Queue<E> {
    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {
            Node<E> current = first;
            @Override
            public boolean hasNext() {
                return current != null;
            }

            @Override
            public E next() {
                E element = current.element;
                current = current.next;
                return element;
            }

            @Override
            public void remove() {
               // Iterator.super.remove();
            }
        };
    }

    private static class Node<E>{
        E element;
        Node<E> next;

        public Node(E element) {
            this.element = element;
        }
    }


    private Node<E> first;
    int size;

    @Override
    public void enqueue(E element) {
        if (isEmpty()){
            first = new Node<>(element);
        } else {
            first.next = new Node<>(element);
        }
        size++;
    }

    @Override
    public E dequeue() {
        if (size == 0){
            throw new IllegalArgumentException();
        }
        E element = first.element;
        first = first.next;
        size--;
        return element;
    }
    @Override
    public E peek() {
        return first.element;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public boolean contains(E element) {
        if (!Objects.isNull(element)) {
            Node<E> current = first;
            while (current != null){
                if (Objects.equals(current.element, element)){
                    return true;
                }
                current = current.next;
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
        first = null;
        size = 0;
    }
}
