package ru.job4j.io;

import ru.job4j.linkedlist.List;
import ru.job4j.linkedlist.SimpleLinkedList;

import java.io.BufferedReader;
import java.io.FileReader;

public class LogFilter {

    public static List<String> filter(String file) {
        List<String> list = new SimpleLinkedList<>();
        try (BufferedReader in = new BufferedReader(new FileReader(file))) {
            in.lines().filter(s -> s.contains(" 404 ")).forEach(list::add);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public static void main(String[] args) {
        List<String> log = filter("log.txt");
        log.forEach(System.out::println);
        System.out.println(log);
    }
}
