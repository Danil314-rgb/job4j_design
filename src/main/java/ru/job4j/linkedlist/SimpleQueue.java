package ru.job4j.linkedlist;

import java.util.NoSuchElementException;

public class SimpleQueue<T> {

    private final SimpleStack<T> in = new SimpleStack<>();
    private final SimpleStack<T> out = new SimpleStack<>();

    private int inCount = 0;
    private int outCount = 0;

    private T res = null;

    /*должен возвращать первое значение и удалять его из коллекции*/
    public T poll() {
        if (inCount == 0 && outCount == 0) {
            throw new NoSuchElementException();
        }

         if (inCount == 0) { // поменять местами
            while (outCount != 0) {
                res = out.pop();
                in.push(res);
                outCount--;
                inCount++;
            }
        }
         if (outCount == 0) {
            while (inCount > 1) {
                res = in.pop();
                out.push(res);
                inCount--;
                outCount++;
            }
            if (inCount == 1) {
                res = in.pop();
                inCount--;
            }
        }
        /*else if (inCount == 0) {
            while (outCount > 1) {
                res = out.pop();
                in.push(res);
                outCount--;
                inCount++;
            }
            if (outCount == 1) {
                res = out.pop();
                outCount--;
            }
        }*/
        return res;
    }

    /*помещает значение в конец*/
    public void push(T value) {
        if (outCount == 0) {
            in.push(value);
            inCount++;
            return;
        }
        out.push(value);
        outCount++;
    }

    public void revers() {
        while (outCount != 0) {
            res = out.pop();
            in.push(res);
            outCount--;
            inCount++;
        }
    }
}
