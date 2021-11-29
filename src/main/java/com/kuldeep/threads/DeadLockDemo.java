package com.kuldeep.threads;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Random;

/**
 * @author <a href="kuldeepyadav7291@gmail.com">Kuldeep</a>
 */
class DeadLockDemo {
    private static final int NUM_ITERATIONS = 1000000;
    private static final int NUM_THREADS = 20;
    private static final int NUM_ACCOUNTS = 5;
    private static final Logger log = LoggerFactory.getLogger(DeadLockDemo.class.getName());

    private static final Object tieLock = new Object();

    public static void main(String[] args) {
        final Account[] accounts = {
                new Account(500),
                new Account(1000),
                new Account(700),
                new Account(300),
                new Account(829)
        };
        final Random rand = new Random();

        class HelperThread extends Thread {
            @Override
            public void run() {
                for (int i = 0 ; i < NUM_ITERATIONS; i++) {
                    int fi = rand.nextInt(NUM_ACCOUNTS);
                    int ti = rand.nextInt(NUM_ACCOUNTS);

                    int amount = rand.nextInt(100);
                    transferMoneyDeadlock(accounts[fi], accounts[ti], amount);
                }
            }
        }

        for (int i = 0; i < NUM_THREADS; i++) {
            new HelperThread().start();
        }
    }

    public static void transferMoneyDeadlock(final Account fromAcc, final Account toAcc, Integer money) {
        synchronized(fromAcc) {
            synchronized(toAcc) {
                log.info("Both Locks acquired");
                if (fromAcc.balance().compareTo(money) < 0)
                    throw new IllegalStateException("From account does not have enough money");
                else {
                    fromAcc.debit(money);
                    toAcc.credit(money);
                }
            }
        }
    }

    /**
     * This version reduces the risk of deadlocking on two resources by other threads
     * @param fromAcc fromAccount
     * @param toAcc toAccount
     * @param money Amount to transfer
     */
    public static void transferMoney(final Account fromAcc, final Account toAcc, Integer money) {
        class Helper {
            public void transfer() {
                if (fromAcc.balance().compareTo(money) < 0)
                    throw new IllegalStateException("From account does not have enough money");
                else {
                    fromAcc.debit(money);
                    toAcc.credit(money);
                }
            }
        }

        // Account Id or some other unique field can be used instead of below,
        // if available
        int fromHash = System.identityHashCode(fromAcc);
        int toHash = System.identityHashCode(toAcc);

        if (fromHash < toHash) {
            synchronized(fromAcc) {
                synchronized(toAcc) {
                    log.info("Locks acquired");
                    new Helper().transfer();
                }
            }
        }else if (toHash < fromHash) {
            synchronized(toAcc) {
                synchronized(fromAcc) {
                    log.info("Locks acquired");
                    new Helper().transfer();
                }
            }
        }else {
            synchronized(tieLock) {
                synchronized(fromAcc) {
                    synchronized(toAcc) {
                        log.info("Locks acquired");
                        new Helper().transfer();
                    }
                }
            }
        }

    }
}

class Account {
    private Integer balance;

    public Account(Integer initialBalance) {
        this.balance = initialBalance;
    }

    public Account() {
        this(0);
    }

    public void credit(Integer amount) {
        this.balance += amount;
    }

    public void debit(Integer amount) {
        this.balance -= amount;
    }

    public Integer balance() {
        return balance;
    }
}
