package com.kuldeep.threads;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @author <a href="kuldeepyadav7291@gmail.com">Kuldeep</a>
 */
class ConcurrentMergeHelper {
    private ConcurrentMergeHelper() {
        throw new UnsupportedOperationException();
    }

    public static int[] merge(int[] a, int[] b) throws InterruptedException {
        ExecutorService executor = Executors.newCachedThreadPool();

        int[] result = new int[a.length + b.length];

        for (int i = 0 ; i < a.length; i++) {
            executor.submit(new MergeTask(result, a[i], i, b, "A"));
        }

        for (int i = 0; i < b.length; i++) {
            executor.submit(new MergeTask(result, b[i], i, a, "B"));
        }

        executor.shutdown();
        executor.awaitTermination(3, TimeUnit.SECONDS);
        return result;
    }
}

class MergeTask implements Runnable {
    private static final Logger log = LoggerFactory.getLogger(Thread.currentThread().getName());
    private final int[] result;
    private final int value;
    private final int index;
    private final int[] array;
    private final String group;

    MergeTask(int[] result, int value, int index, int[] array, String group) {
        this.result = result;
        this.value = value;
        this.index = index;
        this.array = array;
        this.group = group;
    }


    @Override
    public void run() {
        log.info("Processing {} element of group {}", index, group);
        int n = array.length;
        if (value < array[0]) {
            result[index] = value;
        }else if (value > array[n - 1]) {
            result[n + index] = value;
        }else {
            int lower = 0;
            int upper = n - 1;
            int middle = 0;
            while (lower < upper) {
                middle = (lower + upper) / 2;
                if (value > array[middle] && value < array[middle + 1]) {
                    break;
                }
                else if (value < array[middle]) {
                    upper = middle;
                }else if (value > array[middle]) {
                    lower = middle + 1;
                }
            }
            result[index + middle + 1] = value;
        }
    }
}



