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
    private Map<FileProperty, Path> map = new HashMap<>();
    private Map<Path, FileProperty> duplicates = new HashMap<>();

    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
        FileProperty fileProperty = new FileProperty(attrs.size(), file.toFile().getName());
        if (map.containsKey(fileProperty)) {
            duplicates.put(file.toAbsolutePath(), fileProperty);
        } else {
            map.put(fileProperty, file.toAbsolutePath());
        }
        return FileVisitResult.CONTINUE;
    }

    public Map<Path, FileProperty> getMap() {
        return duplicates;
    }
}
