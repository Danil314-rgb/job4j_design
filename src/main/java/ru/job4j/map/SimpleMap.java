package ru.job4j.map;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class SimpleMap<K, V> implements Map<K, V> {

    private static final float LOAD_FACTOR = 0.75f;
    private int capacity = 8;
    private int count = 0;
    private int modCount = 0;

    private MapEntry<K, V>[] table = new MapEntry[capacity];

    @Override
    public boolean put(K key, V value) {
        expand();
        int hash = hash(key.hashCode());
        int index = indexFor(hash);
        boolean result = false;
        if (table[index] == null) {
            table[index] = new MapEntry<>(key, value);
            count++;
            modCount++;
            result = true;
        }
        return result;
    }

    private void expand() {
        if (LOAD_FACTOR <= (float) count / capacity) {
            capacity *= 2;
            table = new MapEntry[capacity];
            modCount++;
            for (MapEntry<K, V> entry : table) {
                if (entry == null) {
                    continue;
                }
                K tableKey = entry.key;
                V tableValue = entry.value;
                int hash = hash(tableKey.hashCode());
                int newIndex = indexFor(hash);
                table[newIndex] = new MapEntry<>(tableKey, tableValue);
            }
        }
    }

    private int hash(int hashCode) {
        return hashCode % capacity;
    }

    private int indexFor(int hash) {
        return Math.abs(hash) % capacity;
    }

    @Override
    public V get(K key) {
        int hashCodeKey = hash(key.hashCode());
        int indexKey = indexFor(hashCodeKey);
        V valueMap = null;
        if (table[indexKey] != null && table[indexKey].key.equals(key)) {
            valueMap = table[indexKey].value;
        }
        return valueMap;
    }

    @Override
    public boolean remove(K key) {
        boolean result = false;
        int hashCodeKey = hash(key.hashCode());
        int indexKey = indexFor(hashCodeKey);
        if (table[indexKey] != null && table[indexKey].key.equals(key)) {
            table[indexKey] = null;
            count--;
            modCount++;
            result = true;
        }
        return result;
    }

    @Override
    public Iterator<K> iterator() {
        int expectedModCount = modCount;

        return new Iterator<K>() {
            int index = 0;

            @Override
            public boolean hasNext() {
                if (expectedModCount != count) {
                    throw new ConcurrentModificationException();
                }
                while (index < table.length && table[index] == null) {
                    index++;
                }
                return index < table.length && table[index] != null;
            }

            @Override
            public K next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                K result = null;
                if (table[index] != null) {
                    result = table[index].key;
                }
                index++;
                return result;
            }
        };
    }

    private static class MapEntry<K, V> {

        K key;
        V value;

        public MapEntry(K key, V value) {
            this.key = key;
            this.value = value;
        }

    }
}
