package edu.practice.java.middle.concurrency.payment;

public class AccountSynchronized extends Account {

    @Override
    public synchronized void addMoney(int money) {
        this.cacheBalance += money;
    }

    @Override
    public synchronized boolean takeOffMoney(int money) {
        return super.takeOffMoney(money);
    }

    public AccountSynchronized(int cacheBalance) {
        super(cacheBalance);
    }

}
