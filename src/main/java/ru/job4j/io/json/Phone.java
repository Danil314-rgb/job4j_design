package ru.job4j.io.json;

import java.util.Arrays;

public class Phone {
    private final boolean camera;
    private final byte megapixels;
    private final String name;
    private final Number number;
    private final String[] contacts;

    public Phone(boolean camera, byte megapixels, String name, Number number, String[] contacts) {
        this.camera = camera;
        this.megapixels = megapixels;
        this.name = name;
        this.number = number;
        this.contacts = contacts;
    }

    @Override
    public String toString() {
        return "Phone{" + "camera=" + camera + ", megapixels=" + megapixels + ", name='" + name + '\'' + ", number=" + number + ", contacts=" + Arrays.toString(contacts) + '}';
    }
}
