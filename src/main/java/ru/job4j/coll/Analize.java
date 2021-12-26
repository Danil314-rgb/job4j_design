package ru.job4j.coll;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Analize {

    public static Info diff(Set<User> previous, Set<User> current) {
        Map<Integer, String> map = new HashMap<>();
        for (User prev : previous) {
            map.put(prev.getId(), prev.getName());
        }
        int added = added(previous, current);
        int changed = changed(previous, current);
        int deleted = deleted(previous, current);

        return new Info(added, changed, deleted);
    }

    public static int added(Set<User> previous, Set<User> current) {
        int result = 0;
        Map<Integer, String> map = new HashMap<>();

        for (User prev : previous) {
            map.put(prev.getId(), prev.getName());
        }
        for (User cur : current) {
            if (!map.containsKey(cur.getId())) {
                result++;
            }
        }
        return result;
    }

    public static int deleted(Set<User> previous, Set<User> current) {
        int result = 0;
        Map<Integer, String> map = new HashMap<>();
        for (User prev : previous) {
            map.put(prev.getId(), prev.getName());
        }

        for (Map.Entry<Integer, String> entry : map.entrySet()) {
            int key = entry.getKey();
            String value = entry.getValue();

            for (User cur : current) {
                if (key == cur.getId() && !value.equals(cur.getName())) {
                    result++;
                }
            }
        }
        return result;
    }

    public static int changed(Set<User> previous, Set<User> current) {
        int result = 0;
        Map<Integer, String> map = new HashMap<>();

        for (User prev : previous) {
            map.put(prev.getId(), prev.getName());
        }


        for (Map.Entry<Integer, String> entry : map.entrySet()) {
            int key = entry.getKey();
            String value = entry.getValue();

            for (User cur : current) {
                if (key == cur.getId() && !value.equals(cur.getName())) {
                    result++;
                }
            }
        }

        return result;
    }

    public static void main(String[] args) {
        User u1 = new User(1, "A");
        User u2 = new User(2, "B");
        User u3 = new User(3, "C");
        User u5 = new User(5, "C");
        Set<User> previous = Set.of(u1, u2, u3);
        Set<User> current = Set.of(u1, u2, u3);
        System.out.println(added(previous, current)); //0
        System.out.println(changed(previous, current)); //1
        System.out.println(deleted(previous, current)); // 0
    }
}
