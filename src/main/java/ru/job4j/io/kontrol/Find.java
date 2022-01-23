package ru.job4j.io.kontrol;

import java.io.*;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.regex.Pattern;

public class Find {
    /*TODO заполнение edit configuration: -d=. -n=lol.txt -t=name -o=./resources/result.txt*/

    public static void main(String[] args) throws Exception {
        Find find = new Find();
        ArgsNames argsNames = ArgsNames.of(args);
        checkArgs(args, argsNames);
        find.writeToTxt(searchAndWriteToList(argsNames, find), Paths.get(argsNames.get("o")));
    }

    public List<Path> search(Path root, Predicate<Path> condition) throws IOException {
        SearchFiles searcher = new SearchFiles(condition);
        Files.walkFileTree(root, searcher);
        return searcher.getPaths();
    }

    public static List<Path> searchAndWriteToList(ArgsNames argsNames, Find find) throws IOException {
        Path start = Paths.get(argsNames.get("d"));
        String fullName = argsNames.get("n");
        List<Path> result = new ArrayList<>();
        if (("name").equals(argsNames.get("t"))) {
            result = find.search(start, p -> p.toFile()
                    .getName()
                    .equals(fullName));
        } else if (("mask").equals(argsNames.get("t"))) {
            result = find.search(start, (f -> (Pattern.compile(fullName.replace("*", ".*")
                    .replace("?", "\\w{1}")))
                    .matcher(f.getFileName().toString()).find()));
        }
        return result;
    }

    public void writeToTxt(List<Path> result, Path target) throws Exception {
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

    public static void checkArgs(String[] args, ArgsNames argsNames) {
        if (args.length != 4) {
            throw new IllegalArgumentException("Ошибка. Нужно передать ровно четыре параметра!");
        }
        File file = new File(argsNames.get("d"));
        if (!file.exists() || !file.isDirectory()) {
            throw new IllegalArgumentException("Файл по переданному пути не существует или не является каталогом. Проверьте путь!");
        }
    }
}


