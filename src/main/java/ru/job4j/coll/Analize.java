package ru.job4j.coll;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

public class Analize {

    public static Info diff(Set<User> previous, Set<User> current) {
        Map<Integer, String> map = new HashMap<>();
        for (var item : previous) {
            map.put(item.getId(), item.getName());
        }

        int added = 0;
        int changed = 0;
        int deleted = 0;

        for (var item : current) {
            String value = map.get(item.getId());
            String ret =  map.put(item.getId(), item.getName());
            if (ret == null) {
                added++;
            } else if (!Objects.equals(map.get(item.getId()), value)) {
                changed++;
            }
        }
        deleted = previous.size() - current.size() + added;
        return new Info(added, changed, deleted);
    }
}
