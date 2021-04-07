package edu.practice.java.middle.concurrency.payment;

import java.util.concurrent.locks.Lock;

public class PaymentThread implements Runnable{

    final Account accountFrom;

    final Account accountTo;

    final int money;

    @Override
    public void run() {
        accountFrom.takeOffMoney(money);
        accountTo.addMoney(money);
    }

    public PaymentThread(Account accountFrom, Account accountTo, int money) {
        this.accountFrom = accountFrom;
        this.accountTo = accountTo;
        this.money = money;
    }

}
