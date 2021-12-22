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
        int hashCode = hash(key);
        int index = indexFor(hashCode);
        boolean result = checkKey(key);
        if (!checkKey(key) && table[index] == null) {
            table[index] = new MapEntry<>(key, value);
            count++;
            result = true;
        }
        return result;
    }

    private void expand() {
        if (LOAD_FACTOR <= (float) count / capacity) {
            capacity *= 2;
            addInNewTableOldValue();
        }
    }

    public void addInNewTableOldValue() {
        table = new MapEntry[capacity];
        for (MapEntry<K, V> entry : table) {
            if (entry == null) {
                continue;
            }
            K tableKey = entry.key;
            V tableValue = entry.value;
            int hashCode = hash(tableKey);
            int newIndex = indexFor(hashCode);
            table[newIndex] = new MapEntry<>(tableKey, tableValue);
        }
    }

    public void addInNewTableOldValue(K key) {
        table = new MapEntry[capacity];
        for (MapEntry<K, V> entry : table) {
            if (entry == null || entry.key.equals(key)) {
                continue;
            }
            K tableKey = entry.key;
            V tableValue = entry.value;
            int hashCode = hash(tableKey);
            int newIndex = indexFor(hashCode);
            table[newIndex] = new MapEntry<>(tableKey, tableValue);
        }
    }

    private boolean checkKey(K key) {
        boolean result = false;
        for (MapEntry<K, V> entry : table) {
            if (entry == null) {
                continue;
            }
            K tableKey = entry.key;
            if (tableKey.equals(key)) {
                result = true;
                break;
            }
        }
        return result;
    }

    private int hash(K key) {
        return key == null ? 0 : key.hashCode();
    }

    private int indexFor(int hash) {
        return Math.abs(hash) % capacity;
    }

    @Override
    public V get(K key) {
        V result = null;
        for (MapEntry<K, V> entry : table) {
            if (entry == null) {
                continue;
            }
            K tableKey = entry.key;
            V tableValue = entry.value;
            if (tableKey.equals(key)) {
                result = tableValue;
                break;
            }
        }
        return result;
    }

    @Override
    public boolean remove(K key) {
        boolean result = false;
        for (MapEntry<K, V> entry : table) {
            if (entry == null) {
                continue;
            }
            K tableKey = entry.key;
            if (tableKey.equals(key)) {
                addInNewTableOldValue(key);
                result = true;
                break;
            }
        }
        return result;
    }

    @Override
    public Iterator<K> iterator() {
        modCount = count;

        return new Iterator<K>() {
            int i = 0;
            @Override
            public boolean hasNext() {
                if (modCount != count) {
                    throw new ConcurrentModificationException();
                }
                return count < capacity;
            }

            @Override
            public K next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                K result = null;
                if (table[i] != null) {
                    result = table[i].key;
                }
                i++;
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
