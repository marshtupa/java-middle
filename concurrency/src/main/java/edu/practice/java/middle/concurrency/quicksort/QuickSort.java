package edu.practice.java.middle.concurrency.quicksort;

import java.util.Comparator;
import java.util.List;

public interface QuickSort<T> {

    void sort(List<T> list);

    void sort(List<T> list, Comparator<? super T> comparator);

}
