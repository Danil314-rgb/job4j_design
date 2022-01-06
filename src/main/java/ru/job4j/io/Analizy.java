package ru.job4j.io;

import java.io.*;

public class Analizy {

    private StringBuilder str = new StringBuilder();

    public void unavailable(String source) {
        try (BufferedReader in = new BufferedReader(new FileReader(source))) {

                byte isWork = 0;
                while (in.ready()) {
                    String[] split = in.readLine().split(" ");
                    if (isWork == 0 && split[0].equals("400") || isWork == 0 && split[0].equals("500")) {
                        str.append(split[1]);
                        isWork++;
                    } else if (isWork != 0 && split[0].equals("200") || isWork != 0 && split[0].equals("300")) {
                        str.append(";");
                        str.append(split[1]);
                        isWork = 0;
                    }
                }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void print(StringBuilder str, String target) {
        try (PrintWriter out = new PrintWriter(target)) {
            out.println(str);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Analizy analizy = new Analizy();
        analizy.unavailable("./resources/server.txt");
        analizy.print(analizy.str, "./resources/unavailable.csv");
    }
}
