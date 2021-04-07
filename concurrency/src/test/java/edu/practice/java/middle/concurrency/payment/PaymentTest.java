package edu.practice.java.middle.concurrency.payment;

import org.junit.jupiter.api.Test;

import java.util.Collection;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.IntStream;

import static java.util.stream.Collectors.collectingAndThen;
import static java.util.stream.Collectors.toList;

public class PaymentTest {

    Account firstAccount;

    Account secondAccount;

    PaymentThread firstThread;

    PaymentThread secondThread;

    @Test
    void testLockAccounts() {
        this.firstAccount = new AccountLock(100_000);
        this.secondAccount = new AccountLock(100_000);
        this.firstThread = new PaymentThread(firstAccount, secondAccount, 100);
        this.secondThread = new PaymentThread(secondAccount, firstAccount, 100);
        testRunPayments();
    }

    @Test
    void testSynchronizedAccounts() {
        this.firstAccount = new AccountSynchronized(100_000);
        this.secondAccount = new AccountSynchronized(100_000);
        this.firstThread = new PaymentThread(firstAccount, secondAccount, 100);
        this.secondThread = new PaymentThread(secondAccount, firstAccount, 100);
        testRunPayments();
    }

    void testRunPayments() {
        long start = System.currentTimeMillis();
        List<CompletableFuture<Void>> futureList = IntStream.range(0, 5_000_000)
                .mapToObj(i -> i % 2 == 0 ? firstThread : secondThread)
                .map(e -> CompletableFuture.runAsync(e))
                .collect(toList());

        allOf(futureList).join();
        long spentTime = System.currentTimeMillis() - start;
        System.out.println("Spent time " + spentTime + "ms");
        System.out.println("Cash balance of the first account: " + firstAccount.getCacheBalance());
        System.out.println("Cash balance of the second account: " + secondAccount.getCacheBalance());
    }

    public static <T> CompletableFuture<List<T>> allOf(Collection<CompletableFuture<T>> futures) {
        return futures.stream()
                .collect(collectingAndThen(
                        toList(),
                        l -> CompletableFuture.allOf(l.toArray(new CompletableFuture[0]))
                                .thenApply(__ -> l.stream()
                                        .map(CompletableFuture::join)
                                        .collect(toList()))));
    }
}
