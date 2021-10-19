package com.kuldeep.threads;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author <a href="kuldeepyadav7291@gmail.com">Kuldeep</a>
 */
public class MultipleThreadGame {
    private static Logger log = LoggerFactory.getLogger(MultipleThreadGame.class);

    public static void main(String[] args) {

        Counter counter = new Counter(0);

        Thread t1 = new Thread(() -> {
            while (counter.count() != 100) {
                if (counter.count() % 2 != 0) {
                    log.info("{} ", counter.count());
                    counter.increment();
                }
            }
        });
        Thread t2 = new Thread(() -> {
            while (counter.count() != 100) {
                if (counter.count() % 2 == 0) {
                    log.info("{} ", counter.count());
                    counter.increment();
                }
            }
        });

        t1.start();
        t2.start();

        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}

class Counter {
    private int value;

    public Counter(int initialValue) {
        this.value = initialValue;
    }

    public void increment() {
        this.value++;
    }

    public int count() {
        return this.value;
    }
}