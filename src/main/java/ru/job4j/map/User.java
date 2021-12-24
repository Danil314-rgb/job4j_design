package ru.job4j.map;

import java.util.*;

public class User {

    private String name;
    private int children;
    private Calendar birthday;

    public User(String name, int children, Calendar birthday) {
        this.name = name;
        this.children = children;
        this.birthday = birthday;
    }

    public static void main(String[] args) {

        System.out.println();

        /*Calendar birth = new GregorianCalendar(1980, 1, 25);

        User userOne = new User("Tom", 1, birth);
        User userTwo = new User("Tom", 1, birth);

        Map<User, Object> map = new HashMap<>();
        map.put(userOne, new Object());
        map.put(userTwo, new Object());*/

        /*for (Map.Entry<User, Object> entry : map.entrySet()) {
            User key = entry.getKey();
            Object value = entry.getValue();
            System.out.println(key + " = " + value);
            System.out.println("hashCode = " + key.hashCode());
        }*/
        /*System.out.println("KING".hashCode());
        System.out.println(hash("KING"));
        int i = (33 + 111) & 34;
        System.out.println(i);
        int a = (10 - 2) & 32;
        System.out.println(a);*/

    }

    /*static final int hash(Object key) {
        int h;
        return (key == null) ? 0 : (h = key.hashCode()) ^ (h >>> 16);
    }*/

    @Override
    public String toString() {
        return "User{" + "name='" + name + '\'' + ", children=" + children + ", birthday=" + birthday + '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        User user = (User) o;
        return children == user.children && Objects.equals(name, user.name) && Objects.equals(birthday, user.birthday);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, children, birthday);
    }
}

