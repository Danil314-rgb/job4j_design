package ru.job4j.io;

import java.nio.file.FileVisitResult;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class SearchFile extends SimpleFileVisitor<Path> {

    Predicate<Path> predicate;
    List<Path> list = new ArrayList<>();

    public SearchFile(Predicate<Path> predicate) {
        this.predicate = predicate;
    }

    public List<Path> getPaths() {
        return list;
    }

    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) {
        if (predicate.test(file)) {
            list.add(file);
        }
        return FileVisitResult.CONTINUE;
    }
}
