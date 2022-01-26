package ru.job4j.sql;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.sql.*;
import java.util.*;

public class ImportDB {

    private Properties cfg;
    private String dump;

    public ImportDB(Properties cfg, String dump) {
        this.cfg = cfg;
        this.dump = dump;
    }

    public List<User> load() throws IOException {
        List<User> users = new ArrayList<>();
        try (BufferedReader rd = new BufferedReader(new FileReader(dump))) {
            rd.lines().forEach(line -> {
                String[] parts = line.split(";");
                users.add(new User(parts[0], parts[1]));
            });
        }
        return users;
    }

    public void save(List<User> users) throws ClassNotFoundException, SQLException {
        Class.forName(cfg.getProperty("driver"));
        try (Connection cnt = DriverManager.getConnection(
                cfg.getProperty("url"),
                cfg.getProperty("login"),
                cfg.getProperty("password")
        )) {
            for (User user : users) {
                try (PreparedStatement statement = cnt.prepareStatement(
                        "insert into users(name, email) values (?, ?)",
                        Statement.RETURN_GENERATED_KEYS)) {
                    statement.setString(1, user.name);
                    statement.setString(2, user.email);
                    statement.execute();
                }
            }
        }
    }

    private static class User {
        String name;
        String email;

        public User(String name, String email) {
            this.name = name;
            this.email = email;
        }
    }


    public static void main(String[] args) throws Exception {
        Properties cfg = new Properties();
        try (FileInputStream in = new FileInputStream("./resources/app.properties")) {
            cfg.load(in);
        }
        ImportDB db = new ImportDB(cfg, "./resources/dump.txt");
        db.save(db.load());
    }
}
