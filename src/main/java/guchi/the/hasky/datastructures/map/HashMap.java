package guchi.the.hasky.datastructures.map;


import java.util.*;

public class HashMap<K, V> implements Map<K, V> {
    public static final int DEFAULT_CAPACITY = 5;
    private List<Entry<K, V>>[] buckets;
    private int size;

    public HashMap() {
        this(DEFAULT_CAPACITY);
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
        return new Iterator() {
            private int bucketIndex;
            private int entryIndex;

            @Override
            public boolean hasNext() {
                for (int i = 0; i < DEFAULT_CAPACITY; i++) {
                    if (buckets[i].size() > 0) {
                        return true;
                    }
                }
                return false;
            }

            @Override
            public Entry<K, V> next() {
                if (!hasNext()) {
                    throw new NullPointerException("No more entries here.");
                }
                return nextEntry();
            }

            @Override
            public void remove() {
                if (!hasNext()) {
                    throw new NullPointerException("Nothing to remove.");
                }
                buckets[bucketIndex].remove(entryIndex);
                size--;
            }
            private Entry<K, V> nextEntry() {
                int entryCount = 0;
                while (bucketIndex < DEFAULT_CAPACITY && entryCount < size) {
                    for (int i = 0; i < buckets[bucketIndex].size(); i++) {
                        if (buckets[bucketIndex].get(entryIndex) != null) {
                            @SuppressWarnings("uncheked")
                            Entry<K, V> current = buckets[bucketIndex].get(entryIndex);
                            return current;
                        }
                        entryIndex++;
                        entryCount++;
                    }
                    entryIndex = 0;
                    bucketIndex++;
                }
                return null;
            }
        };
    }
    private void validateKey(K key) {
        if (key == null) {
            throw new IllegalArgumentException("The key, can't be null.");
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
        return key.hashCode() % buckets.length;
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

    private V previousValue(K key, V value) { // add method
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
