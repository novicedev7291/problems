package com.kuldeep.threads;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import static java.util.concurrent.Executors.newSingleThreadScheduledExecutor;

public class FactorialSimulation {
    private static final Logger log = LoggerFactory.getLogger(FactorialSimulation.class);
    public static void main(String[] args) {
        FactorialSimulation simulation = new FactorialSimulation(newSingleThreadScheduledExecutor());

        Runnable r = () -> {
            log.info("Calculating factorials...");
            try {
                TimeUnit.SECONDS.sleep(10);
                log.info("Finished calculating factorials...");
            } catch (InterruptedException e) {
                log.info("Cancelling current calculations...");
                Thread.currentThread().interrupt();
            }
        };

        simulation.timedRun(r, 5, TimeUnit.SECONDS);


    }

    private final ScheduledExecutorService executor;

    public FactorialSimulation(ScheduledExecutorService executor) {
        this.executor = executor;
    }

    public void timedRun(Runnable r, long timeout, TimeUnit unit) {
        Future<?> task = executor.submit(r);

        try {
            task.get(timeout, unit);
        } catch (InterruptedException | TimeoutException e) {
            task.cancel(true);
        } catch (ExecutionException e) {
            throw new IllegalStateException(e);
        }
    }
}
