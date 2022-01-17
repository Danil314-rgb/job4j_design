package ru.job4j.io;

import javax.swing.*;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class CSVReaderX2 {

    public static void main(String[] args) throws Exception {

        File fileArgs = new File(args[0]);
        File delimiterArgs = new File(args[1]);
        File targetArgs = new File(args[2]);
        File filterArgs = new File(args[3]);

        if (args.length != 4) {
            throw new IllegalArgumentException(
                    "Ошибка. Нужно передать ровно четыре параметра!");
        }
        String data = String.join(
                System.lineSeparator(),
                "name;age;last_name;education",
                "Tom;20;Smith;Bachelor",
                "Jack;25;Johnson;Undergraduate",
                "William;30;Brown;Secondary special"
        );
        ArgsName argsFile = ArgsName.of(new String[]{fileArgs.toString()});
        Files.writeString(Paths.get(argsFile.get("path")), data);
        ArgsName argsName = ArgsName.of(new String[]{
                fileArgs.toString(),
                delimiterArgs.toString(),
                targetArgs.toString(),
                filterArgs.toString()
        });
        handle(argsName);
    }

    public static void handle(ArgsName argsName) throws Exception {
        Scanner scanner = new Scanner(new FileReader(argsName.get("path")));
        try (PrintWriter out = new PrintWriter(
                new BufferedOutputStream(
                        new FileOutputStream(argsName.get("out"))
                ))) {
            while (scanner.hasNext()) {
                StringBuilder res = new StringBuilder();
                String text = scanner.nextLine();
                String[] split = text.split(argsName.get("delimiter"));
                res.append(split[0]);
                res.append(";");
                res.append(split[1]);
                if (!argsName.get("out").equals("stdout")) {
                    out.println(res);
                } else {
                    System.out.println(res);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
