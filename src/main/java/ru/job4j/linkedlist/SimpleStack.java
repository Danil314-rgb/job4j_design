package ru.job4j.linkedlist;

public class SimpleStack<T> {

    private ForwardLinked<T> linked = new ForwardLinked<>();

    /*должен возвращать значение и удалять его из коллекции*/
    public T pop() {
        return linked.deleteLast();
    }

    /*помещает значение в коллекцию*/
    public void  push(T value) {
        linked.add(value);
    }
}
