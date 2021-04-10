package edu.practice.java.middle.concurrency.quicksort;

import lombok.AllArgsConstructor;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.*;

public class MultithreadingQuickSort<T> extends SimpleQuickSort<T> {

    @Override
    @SuppressWarnings("unchecked")
    public void sort(List<T> list) {
        sort(list, (Comparator<? super T>) Comparator.naturalOrder());
    }

    @Override
    public void sort(List<T> list, Comparator<? super T> comparator) {
        ForkJoinTask.invokeAll(new QuickSortAction<T>(list, 0, list.size() - 1, comparator));
    }

    @AllArgsConstructor
    private class QuickSortAction<R> extends RecursiveAction {

        private final List<T> list;

        private final int low;

        private final int high;

        private final Comparator<? super T> comparator;

        @Override
        protected void compute() {
            ForkJoinTask.invokeAll(quickSort());
        }

        private List<QuickSortAction<R>> quickSort() {
            List<QuickSortAction<R>> subtasks = new ArrayList<>();
            if (low >= high)
                return new ArrayList<>();
            int pivot = getPivot(list, low, high);
            int middle = sortPartition(list, low, high, pivot, comparator);
            subtasks.add(new QuickSortAction<>(list, low, middle - 1, comparator));
            subtasks.add(new QuickSortAction<>(list, middle, high, comparator));
            return subtasks;
        }

    }

}
