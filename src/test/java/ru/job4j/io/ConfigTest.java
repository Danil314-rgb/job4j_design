package ru.job4j.io;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class ConfigTest {

    @Test
    public void whenPairWithoutComment() {
        String path = "app.properties";
        Config config = new Config(path);
        config.load();
        assertThat(config.value("hibernate.connection.username"), is("postgres"));
    }

    @Test
    public void whenWithComment() {
        String path = "app.properties";
        Config config = new Config(path);
        config.load();
        assertThat(config.value("hibernate.connection.username"), is("postgres"));
    }

    @Test (expected = IllegalArgumentException.class)
    public void whenKeyIsNull() {
        String path = "app.properties";
        Config config = new Config(path);
        config.load();
        assertThat(config.value("username"), is("postgres"));
    }

    @Test (expected = IllegalArgumentException.class)
    public void whenValueIsNull() {
        String path = "app.properties";
        Config config = new Config(path);
        config.load();
        assertThat(config.value("hibernate.connection.password"), is("123"));
    }

    @Test /*(expected = IllegalArgumentException.class)*/
    public void whenNoInvalidValue() {
        String path = "app.properties";
        Config config = new Config(path);
        config.load();
        assertThat(config.value("hibernate.age"), is(""));
    }

    @Test /*(expected = IllegalArgumentException.class)*/
    public void whenNoInvalidKey() {
        String path = "app.properties";
        Config config = new Config(path);
        config.load();
        assertThat(config.value("hibernate.login"), is("login"));
    }
}