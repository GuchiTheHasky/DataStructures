package guchi.the.hasky.datastructures.map;


import java.util.ArrayList;
import java.util.List;

public class HashMap<K, V> implements Map<K, V> {

    public static final int DEFAULT_CAPACITY = 16;
    private ArrayList<Entry<K,V>>[] buckets;

    // array length = 5
    private int size;
    private Entry<K, V>[] entries = new Entry[DEFAULT_CAPACITY];

    public HashMap() {
    }


    @Override
    public V put(K key, V value) {
        int bucketIndex = getIndex(key);
        ArrayList<Entry<K,V>> entryList = buckets[bucketIndex];
        for (Entry<K,V> entry : entryList){
                if (entry.key.equals(key)) {
                    entry.value = value;
                    return null;
                }
        }
        entryList.add(new Entry<>(key, value));
        return null;
    }

    private int getIndex(K key) {
        return 0;
    }

    /*

        //old version put()
//        if (!isKeyExist(key)) {
//            rise();
//            Entry<K, V> entry = new Entry(key, value);
//            entries[size] = entry;
//            size++;
//            sortEntryMap();
//            return (V) entry;
//        }
//        return null;*/
    @Override
    public V get(K key) { // key == array[index]  ->  key.hashCode() % array.length == index
        int bucketIndex = getIndex(key);
        List<Entry<K,V>> entryList = buckets[bucketIndex];
        for (Entry<K,V> entry : entryList){
            if (entry.key.equals(key)) {
                return entry.value;
            }
        }
        return null;
    }

    /* old get()
    * //        for (int i = 0; i < size; i++) {
//            if (entries[i].key.equals(key)) {
//                return entries[i].value;
//            }
//        }
//        return null;*/
    @Override
    public boolean containsKey(K key) {
        for (int i = 0; i < size; i++) {
            if (entries[i].key.equals(key)) {
                return true;
            }
        }
        return false;
    }
    @SuppressWarnings("unchecked")
    @Override
    public V remove(K key) {
        for (int i = 0; i < size; i++) {
            if (entries[i].key.equals(key)) {
                Entry<K, V> deletedEntry = entries[i];
                Entry<K, V>[] tempEntries = (Entry<K, V>[]) new Entry[size - 1];
                int count = 0;
                for (int j = 0; j < size; j++) {
                    if (j != i) {
                        tempEntries[count] = entries[j];
                        count++;
                    }
                }
                size--;
                entries = tempEntries;
                return deletedEntry.value;
            }
        }
        return null;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void printValues() {
            for (Entry<K, V> entry : entries) {
                System.out.print(entry.value + " ");
        }
    }


    @SuppressWarnings("unchecked")
    private void rise() {
        if (size == DEFAULT_CAPACITY) {
            Entry<K, V>[] tempEntries = (Entry<K, V>[]) new Object[size * 2];
            System.arraycopy(entries, 0, tempEntries, 0, size);
            entries = tempEntries;
        }
    }

    private void sortEntryMap() {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (getCharAtValueInt(entries[i].key) < getCharAtValueInt(entries[j].key)) {
                    Entry<K, V> temp = entries[i];
                    entries[i] = entries[j];
                    entries[j] = temp;
                }
            }
        }
    }

    private int getCharAtValueInt(K key) {
        String str = key.toString();
        return str.charAt(0);
    }

    private boolean isKeyExist(K key) {
        for (int i = 0; i < size; i++) {
            if (entries[i].key.equals(key)) {
                return true;
            }
        }
        return false;
    }

    static class Entry<K, V> {
        private K key;
        private V value;

        public Entry(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }
}

