package edu.practice.java.middle.concurrency.quicksort;

import java.util.Comparator;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class SimpleQuickSort<T> implements QuickSort<T> {

    final int LESS = -1;
    final int MORE = 1;

    @Override
    @SuppressWarnings("unchecked")
    public void sort(List<T> list) {
        sort(list, (Comparator<? super T>) Comparator.naturalOrder());
    }

    @Override
    public void sort(List<T> list, Comparator<? super T> comparator) {
        quickSort(list, 0, list.size() - 1, comparator);
    }

    void quickSort(List<T> list, int low, int high, Comparator<? super T> comparator) {
        if (low >= high)
            return ;
        int pivot = getPivot(list, low, high);
        int middle = sortPartition(list, low, high, pivot, comparator);
        quickSort(list, low, middle - 1, comparator);
        quickSort(list, middle,  high, comparator);
    }

    int sortPartition(List<T> list, int low, int high, int pivot, Comparator<? super T> comparator) {
        int leftPointer = low;
        int rightPointer = high;
        T pivotElement = list.get(pivot);

        while (leftPointer <= rightPointer) {
            while (comparator.compare(list.get(leftPointer), pivotElement) == LESS) {
                leftPointer++;
            }
            while (comparator.compare(list.get(rightPointer), pivotElement) == MORE) {
                rightPointer--;
            }
            if (leftPointer <= rightPointer) {
                swap(list, leftPointer++, rightPointer--);
            }
        }
        return leftPointer;
    }

    void swap(List<T> list, int leftPointer, int rightPointer) {
        T tmp = list.get(leftPointer);
        list.set(leftPointer, list.get(rightPointer));
        list.set(rightPointer, tmp);
    }

    int getPivot(List<T> list, int low, int high) {
        return ThreadLocalRandom.current().nextInt(low, high + 1);
    }

}
