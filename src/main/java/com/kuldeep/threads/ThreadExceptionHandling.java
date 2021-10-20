package com.kuldeep.threads;

import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.atomic.AtomicInteger;

class ThreadExceptionHandling {

    public static void main(String[] args) throws InterruptedException {
        ExecutorService executorService = Executors.newCachedThreadPool();

        List<Callable<Integer>> tasks =
                List.of(new GoodCallable(), new BadCallable(), new GoodCallable(), new GoodCallable());

        List<Future<Integer>> results = executorService.invokeAll(tasks);

        try {
            for (Future<Integer> result : results) {
                System.out.printf("%d", result.get());
            }
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        executorService.shutdown();
    }

    static class GoodCallable implements Callable<Integer> {
        private final AtomicInteger counter = new AtomicInteger(0);

        @Override
        public Integer call() {
            System.out.println("Increasing the counter");
            return counter.incrementAndGet();
        }
    }

    static class BadCallable implements Callable<Integer> {

        @Override
        public Integer call() {
            try {
                return 12 / 0;
            }catch (Exception ex) {
                Thread.currentThread().interrupt();
                throw new IllegalArgumentException(ex);
            }
        }
    }
}
