package guchi.the.hasky.datastructures.map;

import java.util.*;

public class HashMap<K, V> implements Map<K, V> {
    public static final int DEFAULT_CAPACITY = 10;
    public static final double DEFAULT_LOAD_FACTOR = 0.75;
    public static final int DEFAULT_GROW_FACTOR = 5;
    private final int growFactor;
    private final double loadFactor;
    private List<Entry<K, V>>[] buckets;
    private int size;


    public HashMap() {
        this(DEFAULT_CAPACITY);
    }

    public HashMap(int initCapacity) {
        this(initCapacity, DEFAULT_GROW_FACTOR);
    }

    public HashMap(int initCapacity, int defaultGrowFactor) {
        this(initCapacity, defaultGrowFactor, DEFAULT_LOAD_FACTOR);
    }

    @SuppressWarnings("unchecked")
    public HashMap(int initCapacity, int growFactor, double loadFactor) {
        if (initCapacity < 0) {
            throw new IllegalArgumentException(errorCapacityMessage(initCapacity));
        }
        if (loadFactor <= 0 || growFactor <= 0) {
            throw new IllegalArgumentException(errorFactorMessage(growFactor, loadFactor));
        }
        this.buckets = new ArrayList[initCapacity];
        this.growFactor = growFactor;
        this.loadFactor = loadFactor;
        initializeBuckets();
    }

    @Override
    public V put(K key, V value) {
        growIfNeeded();
        if (!containsKey(key)) {
            Entry<K, V> entry = new Entry<>(key, value);
            List<Entry<K, V>> currentList = getBucket(key);
            currentList.add(entry);
            size++;
            return null;
        } else {
            Entry<K, V> entry = getEntry(key);
            assert entry != null;
            V resetValue = entry.value;
            entry.value = value;
            return resetValue;
        }
    }

    @Override
    public V get(K key) {
        if (!containsKey(key)) {
            return null;
        }
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

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        int bucketIndex = 0;
        while (bucketIndex < buckets.length) {
            List<Entry<K, V>> entryList = getBucket(bucketIndex);
            for (Entry<K, V> entry : entryList) {
                builder.append("{").append(entry.key).append(" = ").append(entry.value).append("}");
                builder.append("\n");
            }
            bucketIndex++;
        }
        return builder.toString();
    }

    @Override
    public String toString(K key) {
        return (String) get(key);
    }

    public Iterator<Entry<K, V>> iterator() {
        return new MyIterator();
    }

    private class MyIterator implements Iterator<Entry<K, V>> {
        private final List<Entry<K, V>> list = convertMapContentInList();
        int entryPosition = 0;
        private boolean canRemove = false;

        @Override
        public boolean hasNext() {
            if (entryPosition == list.size()) {
                return false;
            }
            return size != 0;
        }

        @Override
        public Entry<K, V> next() {
            if (entryPosition == list.size()) {
                throw new NoSuchElementException("Map is empty.");
            }
            entryPosition++;
            canRemove = true;
            return list.get(entryPosition - 1);
        }

        @Override
        public void remove() {
            if (!canRemove) {
                throw new NoSuchElementException("Call next(), first.");
            }
            canRemove = false;
            list.remove(entryPosition - 1);
            entryPosition--;
            size--;
        }

        private List<Entry<K, V>> convertMapContentInList() {
            List<Entry<K, V>> mapInList = new ArrayList<>();
            for (List<Entry<K, V>> bucket : buckets) {
                if (bucket.size() > 0) {
                    for (Entry<K, V> kvEntry : bucket) {
                        if (kvEntry != null) {
                            mapInList.add(kvEntry);
                        }
                    }
                }
            }
            return mapInList;
        }
    }

    private void growIfNeeded() {
        if ((buckets.length * loadFactor) <= size) {
            List<Entry<K, V>>[] tempBucketsList = new List[(int) Math.floor(buckets.length * growFactor) + 1];
            System.arraycopy(buckets, 0, tempBucketsList, 0, buckets.length);
            buckets = tempBucketsList;
        }
    }

    private void initializeBuckets() {
        for (int i = 0; i < buckets.length; i++) {
            buckets[i] = new ArrayList<>();
        }
    }

    private List<Entry<K, V>> getBucket(K key) {
        return buckets[getIndex(key)];
    }

    private List<Entry<K, V>> getBucket(int index) {
        return buckets[index];
    }

    private Entry<K, V> getEntry(K key) {
        if (size > 0) {
            List<Entry<K, V>> currentList = getBucket(key);
            for (Entry<K, V> entry : currentList) {
                if (Objects.equals(entry.key, key)) {
                    return entry;
                }
            }
        }
        return null;
    }

    private int getIndex(K key) {
        int hashCode = 0;
        if (key != null) {
            hashCode = key.hashCode();
        }
        if (hashCode == Integer.MIN_VALUE) {
            hashCode = hashCode * (-1) - 1;
        }
        return Math.abs(hashCode) % buckets.length;
    }

    private V getValue(K key) {
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

    private String errorCapacityMessage(int initCapacity) {
        return String.format("Error, wrong initial capacity: %d." +
                "\nYou can't input value less than \"0\".", initCapacity);
    }

    private String errorFactorMessage(int growFactor, double loadFactor) {
        return String.format("""
                Error, wrong:
                 grow factor - %d || load factor - %,.1f
                You can't input value less than "1".""", growFactor, loadFactor);
    }

    static class Entry<K, V> {
        private final K key;
        private V value;

        private Entry(K key, V value) {
            this.key = key;
            this.value = value;
        }

        public V getValue() {
            return value;
        }

        public K getKey() {
            return key;
        }
    }
}




