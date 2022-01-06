package ru.job4j.io;

import java.io.*;

public class Analizy {

    public static void unavailable(String source, String target) {
        try (BufferedReader in = new BufferedReader(new FileReader(source))) {
            try (PrintWriter out = new PrintWriter(target)) {
                byte isWork = 0;
                StringBuilder str = new StringBuilder();
                while (in.ready()) {
                    String[] split = in.readLine().split(" ");
                    if (isWork == 0 && split[0].equals("400") || isWork == 0 && split[0].equals("500")) {
                        str.append(split[1]);
                        isWork++;
                    } else if (isWork != 0 && split[0].equals("200") || isWork != 0 && split[0].equals("300")) {
                        str.append(";");
                        str.append(split[1]);
                        isWork = 0;
                        out.println(str);
                        str = new StringBuilder();
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        unavailable("server.txt", "unavailable.csv");
    }
}
