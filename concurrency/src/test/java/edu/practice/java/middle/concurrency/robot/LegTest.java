package edu.practice.java.middle.concurrency.robot;

import org.junit.jupiter.api.Test;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class LegTest {

    @Test
    void testLeg() {
        Lock lock = new ReentrantLock();

        CompletableFuture.allOf(
                CompletableFuture.runAsync(new Leg(true, lock)),
                CompletableFuture.runAsync(new Leg(false, lock))
        );
    }

}
