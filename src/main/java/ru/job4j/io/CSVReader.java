package ru.job4j.io;

import java.io.*;
import java.nio.file.Files;
import java.util.*;

public class CSVReader {
    private static File file = new File("./resources/source.csv");

    public static void main(String[] args) throws Exception {
        /*var data = "A 1B FF 110";
        var file = File.createTempFile("data", null);
        try (BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(file))) {
            out.write(data.getBytes());
        }
        try (var scanner = new Scanner(file).useRadix(16)) {
            while (scanner.hasNextInt()) {
                System.out.print(scanner.nextInt());
                System.out.print(" ");
            }
        }*/
       File target = new File("./resources/target.csv");
        String data = String.join(
                System.lineSeparator(),
                "name;age;last_name;education",
                "Tom;20;Smith;Bachelor",
                "Jack;25;Johnson;Undergraduate",
                "William;30;Brown;Secondary special"
        );
        Files.writeString(file.toPath(), data);
        ArgsName argsName = ArgsName.of(new String[] {
                "-path=" + file.getAbsolutePath(), "-delimiter=;",
                "-out=" + target.getAbsolutePath(), "-filter=name,age"
        });
        handle(argsName, target.getAbsolutePath());
    }

    public static void handle(ArgsName argsName, String target) throws Exception {
        Scanner scanner = new Scanner(new FileReader(file));
        try (PrintWriter out = new PrintWriter(
                new BufferedOutputStream(
                        new FileOutputStream(target)

        ))) {
            while (scanner.hasNext()) {
                StringBuilder res = new StringBuilder();
                String text = scanner.nextLine();
                //if (!text.contains("age")) {
                String[] split = text.split(argsName.get("delimiter"));
                res.append(split[0]);
                res.append(";");
                res.append(split[1]);
                //System.out.println(split[0] + " " + split[1]);
                out.println(res);
                //System.out.println(res);
            /*} else {
                String[] split = text.split(argsName.get("delimiter"));
                res.append(split[0]);
                res.append(";");
                res.append(split[1]);
                //System.out.println(split[0] + " " + split[1]);
                System.out.println(res);
            }*/

            }
        } catch (Exception e) {
            e.printStackTrace();
        }


    }
}
