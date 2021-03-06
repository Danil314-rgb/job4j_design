package ru.job4j.io;

import java.io.*;

public class Analizy {

    private StringBuilder str = new StringBuilder();

    public void unavailable(StringBuilder str, String source) {
        try (BufferedReader in = new BufferedReader(new FileReader(source))) {
            byte isWork = 0;
            while (in.ready()) {
                String[] split = in.readLine().split(" ");
                if (isWork == 0 && "400".equals(split[0]) || isWork == 0 && "500".equals(split[0])) {
                    str.append(split[1]);
                    str.append(";");
                    isWork++;
                } else if (isWork != 0 && "200".equals(split[0]) || isWork != 0 && "500".equals(split[0])) {
                    str.append(split[1]);
                    str.append("-");
                    isWork = 0;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void print(StringBuilder str, String target) {
        try (PrintWriter out = new PrintWriter(target)) {
            String[] split = str.toString().split("-");
            for (String item : split) {
                out.println(item);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Analizy analizy = new Analizy();
        analizy.unavailable(analizy.str, "./resources/server.txt");
        analizy.print(analizy.str, "./resources/unavailable.csv");
    }
}
