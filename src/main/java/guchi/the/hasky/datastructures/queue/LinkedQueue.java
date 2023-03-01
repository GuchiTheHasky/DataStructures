package guchi.the.hasky.datastructures.queue;


import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

public class LinkedQueue<E> implements Queue<E> {

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
        @Override
    public Iterator<E> iterator() {
            return new QueueIterator();
        }
        private class QueueIterator implements Iterator<E> {
            Node<E> current = first;
            private boolean canRemove;

            @Override
            public boolean hasNext() {
                return current != null;
            }

            @Override
            public E next() {
                if (!hasNext()){
                    throw new NoSuchElementException("It's an emptiness.");
                }
                E element = current.element;
                canRemove = true;
                current = current.next;
                return element;
            }

            @Override
            public void remove() {
                if (!canRemove) {
                    throw new IllegalStateException("Nothing to remove.");
                }
                removeNode(current);
            }
        }

        private E removeNode(Node<E> node) {
            node.element = first.element;
            first = first.next;
            size--;
            return node.element;
        }


}
