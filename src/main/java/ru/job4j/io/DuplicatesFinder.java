package ru.job4j.io;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Map;
import java.util.stream.Stream;

public class DuplicatesFinder {

    public static void main(String[] args) throws IOException {

        DuplicateVisitor duplicateVisitor = new DuplicateVisitor();
        Files.walkFileTree(Path.of("./"), duplicateVisitor);
        Map<Path, FileProperty> map = duplicateVisitor.getMap();
        for (Map.Entry<Path, FileProperty> entry : map.entrySet()) {
            var key = entry.getKey().toAbsolutePath();
            var value = entry.getValue().getName();
            System.out.println(key + "------>" + value);
        }

    }
}
