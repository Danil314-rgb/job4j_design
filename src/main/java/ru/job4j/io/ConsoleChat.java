package ru.job4j.io;

import java.io.*;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class ConsoleChat {

    private final String path;
    private final String botAnswers;
    private static final String OUT = "закончить";
    private static final String STOP = "стоп";
    private static final String CONTINUE = "продолжить";

    private final Scanner sc = new Scanner(System.in);
    private static List<String> logList = new ArrayList<>();

    public ConsoleChat(String path, String botAnswers) {
        this.path = path;
        this.botAnswers = botAnswers;
    }

    public void run() {
        System.out.println("Задайте вопрос с ответом Да или Нет!");
        String question = sc.next();
        List<String> answers = readPhrases();
        logList.add(question);
        while (!question.equals(OUT)) {
            String answer = randomAnswer(answers);
            logList.add(answer);
            System.out.println(answer);
            question = sc.next();
            logList.add(question);
            if (question.equals(STOP)) {
                while (!question.equals(CONTINUE)) {
                    question = sc.next();
                    logList.add(question);
                }
            }
        }
    }

    private String randomAnswer(List<String> list) {
        Random random = new Random();
        return list.get(random.nextInt(list.size() - 1));
    }

    private List<String> readPhrases() {
        List<String> listAnswers = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(botAnswers, Charset.forName("WINDOWS-1251")))) {
            br.lines().forEach(listAnswers::add);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return listAnswers;
    }

    private void saveLog(List<String> log) {
        try (PrintWriter pw = new PrintWriter(
                new FileWriter(path, Charset.forName("WINDOWS-1251"), true))) {
            log.forEach(pw::println);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        ConsoleChat cc = new ConsoleChat("C:\\projects\\job4j_design\\resources\\log.txt",
                "C:\\projects\\job4j_design\\resources\\answer.txt");
        cc.run();
        cc.saveLog(logList);
    }
}
