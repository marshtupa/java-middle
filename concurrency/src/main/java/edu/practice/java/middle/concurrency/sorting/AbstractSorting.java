package edu.practice.java.middle.concurrency.sorting;

import java.util.List;

public class AbstractSorting<T> {

    protected final int LESS = -1;

    protected final int MORE = 1;

    protected void swap(List<T> list, int leftPointer, int rightPointer) {
        T tmp = list.get(leftPointer);
        list.set(leftPointer, list.get(rightPointer));
        list.set(rightPointer, tmp);
    }

}
