package ru.job4j.io;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Map;

public class DuplicatesFinder {

    public static void main(String[] args) throws IOException {

        DuplicateVisitor duplicateVisitor = new DuplicateVisitor();
        Files.walkFileTree(Path.of("./"), duplicateVisitor);
        Map<FileProperty, List<Path>> map = duplicateVisitor.getMap();
        for (Map.Entry<FileProperty, List<Path>> entry : map.entrySet()) {
            var key = entry.getKey();
            var value = entry.getValue();
            if (value.size() > 1) {
                for (var item : value) {
                    System.out.println(key.getName() + "-------->" + item);
                }
            }
        }
    }
}
