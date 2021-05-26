package edu.practice.java.middle.concurrency.payment;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class AccountLock extends Account {

    private final Lock lock;

    @Override
    public void addMoney(int money) {
        lock.lock();
        this.cacheBalance += money;
        lock.unlock();
    }

    @Override
    public boolean takeOffMoney(int money) {
        boolean result;
        lock.lock();
        result = super.takeOffMoney(money);
        lock.unlock();
        return result;
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
