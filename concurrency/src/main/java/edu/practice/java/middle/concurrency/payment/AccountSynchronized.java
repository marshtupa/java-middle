package edu.practice.java.middle.concurrency.payment;

public class AccountSynchronized extends Account {

    @Override
    public synchronized void addMoney(int money) {
        this.cacheBalance += money;
    }

    @Override
    public synchronized void takeOffMoney(int money) {
        if (this.cacheBalance < money) {
            return ;
        }

        this.cacheBalance -= money;
    }

    public AccountSynchronized(int cacheBalance) {
        super(cacheBalance);
    }

}
