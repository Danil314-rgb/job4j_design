package ru.job4j.io.json;

public class Number {

    private final String number;

    public Number(String number) {
        this.number = number;
    }

    @Override
    public String toString() {
        return "Contact{"
                + "number='"
                + number + '\''
                + '}';
    }
}
