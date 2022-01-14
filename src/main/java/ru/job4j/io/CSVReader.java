package ru.job4j.io;

import java.io.*;
import java.nio.file.Files;
import java.util.*;

public class CSVReader {

    public static void main(String[] args) throws Exception {
        /*File file = new File("./resources/source.csv");
        File target = new File("./resources/target.csv");*/

        File fileArgs = new File(args[0]);
        File targetArgs = new File(args[1]);
        if (args.length != 2) {
            throw new IllegalArgumentException(
                    "Ошибка. Нужно передать ровно два параметра!");
        }
        String data = String.join(
                System.lineSeparator(),
                "name;age;last_name;education",
                "Tom;20;Smith;Bachelor",
                "Jack;25;Johnson;Undergraduate",
                "William;30;Brown;Secondary special"
        );
        Files.writeString(fileArgs.toPath(), data);
        ArgsName argsName = ArgsName.of(new String[]{
                "-path=" + fileArgs.getAbsolutePath(), "-delimiter=;",
                "-out=" + targetArgs.getAbsolutePath(), "-filter=name,age"
        });
        handle(argsName, targetArgs.getAbsolutePath(), fileArgs.getAbsolutePath());
    }

    public static void handle(ArgsName argsName, String target, String file) throws Exception {
        Scanner scanner = new Scanner(new FileReader(file));
        try (PrintWriter out = new PrintWriter(
                new BufferedOutputStream(
                        new FileOutputStream(target)
                ))) {
            while (scanner.hasNext()) {
                StringBuilder res = new StringBuilder();
                String text = scanner.nextLine();
                String[] split = text.split(argsName.get("delimiter"));
                res.append(split[0]);
                res.append(";");
                res.append(split[1]);
                out.println(res);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
