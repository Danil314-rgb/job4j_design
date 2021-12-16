package ru.job4j.linkedlist;

import org.w3c.dom.Node;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class ForwardLinked<T> implements Iterable<T> {

    private Node<T> head;

    private static class Node<T> {
        T value;
        Node<T> next;

        public Node(T value, Node<T> next) {
            this.value = value;
            this.next = next;
        }
    }

    public void add(T value) {
        Node<T> node = new Node<>(value, null);
        if (head == null) {
            head = node;
            return;
        }
        Node<T> tail = head;
        while (tail.next != null) {
            tail = tail.next;
        }
        tail.next = node;
    }

    public T deleteFirst() {
        if (head == null) {
            throw new NoSuchElementException();
        }
        T result = head.value;
        head.value = null;
        head = head.next;
        return result;
    }

    public T deleteLast() {
        if (head == null) {
            throw new NoSuchElementException();
        }
        T result = head.value;
        while (head.next != null) {
            result = head.next.value;
            head = head.next;  /*тут проблема*/
        }
        head.value = null;
        return result;
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            ForwardLinked.Node<T> node = head;

            @Override
            public boolean hasNext() {
                return node != null;
            }

            @Override
            public T next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                T value = node.value;
                node = node.next;
                return value;
            }
        };
    }
}
