package ru.job4j.io;

import java.util.HashMap;
import java.util.Map;

public class ArgsName {

    private final Map<String, String> values = new HashMap<>();

    public String get(String key) {
        return values.get(key);
    }

    private Map<String, String> parse(String[] args) {
        if (args.length == 0) {
            throw new IllegalArgumentException();
        }
        for (String item : args) {
            String[] split = item.split("=");
            if (split.length < 2) {
                throw new IllegalArgumentException();
            }
            char[] ch = new char[split[0].length() - 1];
            split[0].getChars(1, split[0].length(), ch, 0);
            values.put(String.valueOf(ch), split[1]);
        }
        return values;
    }

    public static ArgsName of(String[] args) {
        ArgsName names = new ArgsName();
        names.parse(args);
        return names;
    }

    public static void main(String[] args) {
        ArgsName jvm = ArgsName.of(new String[] {"-Xmx=512", "-encoding=UTF-8"});
        System.out.println(jvm.get("Xmx"));

        ArgsName zip = ArgsName.of(new String[] {"-out=project.zip", "-encoding=UTF-8"});
        System.out.println(zip.get("out"));
    }
}
