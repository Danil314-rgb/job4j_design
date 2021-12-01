package ru.job4j.generics;

import java.util.*;

public class GenericUsage {

    public static void main(String[] args) {
        /*List<Person> p = List.of(new Person("name", 21, new Date(913716000000L)));
        new GenericUsage().printInfo(p);

        List<Programmer> prog = List.of(new Programmer("name", 21, new Date(913716000000L)));
        new GenericUsage().printInfo(prog);*/

        List<? super Integer> list = new ArrayList<>();
        new GenericUsage().addAll(list);
    }

    public void printRsl(Collection<?> col) {
        for (Iterator<?> it = col.iterator(); it.hasNext();) {
            Object next = it.next();
            System.out.println(next);
        }
    }

    public void printInfo(Collection<? extends Person> col) {
        for (Iterator<? extends Person> it = col.iterator(); it.hasNext();) {
            Person next = it.next();
            System.out.println(next);
        }
    }

    public void addAll(List<? super Integer> list) {
        for (int i = 1; i <= 5; i++) {
            list.add(i);
        }
        for (Object o : list) {
            System.out.println(o);
        }
    }
}
