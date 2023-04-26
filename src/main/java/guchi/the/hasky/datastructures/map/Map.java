package guchi.the.hasky.datastructures.map;

import static guchi.the.hasky.datastructures.map.HashMap.Entry;


public interface Map<K, V> extends Iterable<Entry<K, V>> {
    /**
     * Add new Entry <K key, V value>,
     * if key already exist, reset value.
     * Returns previous value.
     */
    V put(K key, V value);

    /**
     * Get value by key, if key doesn't exist,
     * return null.
     */
    V get(K key);

    /**
     * Return true if map contains key or
     * false if not.
     */
    boolean containsKey(K key);

    /**
     * Delete entry by kye,
     * return previous value.
     */
    V remove(K key);

    /**
     * Return entry count.
     */
    int size();

    /**
     * Return String of all entries.
     */
    String toString();

    /**
     * Return String of value by key.
     */
    String toString(K key);
}
