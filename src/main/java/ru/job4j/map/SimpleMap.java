package ru.job4j.map;

import java.util.Iterator;

public class SimpleMap<K, V> implements Map<K, V> {

    private static final float LOAD_FACTOR = 0.75f;
    private int capacity = 8;
    private int count = 0;
    private int modCount = 0;

    private MapEntry<K, V>[] table = new MapEntry[capacity];

    @Override
    public boolean put(K key, V value) {
        int hashCode = hash(key);
        int index = indexFor(hashCode);
        expand();
        if (checkKey(key) || table[index] != null) {
            return false;
        }
        table[index] = new MapEntry<>(key, value);
        count++;
        return true;
    }

    public void addInNewTableOldValue() {
        SimpleMap<K, V> newTable = new SimpleMap<>();
        for (MapEntry<K, V> entry : table) {
            K tableKey = entry.key;
            V tableValue = entry.value;
            newTable.put(tableKey, tableValue);
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

    private void expand() {
        if (LOAD_FACTOR <= count / capacity) {
            capacity *= 2;
            addInNewTableOldValue();
        }
    }

    private int hash(K key) {
        return key == null ? 0 : key.hashCode();
    }

    private int indexFor(int hash) {
        int index = Math.abs(hash) % capacity;
        return index;
    }

    @Override
    public V get(K key) {
        for (MapEntry<K, V> entry : table) {
            K tableKey = entry.key;
            V tableValue = entry.value;
            if (tableKey.equals(key)) {
                return tableValue;
            }
        }
        return null;
    }

    @Override
    public boolean remove(K key) {
        return false;
    }

    @Override
    public Iterator<K> iterator() {
        return null;
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
