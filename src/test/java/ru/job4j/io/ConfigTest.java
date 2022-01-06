package ru.job4j.io;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class ConfigTest {

    @Test
    public void whenPairWithoutComment() {
        String path = "./resources/first.properties";
        Config config = new Config(path);
        config.load();
        assertThat(config.value("hibernate.connection.username"), is("postgres"));
    }

    @Test
    public void whenWithComment() {
        String path = "./resources/second.properties";
        Config config = new Config(path);
        config.load();
        assertThat(config.value("hibernate.connection.username"), is("postgres"));
    }

    @Test(expected = IllegalArgumentException.class)
    public void whenKeyIsNull() {
        String path = "./resources/third.properties";
        Config config = new Config(path);
        config.load();
        assertThat(config.value("hibernate.connection.lastname"), is(""));
    }

    @Test(expected = IllegalArgumentException.class)
    public void whenValueIsNull() {
        String path = "./resources/fourth.properties";
        Config config = new Config(path);
        config.load();
        assertThat(config.value("hibernate.age"), is(""));
    }

    @Test(expected = IllegalArgumentException.class)
    public void whenNoInvalidValue() {
        String path = "./resources/fifth.properties";
        Config config = new Config(path);
        config.load();
        assertThat(config.value("hibernate.age"), is(""));
    }

    @Test(expected = IllegalArgumentException.class)
    public void whenNoInvalidKey() {
        String path = "./resources/sixth.properties";
        Config config = new Config(path);
        config.load();
        assertThat(config.value("hibernate.login"), is(""));
    }
}