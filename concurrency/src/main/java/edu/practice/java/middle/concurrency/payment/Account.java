package edu.practice.java.middle.concurrency.payment;

public class Account {

    int cacheBalance;

    public void addMoney(int money) {
        this.cacheBalance += money;
    }

    public void takeOffMoney(int money) {
        if (this.cacheBalance < money) {
            return ;
        }

        this.cacheBalance -= money;
    }

    public int getCacheBalance() {
        return cacheBalance;
    }

    public Account(int cacheBalance) {
        this.cacheBalance = cacheBalance;
    }

}
