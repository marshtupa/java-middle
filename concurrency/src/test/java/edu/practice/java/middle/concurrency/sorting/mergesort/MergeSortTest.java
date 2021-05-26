package edu.practice.java.middle.concurrency.sorting.mergesort;

import edu.practice.java.middle.concurrency.sorting.Sorting;
import edu.practice.java.middle.concurrency.sorting.quicksort.MultithreadingQuickSort;
import edu.practice.java.middle.concurrency.sorting.quicksort.QuickSort;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MergeSortTest {

    @Test
    void test() {
        long standardTotal = 0L;
        long myTotal = 0L;

        for (int i = 0; i < 20; i++) {
//            ArrayList<Integer> origin = (ArrayList<Integer>) IntStream.range(0, 10)
//                    .mapToObj(j -> ThreadLocalRandom.current().nextInt(0, 20))
//                    .collect(Collectors.toList());
            ArrayList<Integer> origin = new ArrayList<>(List.of(3, 1, 0, 4));
            List<Integer> standard = (ArrayList<Integer>) origin.clone();

            Sorting<Integer> sorting = new SimpleMergeSort<>();
            long startStandard = System.currentTimeMillis();
            standard.sort(Comparator.naturalOrder());
            long endStandard = System.currentTimeMillis();
            sorting.sort(origin, Comparator.naturalOrder());
            long endMy = System.currentTimeMillis();

            assertEquals(standard, origin);
            System.out.println("standard -> " + (endStandard - startStandard) + "ms");
            System.out.println("   my    -> " + (endMy - endStandard) + "ms");
            standardTotal += endStandard - startStandard;
            myTotal += endMy - endStandard;
        }

        System.out.println("\n\nTOTAL:");
        System.out.println("standard -> " + (standardTotal / 20) + "ms");
        System.out.println("   my    -> " + (    myTotal   / 20) + "ms");
    }

}
