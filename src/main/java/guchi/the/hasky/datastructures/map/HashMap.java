package guchi.the.hasky.datastructures.map;


import com.google.common.annotations.VisibleForTesting;

import java.util.*;


public class HashMap<K, V> implements Map<K, V> {
    public static final int DEFAULT_CAPACITY_FACTOR = 5;
    public static final double DEFAULT_LOAD_FACTOR = 0.75;
    public static final int DEFAULT_GROW_FACTOR = 5;
    private List<Entry<K, V>>[] buckets;
    private int size;

    public HashMap() {
        this(DEFAULT_CAPACITY_FACTOR);
        initializeBuckets();
    }

    @SuppressWarnings("unchecked")
    public HashMap(int initCapacity) {
        this.buckets = new ArrayList[initCapacity];
    }

    @Override
    public V put(K key, V value) {
        validateKey(key);
        if (!containsKey(key)) {
            add(key, value);
            return null;
        }
        return previousValue(key, value);
    }

    @Override
    public V get(K key) {
        return getValue(key);
    }

    @Override
    public boolean containsKey(K key) {
        return getEntry(key) != null;
    }

    @SuppressWarnings("uncheked")
    @Override
    public V remove(K key) {
        if (containsKey(key)) {
            V removedValue = getValue(key);
            List<Entry<K, V>> currentList = getBucket(key);
            currentList.remove(getEntry(key));
            size--;
            return removedValue;
        }
        return null;
    }

    @Override
    public int size() {
        return size;
    }

    public Iterator<Entry<K, V>> iterator() {
        return new MyIterator();
    }

    private class MyIterator implements Iterator<Entry<K, V>> {
        private int bucketIndex = 0;

        private Iterator<Entry<K, V>> bucketIterator = buckets[bucketIndex].iterator();

        @Override
        public boolean hasNext() {
            if (size == 0) {
                return false;
            } else if (bucketIterator.hasNext()) {
                return true;
            }
            bucketIndex++;
            bucketIterator = buckets[bucketIndex].iterator();
            return true;
        }

        @Override
        public Entry<K, V> next() {
            if (!hasNext()) {
                throw new NullPointerException("Map is empty.");
            }
            return bucketIterator.next();
        }

        @Override
        public void remove() {
            if (next() == null) {
                throw new NullPointerException("Nothing to remove");
            }
            bucketIterator.remove();
        }
    }

    @VisibleForTesting
    void validateKey(K key) {
        if (key == null) {
            throw new NullPointerException("The key, can't be null.");
        }
    }

    private void add(K key, V value) {
        Entry<K, V> entry = new Entry<>(key, value);
        List<Entry<K, V>> nodesList = getBucket(key);
        nodesList.add(entry);
        size++;
    }

    private void initializeBuckets() {
        for (int i = 0; i < buckets.length; i++) {
            buckets[i] = new ArrayList<>();
        }
    }

    private List<Entry<K, V>> getBucket(K key) {
        validateKey(key);
        return buckets[getIndex(key)];
    }

    private Entry<K, V> getEntry(K key) {
        validateKey(key);
        List<Entry<K, V>> currentList = getBucket(key);
        for (Entry<K, V> entry : currentList) {
            if (Objects.equals(entry.key, key)) {
                return entry;
            }
        }
        return null;
    }

    private int getIndex(K key) {
        validateKey(key);
        return Math.abs(key.hashCode()) % buckets.length; // ???
    }

    private V getValue(K key) {
        validateKey(key);
        if (containsKey(key)) {
            List<Entry<K, V>> currentList = getBucket(key);
            for (Entry<K, V> entry : currentList) {
                V currentValue = entry.value;
                if (Objects.equals(entry.key, key)) {
                    return currentValue;
                }
            }
        }
        return null;
    }

    private V previousValue(K key, V value) {
        Entry<K, V> currentEntry = getEntry(key);
        assert currentEntry != null;
        V resetValue = currentEntry.value;
        currentEntry.value = value;
        return resetValue;
    }

    static class Entry<K, V> {
        private K key;
        private V value;

        private Entry(K key, V value) {
            this.key = key;
            this.value = value;
        }

        public V getValue() {
            return value;
        }
    }
}

        /*
        if (buckets.length * loadFactor) <= size  {
                grow()
        }*/



