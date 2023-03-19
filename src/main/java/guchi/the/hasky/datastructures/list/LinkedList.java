package guchi.the.hasky.datastructures.list;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.StringJoiner;

/**
 * @author GuchiTheHasky
 * @since 2023
 */

public class LinkedList<T> implements List<T> {
    private Node<T> first;
    private Node<T> last;
    private int size;

    @Override
    public void add(T element) {
        add(element, size);
    }

    @Override
    public void add(T element, int index) {
        validateAddIndex(index);
        Node<T> node = new Node<>(element);
        add(index, node);
        size++;
    }

    @Override
    public T remove(int index) {
        validateIndex(index);
        Node<T> current = getNode(index);
        removeNode(current);
        return current.element;
    }

    @Override
    public T get(int index) {
        validateIndex(index);
        Node<T> node = getNode(index);
        return node.element;
    }

    @Override
    public T set(T element, int index) {
        validateIndex(index);
        Node<T> node = getNode(index);
        T resetElement = node.element;
        node.element = element;
        return resetElement;
    }

    @Override
    public void clear() {
        first = last = null;
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
        Node<T> current = first;
        for (int i = 0; i < size; i++) {
            if (Objects.equals(current.element, element)) {
                return i;
            }
            current = current.next;
        }
        return -1;
    }

    @Override
    public int lastIndexOf(T element) {
        Node<T> current = last;
        for (int i = size - 1; i >= 0; i--) {
            if (Objects.equals(current.element, element)) {
                return i;
            }
            current = current.previous;
        }
        return -1;
    }

    @Override
    public String toString() {
        StringJoiner joiner = new StringJoiner(", ", "[", "]");
        for (T element : this) {
            joiner.add(String.valueOf(element));
        }
        return joiner.toString();
    }

    @Override
    public Iterator<T> iterator() {
        return new ListIterator();
    }

    private void add(int index, Node<T> node) {
        if (size == 0) {
            first = last = node;
        } else if (index == 0) {
            node.next = first;
            first.previous = node;
            first = node;
        } else if (index == size) {
            node.previous = last;
            last.next = node;
            last = node;
        } else {
            Node<T> current = getNode(index);
            node.next = current;
            node.previous = current.previous;
            current.previous.next = node;
        }
    }

    private T removeNode(Node<T> node) {
        if (size == 1) {
            first = null;
            last = null;
        } else if (node == first) {
            first = node.next;
            first.previous = null;
        } else if (node == last) {
            last = node.previous;
            last.previous = null;
        } else {
            node.previous.next = node.next;
            node.next = node.previous;
        }
        size--;
        return node.element;
    }

    private void validateAddIndex(int index) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException(indexAddErrorMessage(index));
        }
    }

    private void validateIndex(int index) {
        if (index < 0 || index > size - 1) {
            throw new IndexOutOfBoundsException(indexErrorMessage(index));
        }
    }

    private String indexAddErrorMessage(int index) {
        return String.format("Error, index: %d;\nIndex must be between " +
                "\"0\" and \"%d\".", index, size);
    }

    private String indexErrorMessage(int index) {
        return String.format("Error, index: %d;\nIndex must be between " +
                "\"0\" and \"%d\".", index, size - 1);
    }

    private Node<T> getNode(int index) {
        Node<T> current;
        if (index <= size / 2) {
            current = first;
            for (int i = 0; i < index; i++) {
                current = current.next;
            }
        } else {
            current = last;
            for (int i = size - 1; i > index; i--) {
                current = current.previous;
            }
        }
        return current;
    }

    private class ListIterator implements Iterator<T> {
        private Node<T> current = first;

        @Override
        public boolean hasNext() {
            return current != null;
        }

        @Override
        public T next() {
            if (!hasNext()) {
                throw new NoSuchElementException("No more elements.");
            }
            T element = current.element;
            current = current.next;
            return element;
        }

        @Override
        public void remove() {
            if (!hasNext()) {
                throw new UnsupportedOperationException("Invoke next() method first.");
            }
            removeNode(current.previous);
        }
    }

    private static class Node<T> {
        private T element;
        private Node<T> next;
        private Node<T> previous;

        private Node(T element) {
            this.element = element;
        }
    }
}
