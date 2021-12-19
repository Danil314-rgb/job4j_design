package ru.job4j.set;

import ru.job4j.list.SimpleArrayList;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

public class SimpleSet<T> implements Set<T> {

    private SimpleArrayList<T> set = new SimpleArrayList<>(0);

    @Override
    public boolean add(T value) {
        for (T item : set) {
            if (Objects.equals(value, item)) {
                return false;
            }
        }
        set.add(value);
        return true;
    }

    @Override
    public boolean contains(T value) {
        boolean result = false;
        for (T item : set) {
            if (Objects.equals(value, item)) {
                result = true;
            }
        }
        return result;
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            int i = 0;

            @Override
            public boolean hasNext() {
                return i < set.size();
            }

            @Override
            public T next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                return set.get(i++);
            }
        };
    }
}
