package edu.practice.java.middle.concurrency.sorting.mergesort;

import edu.practice.java.middle.concurrency.sorting.AbstractSorting;

import java.lang.reflect.Array;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class SimpleMergeSort<T> extends AbstractSorting<T> implements MergeSort<T> {

    @Override
    @SuppressWarnings("unchecked")
    public void sort(List<T> list) {
        sort(list, (Comparator<? super T>) Comparator.naturalOrder());
    }

    @Override
    @SuppressWarnings("unchecked")
    public void sort(List<T> list, Comparator<? super T> comparator) {
        List<T> temp = (List<T>) Collections.singletonList(Array.newInstance(list.getClass().getComponentType(), list.size()));
        mergeSort(list, 0, list.size() - 1, temp, comparator);
    }

    private void mergeSort(List<T> list, int low, int high, List<T> temp, Comparator<? super T> comparator) {
        if (low >= high)
            return;
        int middle = low + (high - low) / 2;
        mergeSort(list, low, middle, temp, comparator);
        mergeSort(list, middle + 1, high, temp, comparator);
        mergeParts(list, low, high, temp, comparator);
    }

    private void mergeParts(List<T> list, int low, int high, List<T> temp, Comparator<? super T> comparator) {
        int leftEnd = (low + high) / 2;
        int rightPointer;

    }

}
