package guchi.the.hasky.list;

import java.util.Objects;
import java.util.StringJoiner;

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
        if (index < 0 && index > size - 1) {
            throw new IndexOutOfBoundsException("Index out of bounds.");
        }
        Node<T> node = new Node<>(element);
        if (size == 0) {
            first = node;
            last = node;
        } else if (index == 0) {
            node.next = first;
            first.previous = node;
            first = node;
        } else if (index == size) {
            node.previous = last;
            last.next = node;
            last = node;
        } else {
            Node<T> middleNode = first;
            for (int i = 0; i < index; i++) {
                middleNode = middleNode.next;
            }
            node.next = middleNode;
            node.previous = middleNode.previous;
            middleNode.previous.next = node;
        }
        size++;
    }
    /*    @Override
    public void add(T element, int index) {
        if (index < 0 && index > size - 1){
            throw new IndexOutOfBoundsException("Index out of bound.");
        }
        Node<T> node = new Node<>(element);
        if (first == null){
            first = last = node;
        }
        else if (index == 0){
            node.next = first;
            first = node;
            node.next.previous = node;
        }
        else if (index > 0 && index < size - 1) {
            Node<T> current = getNodeByIndex(index - 1);
            current.next = node;
            current.next.previous = getNodeByIndex(index - 1);
        }
        else {
            Node<T> current = getNodeByIndex(index - 1);
            current.next = node;
            current.next.previous = getNodeByIndex(index - 1);
            last = node;
        }
        size++;
    }*/

    @Override
    public T remove(int index) { // має бути чотири кейси
        T removedElement = null;
        if (!isEmpty()) {
            if (index < 0 && index > size - 1) {
                throw new IndexOutOfBoundsException(indexError());
            }
        }


        return removedElement;
    }

//    @Override
//    public T remove(int index) { // має бути чотири кейси
//        Objects.checkIndex(index, size);
//        T removedElement;
//        if (index == 0){
//            removedElement = first.element;
//            first = first.next;
//        } else if (index < size - 1) {
//            Node<T> previous = getNode(index - 1);
//            removedElement = previous.next.element;
//            previous.next = previous.next.next;
//            if (index == size - 1){
//                last = previous;
//            }
//            previous.next.previous = previous;
//        }
//        else {
//            Node<T> previous = getNode(index - 1);
//            removedElement = previous.next.element;
//            previous.next = previous.next.next;
//            if (index == size - 1){
//                last = previous;
//            }
//        }
//        size--;
//        return removedElement;
//    }

    @Override
    public T get(int index) {
        Objects.checkIndex(index, size);
        return getNode(index).element;
    }

    @Override
    public void set(T element, int index) {
        Objects.checkIndex(index, size);
        Node<T> node = getNode(index);
        node.element = element;
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
        Node<T> current = first;
        for (int i = 0; i < size; i++) {
            if (current.element.equals(element) && i == size - 1 ||
                    current.element.equals(element) && !current.next.element.equals(element)
            ) {
                return i;
            }
            current = current.next;
        }
        return -1;
    }

    @Override
    public String toString() {
        StringJoiner joiner = new StringJoiner(",", "{", "}");
        Node<T> current = first;
        while (current != null) {
            joiner.add((CharSequence) current);
            current = current.next;
        }
        return joiner.toString();
    }

    // написати метод так, щоб не віднімати 1
    private Node<T> getNode(int index) {
        Node<T> current = first;
        for (int i = 0; i < index; i++) {
            current = current.next;
        }
        return current;
    }
    private String indexError() {
        return "Error, index: less than: \"0\" or more than: \"" + (size + 1) + "\".";
    }


    @Override
    public java.util.Iterator<T> iterator() {
        return null;
    }

//    @SuppressWarnings("unchecked")
//    private class Iterator<T> implements MyIterator<T>{
//        private Node<T> current = (Node<T>) first;
//
//        @Override
//        public boolean hasNext() {
//            return current != null;
//        }
//
//        @Override
//        public T next() {
//            T element = current.element;
//            current = current.next;
//            return element;
//        }
//    }


    private static class Node<T> {
        private T element;
        private Node<T> next;
        private Node<T> previous;

        public Node(T element) {
            this.element = element;
        }
    }
}
