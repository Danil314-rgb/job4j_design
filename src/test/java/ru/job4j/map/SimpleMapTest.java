package ru.job4j.map;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class SimpleMapTest {

    @Test
    public void whenPutIndexFourAndTwoAndFive() {
        SimpleMap<Integer, Integer> map = new SimpleMap<>();
        assertTrue(map.put(132, 1));
        assertTrue(map.put(2, 1));
        assertTrue(map.put(5, 1));
    }

    @Test
    public void whenPutIndexFourAndFive() {
        SimpleMap<Integer, Integer> map = new SimpleMap<>();
        assertTrue(map.put(132, 1));
        assertTrue(map.put(2, 1));
        assertFalse(map.put(1132, 1));
    }

    /*по 2 метода для
    * put - добавить
    * get - получить
    * remove - удалить
    * iterator - перебрать
    * */
}