package ru.job4j.io.kontrol;

import java.io.*;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Find  {
    /*-d=. -n=lol.txt -t=name -o=./resources/result.txt*/

    public static void main(String[] args) throws Exception {
        if (args.length != 4) {
            throw new IllegalArgumentException("Ошибка. Нужно передать ровно четыре параметра!");
        }
        ArgsNames argsNames = ArgsNames.of(args);
        Path start = Paths.get(argsNames.get("d"));
        String fullName = argsNames.get("n");
        Path resultTxt = Paths.get(argsNames.get("o"));
        List<Path> result = new ArrayList<>();
        if (argsNames.get("t").equals("name")) {
            result = search(start, p -> p.toFile()
                    .getName()
                    .equals(fullName));
        } else if (argsNames.get("t").equals("mask")) {
            /*fullName = .*.txt сейчас*/
            Pattern pattern = Pattern.compile("\\w*");
            result = search(start, p -> p.toFile()
                    .getName()
                    .equals(pattern.pattern()));
            Matcher matcher = pattern.matcher(result.toString());
            while (matcher.find()) {
                result.forEach(System.out::println);
            }

        }

        for (var item : result) {
            System.out.println(item);
        }
        /*writeToTxt(result, resultTxt);*/
    }

    public static List<Path> search(Path root, Predicate<Path> condition) throws IOException {
        SearchFiles searcher = new SearchFiles(condition);
        Files.walkFileTree(root, searcher);
        return searcher.getPaths();
    }

    public static void writeToTxt(List<Path> result, Path target) throws Exception {
        try (PrintWriter out = new PrintWriter(
                new BufferedOutputStream(
                        new FileOutputStream(target.toFile())))) {
            for (var item : result) {
                out.write(item.toString());
                out.write(System.lineSeparator());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}


