package ru.job4j.io.kontrol;

import java.util.HashMap;
import java.util.Map;

public class ArgsNames {

    private final Map<String, String> values = new HashMap<>();

    public String get(String key) {
        if (values.get(key) == null) {
            throw new IllegalArgumentException("У переданного ключа отсутсвует значение. Проверьте переданный ключ!");
        }
        return values.get(key);
    }

    private Map<String, String> parse(String[] args) {
        if (args.length == 0) {
            throw new IllegalArgumentException("Количество переданных аргументов равно ноль. Передайте аргументы!");
        }
        for (String item : args) {
            validate(item);
            String[] split = item.split("=");
            char[] ch = new char[split[0].length() - 1];
            split[0].getChars(1, split[0].length(), ch, 0);
            values.put(String.valueOf(ch), split[1]);
        }
        return values;
    }

    public static void validate(String str) {
        if (!str.startsWith("-") || !str.contains("=") || str.indexOf("=") != str.lastIndexOf("=")
                || str.endsWith("=") || str.indexOf("=") == str.indexOf("-") + 1) {
            throw new IllegalArgumentException("Несоответствие шаблону -key=value. Проверьте правильность по шаблону!");
        }
    }

    public static ArgsNames of(String[] args) {
        ArgsNames names = new ArgsNames();
        names.parse(args);
        return names;
    }
}
