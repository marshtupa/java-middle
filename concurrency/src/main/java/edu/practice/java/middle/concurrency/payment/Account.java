package edu.practice.java.middle.concurrency.payment;

public class Account {

    int cacheBalance;

    public void addMoney(int money) {
        this.cacheBalance += money;
    }

    public boolean takeOffMoney(int money) {
        if (this.cacheBalance < money) {
            return false;
        }

        this.cacheBalance -= money;
        return true;
    }

    public int getCacheBalance() {
        return cacheBalance;
    }

    public Account(int cacheBalance) {
        this.cacheBalance = cacheBalance;
    }

}
