package edu.practice.java.middle.concurrency.payment;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class AccountLock extends Account {

    private final Lock lock;

    @Override
    public void addMoney(int money) {
        lock.lock();
        try {
            this.cacheBalance += money;
        } finally {
            lock.unlock();
        }
    }

    @Override
    public void takeOffMoney(int money) {
        lock.lock();
        try {
            if (this.cacheBalance < money) {
                return ;
            }
            this.cacheBalance -= money;
        } finally {
            lock.unlock();
        }
    }

    public AccountLock(int cacheBalance) {
        super(cacheBalance);
        lock = new ReentrantLock();
    }

    public AccountLock(int cacheBalance, Lock lock) {
        super(cacheBalance);
        this.lock = lock;
    }

}
