package ru.job4j.sql;

import ru.job4j.io.Config;

import java.io.File;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.Properties;
import java.util.StringJoiner;

public class TableEditor implements AutoCloseable {

    private Connection connection;
    private Properties properties;

    public TableEditor(Properties properties) {
        this.properties = properties;
        initConnection();
    }

    private void initConnection() {
        connection = null;
    }

    public String createTable(String tableName) {
        return String.format("create table if not exists %s(%s);", tableName, "id serial primary key");
    }

    public String dropTable(String tableName) {
        return String.format("drop table if exists %s cascade;", tableName);
    }

    public String addColumn(String tableName, String columnName, String type) {
        return String.format("alter table %s add column %s %s", tableName, columnName, type);
    }

    public String dropColumn(String tableName, String columnName) {
        return String.format("alter table %s drop column %s;", tableName, columnName);
    }

    public String renameColumn(String tableName, String columnName, String newColumnName) {
        return String.format("alter table %s rename column %s to %s;", tableName, columnName, newColumnName);
    }

    public static String getTableScheme(Connection connection, String tableName) throws Exception {
        var rowSeparator = "-".repeat(30).concat(System.lineSeparator());
        var header = String.format("%-15s|%-15s%n", "NAME", "TYPE");
        var buffer = new StringJoiner(rowSeparator, rowSeparator, rowSeparator);
        buffer.add(header);
        try (var statement = connection.createStatement()) {
            var selection = statement.executeQuery(String.format(
                    "select * from %s limit 1", tableName
            ));
            var metaData = selection.getMetaData();
            for (int i = 1; i <= metaData.getColumnCount(); i++) {
                buffer.add(String.format("%-15s|%-15s%n",
                        metaData.getColumnName(i), metaData.getColumnTypeName(i))
                );
            }
        }
        return buffer.toString();
    }

    @Override
    public void close() throws Exception {
        if (connection != null) {
            connection.close();
        }
    }

    private static Connection getConnection() throws Exception {
        Config config = new Config("./resources/app.properties");
        config.load();
        Class.forName(config.value("driver"));
        String url = config.value("url");
        String login = config.value("login");
        String password = config.value("password");
        return DriverManager.getConnection(url, login, password);
    }

    public static void main(String[] args) throws Exception {
        File file = new File("./resources/app.properties");
        Properties properties = new Properties();
        properties.load(new FileReader(file));
        TableEditor tableEditor = new TableEditor(properties);
        String nameTable = "demon_table";
        try (Connection connection = getConnection()) {
            try (Statement statement = connection.createStatement()) {

                statement.execute(tableEditor.createTable(nameTable));
                System.out.println(getTableScheme(connection, nameTable));

                statement.execute(tableEditor.addColumn(nameTable, "name", "text"));
                System.out.println(getTableScheme(connection, nameTable));

                statement.execute(tableEditor.renameColumn(nameTable, "name", "surname"));
                System.out.println(getTableScheme(connection, nameTable));

                statement.execute(tableEditor.dropColumn(nameTable, "surname"));
                System.out.println(getTableScheme(connection, nameTable));

                statement.execute(tableEditor.dropTable(nameTable));
                System.out.println(getTableScheme(connection, nameTable));
            }
        }
    }
}
