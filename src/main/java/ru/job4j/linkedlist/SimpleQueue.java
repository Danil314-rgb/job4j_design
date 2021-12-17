package ru.job4j.linkedlist;

import java.util.NoSuchElementException;

public class SimpleQueue<T> {

    private final SimpleStack<T> in = new SimpleStack<>();
    private final SimpleStack<T> out = new SimpleStack<>();

    private int inCount = 0;
    private int outCount = 0;
    private T res = null;

    public T poll() {
        if (inCount == 0 && outCount == 0) {
            throw new NoSuchElementException();
        }
        if (outCount == 0) {
            while (inCount != 0) {
                res = in.pop();
                out.push(res);
                inCount--;
                outCount++;
            }
        }
        res = out.pop();
        outCount--;
        return res;
    }

    public void push(T value) {
        in.push(value);
        inCount++;
    }
}
