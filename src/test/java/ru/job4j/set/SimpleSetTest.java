package ru.job4j.set;

import org.junit.Test;

import java.util.Iterator;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class SimpleSetTest {

    @Test
    public void whenAddNonNull() {
        Set<Integer> set = new SimpleSet<>();
        assertTrue(set.add(1));
        assertTrue(set.contains(1));
        assertFalse(set.add(1));
    }

    @Test
    public void whenAdd() {
        Set<Integer> set = new SimpleSet<>();
        assertTrue(set.add(1));
        assertTrue(set.add(2));
        assertTrue(set.add(3));
        assertTrue(set.add(4));
        assertTrue(set.contains(3));
        assertFalse(set.add(1));
    }

    @Test
    public void whenAddNull() {
        Set<Integer> set = new SimpleSet<>();
        assertTrue(set.add(null));
        assertTrue(set.contains(null));
        assertFalse(set.add(null));
    }

    @Test
    public void whenOneAndNull() {
        Set<Integer> set = new SimpleSet<>();
        assertTrue(set.add(1));
        assertTrue(set.contains(1));
        assertTrue(set.add(null));
        assertTrue(set.contains(null));
    }

    @Test
    public void whenIterator() {
        Set<Integer> set = new SimpleSet<>();
        Iterator<Integer> it = set.iterator();
        set.add(1);
        set.add(null);
        set.add(2);
        assertThat(it.next(), is(1));
        it.next();
        assertThat(it.next(), is(2));
    }
}