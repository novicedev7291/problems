package com.kuldeep.file;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.RunnableFuture;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class DiwaliLights {
    private static final Logger log = LoggerFactory.getLogger(DiwaliLights.class);

    public static void main(String[] args) throws InterruptedException {
        CancellableExecutor pool = CancellableExecutor.newCancellableThreadPool(4);

        CountDownLatch startSignal = new CountDownLatch(4);

        int i = 0;
        List<Future<Void>> bulbs = new ArrayList<>(4);
        while (i < 4) {
            bulbs.add(pool.submit(new BulbTask(startSignal)));
            i++;
        }

        try {
            startSignal.await();

            TimeUnit.SECONDS.sleep(30);

            bulbs.forEach(bulb -> bulb.cancel(true));
        } catch (InterruptedException e) {
            e.printStackTrace();
            throw new IllegalStateException(e);
        }finally {
            pool.shutdown();
        }

    }
}

interface CancellableRunnable<T> extends Callable<T> {
    void cancel();
    RunnableFuture<T> newTask();
}

abstract class AbstractCancellableTask<T> implements CancellableRunnable<T> {
    protected boolean isCancelled = false;

    @Override
    public synchronized void cancel() {
        isCancelled = true;
    }

    @Override
    public RunnableFuture<T> newTask() {
        return new FutureTask<T>(this) {
            @Override
            public boolean cancel(boolean mayInterruptIfRunning) {
                 try{
                     AbstractCancellableTask.this.cancel();
                 }finally {
                     return super.cancel(mayInterruptIfRunning);
                 }
            }
        };
    }
}

class CancellableExecutor extends ThreadPoolExecutor {
    private CancellableExecutor(int nThreads) {
        super(nThreads, nThreads, 0, TimeUnit.MILLISECONDS, new LinkedBlockingQueue<>());
    }

    public static CancellableExecutor newCancellableThreadPool(int nThreads) {
        return new CancellableExecutor(nThreads);
    }

    @Override
    protected <T> RunnableFuture<T> newTaskFor(Callable<T> callable) {
        if(callable instanceof CancellableRunnable) {
            return ((CancellableRunnable<T>) callable).newTask();
        }
        return super.newTaskFor(callable);
    }
}

class BulbTask extends AbstractCancellableTask<Void> {
    private static final Logger log = LoggerFactory.getLogger(Thread.currentThread().getName());
    private final CountDownLatch startSignal;

    public BulbTask(CountDownLatch startSignal) {
        this.startSignal = startSignal;
    }

    @Override
    public Void call() {
        try {
            startSignal.countDown();

            while (!isCancelled) {
                    log.info("ON");
                    TimeUnit.SECONDS.sleep(5);
            }

        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        return null;
    }
}
