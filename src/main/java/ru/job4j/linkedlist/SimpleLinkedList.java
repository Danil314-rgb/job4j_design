package ru.job4j.linkedlist;

import org.w3c.dom.Node;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

public class SimpleLinkedList<E> implements List<E> {

    private static class Node<E> {
        E item;
        Node<E> next;

        Node(E element, Node<E> next) {
            this.item = element;
            this.next = next;
        }
    }

    transient int size = 0;
    transient Node<E> first;
    transient Node<E> last;
    private int modCount = 0;

    @Override
    public void add(E value) {
        final Node<E> l = last;
        final Node<E> newNode = new Node<>(value, null);
        last = newNode;
        if (l == null) {
            first = newNode;
        } else {
            l.next = newNode;
        }
        modCount++;
        size++;
    }

    @Override
    public E get(int index) {
        Objects.checkIndex(index, size);
        Node<E> pref = first;
        E res = pref.item;
        for (int i = 0; i <= index; i++) {
            res = pref.item;
            pref = pref.next;
        }
        return res;
    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {
            SimpleLinkedList.Node<E> res = first;
            final int expectedModCount = modCount;

            @Override
            public boolean hasNext() {
                if (expectedModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                return res != null;
            }

            @Override
            public E next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                E result = res.item;
                res = res.next;
                return result;
            }
        };
    }
}
