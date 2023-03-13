package guchi.the.hasky.datastructures.map;


import java.util.*;

public class HashMap<K, V> implements Map<K, V> {
    public static final int DEFAULT_CAPACITY = 5;
    private List<Enty<K, V>>[] buckets;
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
    public V put(K key, V value) { // має повернути попереднє значення
        int bucketIndex = getIndex(key);
        if (!containsKey(key)) {
            Enty<K, V> node = new Enty<>(key, value);
            List<Enty<K, V>> nodesList = buckets[bucketIndex];
            nodesList.add(node);
            size++;
            return node.value;
        }
        return null;
    }

    @Override
    public V get(K key) {
        int bucketIndex = getIndex(key);
        if (containsKey(key)) {
            List<Enty<K, V>> currentList = buckets[bucketIndex];
            for (Enty<K, V> node : currentList) {
                if (node.key.equals(key)) {
                    return node.value;
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
        }
        List<Enty<K, V>> currentList = buckets[bucketIndex];
        for (int i = 0; i < currentList.size(); i++) {
            if (currentList.get(i).key.equals(key)) {
                return true;
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
        }
        List<Enty<K, V>> currentList = buckets[bucketIndex];

        for (Enty<K, V> entry : currentList) {
            if (Objects.equals(entry.key, key)) {
                V removedEntry = entry.value;
                currentList.remove(entry);
                size--;
                return removedEntry;
            }
        }
        return null;
    }

    @Override
    public int size() {
        return size;
    }

    public Iterator<Enty<K, V>> iterator() {
        return new Iterator() {

            private int bucketIndex;
            private int entryIndex;
            private Iterator<Enty<K, V>> bucketIterator;

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
            public Enty<K, V> next() {
                while (bucketIndex < DEFAULT_CAPACITY) {
                    for (int i = 0; i < buckets[bucketIndex].size(); i++) {
                        if (buckets[bucketIndex].get(entryIndex) != null) {
                            return buckets[bucketIndex].get(entryIndex);
                        }
                        entryIndex++;
                    }
                    bucketIndex++;
                }
                return null;
            }

            @Override
            public void remove() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                buckets[bucketIndex].remove(entryIndex);
            }
        };
    }

    private List<Enty<K, V>> getBucket(K key) { // треба заюзати
        return buckets[getIndex(key)];
    }

    private int getIndex(K key) {
        return key.hashCode() % buckets.length;
    }

    private void initializeBuckets() { // add method
        for (int i = 0; i < buckets.length; i++) {
            buckets[i] = new ArrayList<>();
        }
    }

    private boolean validateIndex(int index) {
        return index >= 0 && index < DEFAULT_CAPACITY;
    }

    private String errorPutIndex(int index) {
        return String.format("Index %d, can't be less than: 0 & more than: %d.", index, DEFAULT_CAPACITY - 1);
    }


    private static class Enty<K, V> {
        private K key;
        private V value;

        private Enty(K key, V value) {  // <- change modificator
            this.key = key;
            this.value = value;
        }
    }


}
