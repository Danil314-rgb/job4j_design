package ru.job4j.io;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class DuplicatesFinder {

    public static void main(String[] args) throws IOException {

        DuplicateVisitor duplicateVisitor = new DuplicateVisitor();
        Files.walkFileTree(Path.of("./"), duplicateVisitor);
        duplicateVisitor.print(duplicateVisitor.getMap());
    }


}
