package ru.job4j.io;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DuplicateVisitor extends SimpleFileVisitor<Path> {

    private Map<FileProperty, List<Path>> map = new HashMap<>();

    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
        FileProperty fileProperty = new FileProperty(attrs.size(), file.toFile().getName());
        if (!map.containsKey(fileProperty)) {
            List<Path> list = new ArrayList<>();
            list.add(file.toAbsolutePath());
            map.put(fileProperty, list);
        } else {
            var value = map.get(fileProperty);
            value.add(file.toAbsolutePath());
        }
        return FileVisitResult.CONTINUE;
    }

    public Map<FileProperty, List<Path>> getMap() {
        return map;
    }
}
