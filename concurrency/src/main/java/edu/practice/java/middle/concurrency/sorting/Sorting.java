package edu.practice.java.middle.concurrency.sorting;

import java.util.Comparator;
import java.util.List;

public interface Sorting<T> {

    void sort(List<T> list);

    void sort(List<T> list, Comparator<? super T> comparator);

}
