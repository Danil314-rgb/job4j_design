package ru.job4j.linkedlist;

import org.w3c.dom.Node;

import java.util.Iterator;

public class SimpleLinkedList<E> implements List<E> {

    private static class Node<E> {
        E item;
        Node<E> next;

        Node(E element, Node<E> next) {
            this.item = element;
            this.next = next;
        }
    }

    private int size = 0;
    private int modCount = 0;
    private Node<E> container;
    private SimpleLinkedList<E> first;
    private SimpleLinkedList<E> last;

    @Override
    public void add(E value) {
        /*
        Node newNOde = new Node(value);
        Node walk = head;
        while (walk.next != null) {
            walk = walk.next;
        }
        walk.next = newNOde;
*/
        modCount++;
        size++;
    }

    @Override
    public E get(int index) {
        return null;
    }

    @Override
    public Iterator<E> iterator() {
        return null;
    }
}
