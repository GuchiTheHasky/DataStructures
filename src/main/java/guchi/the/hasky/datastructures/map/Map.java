package guchi.the.hasky.datastructures.map;

public interface Map <K, V> {
        V put(K key, V value);

        V get(K key);

        int size();

        boolean isEmpty();

        V remove(K key);

        boolean containsKey(K key);
        void printValues();

}
