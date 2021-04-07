package edu.practice.java.middle.concurrency.robot;

import java.util.concurrent.locks.Lock;

public class Leg implements Runnable {

    private final boolean name;

    private static boolean state = true;

    private final Lock lock;

    @Override
    public void run() {
        while (true) {
            lock.lock();
            if (state == name) {
                System.out.println(name);
                state = !state;
            }
            lock.unlock();
        }
    }

    public Leg(boolean name, Lock lock) {
        this.name = name;
        this.lock = lock;
    }

}