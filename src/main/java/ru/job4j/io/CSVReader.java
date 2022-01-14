package ru.job4j.io;

import java.io.*;
import java.util.*;

public class CSVReader {

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
