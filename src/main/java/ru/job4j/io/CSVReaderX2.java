package ru.job4j.io;

import javax.swing.*;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class CSVReaderX2 {

    public static void main(String[] args) throws Exception {
        /*
        -path=./resources/source.csv
        -delimiter=";"
        -out=./resources/target.csv
        -filter="age,name"
        */

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
        ArgsName argsFile = ArgsName.of(new String[]{args[0]});
        Files.writeString(Paths.get(argsFile.get("path")), data);
        ArgsName argsName = ArgsName.of(new String[]{
                args[0],
                args[1],
                args[2],
                args[3]
        });
        handle(argsName);
    }

    public static void handle(ArgsName argsName) throws Exception {
        Map<String, ArrayList<String>> map = new HashMap<>();
        Map<Integer, String> heads = new HashMap<>();
        Scanner scanner = new Scanner(new FileReader(argsName.get("path")));
        try (PrintWriter out = new PrintWriter(
                new BufferedOutputStream(
                        new FileOutputStream(argsName.get("out"))
                ))) {
            String oneSrt = scanner.nextLine();
            String[] splitOneStr = oneSrt.split(argsName.get("delimiter"));
            int index = 0;
            for (var item : splitOneStr) {
                heads.put(index, item);
                map.put(item, new ArrayList<>());
                index++;
            }

            while (scanner.hasNext()) {
                String text = scanner.nextLine();
                String[] split = text.split(argsName.get("delimiter"));
                for (int i = 0; i < split.length; i++) {
                    map.get(heads.get(i)).add(split[i]);
                }
            }
            String[] filterArgs = argsName.get("filter").split(",");

            StringBuilder res = new StringBuilder();
            StringBuilder arg = new StringBuilder();
            for (String filer : filterArgs) {
                for (var key : map.keySet()) {
                    if (key.equals(filer)) {
                        res.append(key);
                        res.append(";");
                        for (var item : map.get(key)) {
                            arg.append(item);
                            arg.append(";");
                        }
                    }
                }
            }
            out.println(res);
            out.println(arg);
            System.out.println(res);
            System.out.println(arg);



        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

