package ru.job4j.map;

import org.junit.Test;

import javax.swing.*;
import java.util.ConcurrentModificationException;
import java.util.Iterator;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class SimpleMapTest {

    @Test
    public void whenPutTrue() {
        SimpleMap<Integer, Integer> map = new SimpleMap<>();
        assertTrue(map.put(1, 1));
        assertTrue(map.put(2, 1));
        assertTrue(map.put(3, 1));
        assertTrue(map.put(4, 1));
        assertTrue(map.put(5, 1));
        assertTrue(map.put(6, 1));
        assertTrue(map.put(7, 1));
        assertTrue(map.put(8, 1));
        assertTrue(map.put(9, 1));
    }

    @Test
    public void whenPutTrueAndFalse() {
        SimpleMap<Integer, Integer> map = new SimpleMap<>();
        assertTrue(map.put(132, 1));
        assertTrue(map.put(2, 1));
        assertFalse(map.put(1132, 1));
    }

    @Test
    public void whenGetValue() {
        SimpleMap<Integer, Integer> map = new SimpleMap<>();
        assertTrue(map.put(132, 1));
        assertThat(map.get(132), is(1));
        assertTrue(map.put(2, 1));
        assertFalse(map.put(1132, 1));
    }

    @Test
    public void whenGetNull() {
        SimpleMap<Integer, Integer> map = new SimpleMap<>();
        assertTrue(map.put(132, 1));
        assertTrue(map.put(2, 1));
        assertTrue(map.put(62, 1));
        assertNull(map.get(15));
    }

    @Test
    public void whenRemoveOneKey() {
        SimpleMap<Integer, Integer> map = new SimpleMap<>();
        map.put(1, 1);
        map.put(2, 11);
        map.put(3, 13);
        assertTrue(map.remove(2));
        assertTrue(map.put(2, 11));
    }

    @Test
    public void whenFalseRemove() {
        SimpleMap<Integer, Integer> map = new SimpleMap<>();
        map.put(1, 1);
        map.put(2, 11);
        map.put(3, 13);
        assertFalse(map.remove(4));
        assertTrue(map.put(4, 11));
    }

    @Test
    public void whenIteratorHashNext() {
        SimpleMap<Integer, Integer> map = new SimpleMap<>();
        map.put(1, 1);
        map.put(2, 1);
        map.put(3, 1);
        Iterator<Integer> iterator = map.iterator();
        assertTrue(iterator.hasNext());
    }

    @Test
    public void whenIteratorNextAndHashNext() {
        SimpleMap<Integer, Integer> map = new SimpleMap<>();
        map.put(0, 1);
        map.put(2, 1);
        map.put(3, 1);
        Iterator<Integer> iterator = map.iterator();
        assertThat(iterator.next(), is(0));
        assertTrue(iterator.hasNext());
    }

    @Test
    public void whenIteratorNextNull() {
        SimpleMap<Integer, Integer> map = new SimpleMap<>();
        map.put(1, 1);
        map.put(2, 1);
        map.put(3, 1);
        Iterator<Integer> iterator = map.iterator();
        assertNull(iterator.next());
        assertThat(iterator.next(), is(1));
        assertTrue(iterator.hasNext());
        assertThat(iterator.next(), is(2));
    }

    @Test
    public void whenIteratorNext() {
        SimpleMap<Integer, Integer> map = new SimpleMap<>();
        map.put(1, 1);
        map.put(2, 1);
        map.put(3, 1);
        Iterator<Integer> iterator = map.iterator();
        assertNull(iterator.next());
        assertThat(iterator.next(), is(1));
        assertThat(iterator.next(), is(2));
        assertTrue(iterator.hasNext());
        assertThat(iterator.next(), is(3));
        assertFalse(iterator.hasNext());
    }

    @Test(expected = ConcurrentModificationException.class)
    public void whenIteratorConcurrent() {
        SimpleMap<Integer, Integer> map = new SimpleMap<>();
        map.put(1, 1);
        map.put(2, 1);
        Iterator<Integer> iterator = map.iterator();
        assertNull(iterator.next());
        assertThat(iterator.next(), is(1));
        assertTrue(iterator.hasNext());
        assertThat(iterator.next(), is(2));
        map.put(3, 1);
        assertFalse(iterator.hasNext());
    }
}