package ru.job4j.io;

import java.io.FileInputStream;

public class ReadFile {

    public static void main(String[] args) {
        try (FileInputStream in = new FileInputStream("even.txt")) {
            StringBuilder text = new StringBuilder();
            int read;
            while ((read = in.read()) != -1) {
                text.append((char) read);
            }
            String[] lines = text.toString().split(System.lineSeparator());
            for (String line : lines) {
                int number = Integer.parseInt(line);
                boolean result = number % 2 == 0;
                System.out.println(line + " - " + result);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
