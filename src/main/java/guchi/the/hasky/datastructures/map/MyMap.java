package guchi.the.hasky.datastructures.map;


import java.util.ArrayList;
import java.util.List;

public class MyMap<K, V> implements Map<K, V> {
    public static final int DEFAULT_CAPACITY = 5;
    private List<Entry<K, V>>[] buckets;
    private int size;

    public MyMap() {
        this(DEFAULT_CAPACITY);
        this.buckets[0] = new ArrayList<>(16);
        this.buckets[1] = new ArrayList<>(16);
        this.buckets[2] = new ArrayList<>(16);
        this.buckets[3] = new ArrayList<>(16);
        this.buckets[4] = new ArrayList<>(16);
    }

    @SuppressWarnings("unchecked")
    public MyMap(int initCapacity) {
        this.buckets = new ArrayList[initCapacity];
    }


    @Override
    public V put(K key, V value) {
        int bucketIndex = getIndex(key);
        if (!validateIndex(bucketIndex)) {
            throw new IndexOutOfBoundsException(errorPutIndex(bucketIndex));
        } else if (!containsKey(key)) {
            Entry<K, V> entry = new Entry<>(key, value);
            List<Entry<K, V>> entriesList = buckets[bucketIndex];
            entriesList.add(entry);
            size++;
            return entry.value;
        }
        return null;
    }

    @Override
    public V get(K key) {
        int bucketIndex = getIndex(key);
        if (!validateIndex(bucketIndex)) {
            throw new IndexOutOfBoundsException(errorPutIndex(bucketIndex));
        } else  {
            List<Entry<K, V>> currentList = buckets[bucketIndex];
            for (Entry<K, V> entry : currentList) {
                if (entry.key.equals(key)) {
                    return entry.value;
                }
            }
        }
        return null;
    }

    @Override
    public boolean containsKey(K key) {
        int bucketIndex = getIndex(key);
        if (!validateIndex(bucketIndex)) {
            throw new IndexOutOfBoundsException(errorPutIndex(bucketIndex));
        } else {
            List<Entry<K, V>> currentList = buckets[bucketIndex];
            for (int i = 0; i < currentList.size(); i++) {
                if (currentList.get(i).key.equals(key)) {
                    return true;
                }
            }
        }
        return false;
    }

    @SuppressWarnings("uncheked")
    @Override
    public V remove(K key) {
        int bucketIndex = getIndex(key);
        if (!validateIndex(bucketIndex)) {
            throw new IndexOutOfBoundsException(errorPutIndex(bucketIndex));
        } else {
            List<Entry<K, V>> currentList = buckets[bucketIndex];
            Entry<K, V> entryToRemove = (Entry<K, V>) get(key);
            currentList.remove(get(key));
            size--;
            return entryToRemove.value;
        }
    }

    @Override
    public int size() {
        return size;
    }

    private int getIndex(K key) {
        if (key != null) {
            return key.hashCode()  ;
        }
        return -1;
    }

    private boolean validateIndex(int index) {
        return index >= 0 && index < DEFAULT_CAPACITY;
    }

    private String errorPutIndex(int index) {
        return String.format("Index %d, can't be less than: 0 & more than: %d.", index, DEFAULT_CAPACITY - 1);
    }

    private static class Entry<K, V> {
        private K key;
        private V value;

        public Entry(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }


}
