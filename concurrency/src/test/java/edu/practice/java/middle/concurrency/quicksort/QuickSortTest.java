package edu.practice.java.middle.concurrency.quicksort;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class QuickSortTest {

    @Test
    void quickSortTest() {
        long standardTotal = 0L;
        long myTotal = 0L;

        for (int i = 0; i < 20; i++) {
            ArrayList<Integer> origin = (ArrayList<Integer>) IntStream.range(0, 10_000_000)
                    .mapToObj(j -> ThreadLocalRandom.current().nextInt(Integer.MIN_VALUE, Integer.MAX_VALUE))
                    .collect(Collectors.toList());;
            List<Integer> standard = (ArrayList<Integer>) origin.clone();

            QuickSort<Integer> quickSort = new MultithreadingQuickSort<>();
            long startStandard = System.currentTimeMillis();
            standard.sort(Comparator.naturalOrder());
            long endStandard = System.currentTimeMillis();
            quickSort.sort(origin, Comparator.naturalOrder());
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
