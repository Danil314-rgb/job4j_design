package ru.job4j.io;

import ru.job4j.io.kontrol.ArgsNames;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class CSVReaderX2 {

    public static void main(String[] args) throws Exception {
        /*TODO заполнение edit configuration: -path=./resources/source.csv -delimiter=";" -out=./resources/target.csv -filter="age,name"*/

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
        ArgsNames argsFile = ArgsNames.of(new String[]{args[0]});
        Files.writeString(Paths.get(argsFile.get("path")), data);
        ArgsNames argsNames = ArgsNames.of(new String[]{
                args[0],
                args[1],
                args[2],
                args[3]
        });
        handle(argsNames);
    }

    public static void handle(ArgsNames argsNames) throws Exception {
        Map<String, ArrayList<String>> map = new HashMap<>();
        Map<Integer, String> heads = new HashMap<>();
        Scanner scanner = new Scanner(new FileReader(argsNames.get("path")));
        try (PrintWriter out = new PrintWriter(
                new BufferedOutputStream(
                        new FileOutputStream(argsNames.get("out"))
                ))) {
            String oneSrt = scanner.nextLine();
            String[] splitOneStr = oneSrt.split(argsNames.get("delimiter"));
            int index = 0;
            for (var item : splitOneStr) {
                heads.put(index, item);
                map.put(item, new ArrayList<>());
                index++;
            }
            while (scanner.hasNext()) {
                String text = scanner.nextLine();
                String[] split = text.split(argsNames.get("delimiter"));
                for (int i = 0; i < split.length; i++) {
                    map.get(heads.get(i)).add(split[i]);
                }
            }
            String[] filterArgs = argsNames.get("filter").split(",");
            StringBuilder res = new StringBuilder();
            for (var key : filterArgs) {
                res.append(key + ";");
            }
            res.append(System.lineSeparator());
            for (int i = 0; i < map.get(filterArgs[0]).size(); i++) {
                for (var key : filterArgs) {
                    res.append(map.get(key).get(i) + ";");
                }
                res.append(System.lineSeparator());
            }
            if (!"stdout".equals(argsNames.get("out"))) {
                out.println(res);
            } else {
                System.out.println(res);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

