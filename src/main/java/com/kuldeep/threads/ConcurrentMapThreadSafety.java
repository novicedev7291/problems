package com.kuldeep.threads;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @author <a href="kuldeepyadav7291@gmail.com">Kuldeep</a>
 */
class ConcurrentMapThreadSafety {
    private static final int NUM_THREADS = 1000;
    private static final Logger log = LoggerFactory.getLogger(ConcurrentMapThreadSafety.class.getName());

    private static final Object lock = new Object();

    public static void main(String[] args) throws InterruptedException {
        final Map<String, Integer> map = new ConcurrentHashMap<>();
        final String key = "test";
        map.put(key, 0);

        CyclicBarrier barrier = new CyclicBarrier(NUM_THREADS);
        class Task implements Runnable {
            @Override
            public void run() {
                try {
                    barrier.await();
                    incrementKeyValue(map, key);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    e.printStackTrace();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                }
            }
        }

        ExecutorService executor = Executors.newCachedThreadPool();
        for (int i = 0; i < NUM_THREADS; i++) {
            executor.submit(new Task());
        }

        executor.shutdown();
        boolean terminated = executor.awaitTermination(5, TimeUnit.SECONDS);
        log.info("Executor was timed out? {}", !terminated);

        log.info("Final value incremented by {} threads is {}", NUM_THREADS, map.get(key));
    }

    public static void incrementKeyValue(Map<String, Integer> map, String key) {
        synchronized(lock) {
            int i = map.get(key);
            map.put(key, ++i);
        }
    }
}
