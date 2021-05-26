package edu.practice.java.middle.concurrency.payment;

public class PaymentThreadWithDeadLock extends PaymentThread {

    @Override
    public void run() {
        synchronized (accountFrom) {
            synchronized (accountTo) {
                if (accountFrom.takeOffMoney(money)) {
                    accountTo.addMoney(money);
                }
            }
        }
    }

    public PaymentThreadWithDeadLock(Account accountFrom, Account accountTo, int money) {
        super(accountFrom, accountTo, money);
    }

}
