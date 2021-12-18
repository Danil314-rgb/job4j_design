package ru.job4j.linkedlist;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class ListUtilsTest {

    @Test
    public void whenAddBefore() {
        List<Integer> input = new ArrayList<>(Arrays.asList(1, 3));
        ListUtils.addBefore(input, 1, 2);
        assertThat(input, is(Arrays.asList(1, 2, 3)));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void whenAddBeforeWithInvalidIndex() {
        List<Integer> input = new ArrayList<>(Arrays.asList(1, 3));
        ListUtils.addBefore(input, 3, 2);
    }

    @Test
    public void whenAddAfterLast() {
        List<Integer> input = new ArrayList<>(Arrays.asList(0, 1, 3));
        ListUtils.addAfter(input, 1, 2);
        assertThat(input, is(Arrays.asList(0, 1, 2, 3)));
    }

    @Test
    public void whenRemoveIf() {
        List<Integer> input = new ArrayList<>(Arrays.asList(1, 2, 2, 3, 4, 5, 6, 7));
        ListUtils.removeIf(input, value -> value % 2 == 0);
        assertThat(input, is(Arrays.asList(1, 3, 5, 7)));
    }

    @Test
    public void whenRemoveIf1() {
        List<Integer> input = new ArrayList<>(Arrays.asList(2, 2, 2, 6, 8, 12, 2));
        List<Integer> output = new ArrayList<>();
        ListUtils.removeIf(input, value -> value % 2 == 0);
        assertThat(output, is(input));
    }

    @Test
    public void whenReplaceIt() {
        List<Integer> input = new ArrayList<>(Arrays.asList(2, 3, 4, 5, 6, 7));
        ListUtils.replaceIf(input, value -> value % 2 == 0, -1);
        assertThat(input, is(Arrays.asList(-1, 3, -1, 5, -1, 7)));
    }

    @Test
    public void whenReplaceIt1() {
        List<Integer> input = new ArrayList<>(Arrays.asList(1, 3, 5, 7, 9));
        ListUtils.replaceIf(input, value -> value % 2 == 0, -1);
        assertThat(input, is(Arrays.asList(1, 3, 5, 7, 9)));
    }

    @Test
    public void whenRemoveAll() {
        List<Integer> list = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5));
        List<Integer> elements = new ArrayList<>(Arrays.asList(1, 5));
        ListUtils.removeAll(list, elements);
        assertThat(list, is(Arrays.asList(2, 3, 4)));
    }
}