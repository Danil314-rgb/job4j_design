package ru.job4j.coll;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

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
            int size = map.size();
            String value = map.get(item.getId());
            map.put(item.getId(), item.getName());
            if (size != map.size()) {
                added++;
            } else if (!map.get(item.getId()).equals(value)) {
                changed++;
            }
        }
        deleted = previous.size() - current.size() + added;
        return new Info(added, changed, deleted);
    }
}
