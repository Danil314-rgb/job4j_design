package ru.job4j.io;

import java.io.FileOutputStream;

public class ResultFile {

    public static void main(String[] args) {
        int size = 9;
        int[][] arr = new int[size][size];
        try (FileOutputStream out = new FileOutputStream("result.txt")) {
            for (int i = 0; i < size; i++) {
                for (int j = 0; j < size; j++) {
                    arr[i][j] = (j + 1) * (i + 1);
                    String text = Integer.toString(arr[i][j]);
                    String tab = " ";
                    out.write(text.getBytes());
                    out.write(tab.getBytes());
                }
                out.write(System.lineSeparator().getBytes());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}


