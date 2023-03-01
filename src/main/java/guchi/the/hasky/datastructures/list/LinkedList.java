package guchi.the.hasky.datastructures.list;

import java.util.Iterator;
import java.util.NoSuchElementException;
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
        if (index >= 0 && index <= size) {
            Node<T> node = new Node<>(element);
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
            size++;
        } else {
            throw new ArrayIndexOutOfBoundsException(indexAddError(index));
        }
    }

    @Override
    public T remove(int index) {
        if (index < 0 || index > size - 1) {
            throw new ArrayIndexOutOfBoundsException(indexError(index));
        } else {
            Node<T> removedElement = getNode(index);
            if (size == 1) {
                first = null;
                last = null;
            } else if (index == 0) {
                first.next.previous = null;
                first = first.next;
            } else if (index == size - 1) {
                last.previous.next = null;
                last = last.previous;
            } else {
                Node<T> current = getNode(index);
                current.previous.next = current.next;
                current.next.previous = current.previous;
            }
            size--;
            return (T) removedElement;
        }
    }

    @Override
    public T get(int index) {
        if (index < 0 || index > size - 1) {
            throw new ArrayIndexOutOfBoundsException(indexError(index));
        }
        return getNode(index).element;
    }

    @Override
    public T set(T element, int index) {
        if (index < 0 || index > size - 1) {
            throw new ArrayIndexOutOfBoundsException(indexError(index));
        } else {
            Node<T> node = getNode(index);
            T resetNode = node.element;
            node.element = element;
            return resetNode;
        }
    }

    @Override
    public void clear() {
        size = 0;
        first = last = null;
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
            if (current.element.equals(element)) {
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
            if (current.element.equals(element)) {
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

    private class ListIterator implements Iterator<T> {
        private Node<T> current = first;
        private boolean canRemove;

        @Override
        public boolean hasNext() {
            return current != null;
        }

        @Override
        public T next() {
            if (!hasNext()) {
                throw new NoSuchElementException("It's an emptiness.");
            }
            T element = current.element;
            current = current.next;
            canRemove = true;
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

    private String indexError(int index) {
        return String.format("Error, index: %d;\nIndex less than => \"0\" or more than => \"%d\".", index, size - 1);
    }

    private String indexAddError(int index) {
        return String.format("Error, index: %d;\nIndex less than => \"0\" or more than => \"%d\".", index, size);
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
            node = first.next;
            node.next.previous = node.previous;
        }
        size--;
        return (T) node;
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
