package ru.job4j.iterator;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class EvenNumberIterator implements Iterator<Integer> {

    private int[] data;
    private int index = 0;

    public EvenNumberIterator(int[] data) {
        this.data = data;
    }

    @Override
    public boolean hasNext() {
        int line = index + 1;
        for (int i = line; i <= data.length; i++) {
            if (line < data.length && data[line] % 2 == 0) {
                return true;
            }
        }
        return false;
    }

    @Override
    public Integer next() {
        if (!hasNext()) {
            throw  new NoSuchElementException();
        }
        while (data[index] % 2 != 0) {
            index++;
        }
        /*входит и выполняется пока условие true
        * циклом while(число % 2 != 0)
        *{берём следующее число и сравниваем
        * if число % 2 == 0 то return число
        * else берём следующее число }
        *
        * */
        int res = data[index];
        index++;
        return res;
    }
}
