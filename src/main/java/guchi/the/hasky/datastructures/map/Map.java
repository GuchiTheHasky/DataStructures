package guchi.the.hasky.datastructures.map;

import java.util.Iterator;

public interface Map<K, V> extends Iterable {
    V put(K key, V value);

    V get(K key);

    int size();

    V remove(K key);

    boolean containsKey(K key);

    void printValues();

    default Iterator iterator() {
        return null;
    }

}
