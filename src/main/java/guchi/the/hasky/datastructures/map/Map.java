package guchi.the.hasky.datastructures.map;

import java.util.Iterator;


public interface Map<K, V> extends Iterable <HashMap.Entry<K,V>> {
    V put(K key, V value);

    V get(K key);

    boolean containsKey(K key);

    V remove(K key);

    int size();



    default Iterator<HashMap.Entry<K,V>> iterator() {
        throw new UnsupportedOperationException();
    }

}
