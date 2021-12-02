package ru.job4j.generics;

import java.util.HashMap;
import java.util.Map;

public class MemStore<T extends Base> implements Store<T> {

    private final Map<String, T> storage = new HashMap<>();

    @Override
    public void add(T model) {
        storage.putIfAbsent(model.getId(), model);
    }

    @Override
    public boolean replace(String id, T model) {
        storage.replace(id, model);
        return (storage.containsKey(id) && storage.containsValue(model));
    }

    @Override
    public boolean delete(String id) {
        storage.remove(id);
        return (!storage.containsKey(id));
    }

    @Override
    public T findById(String id) {
        return storage.get(id);
    }
}
