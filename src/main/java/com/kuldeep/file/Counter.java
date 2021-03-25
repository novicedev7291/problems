package com.kuldeep.file;

import java.nio.file.Path;
import java.util.concurrent.Callable;

import static com.kuldeep.file.ReadUtil.wordsCounter;

class Counter implements Callable<Long> {
    private final Path file;
    private final Long maxBuffer;
    private final Long startPosition;

    Counter(Path file, Long maxBuffer, Long startPosition) {
        this.file = file;
        this.maxBuffer = maxBuffer;
        this.startPosition = startPosition;
    }


    @Override
    public Long call() throws Exception {
        return wordsCounter().apply(new ReadParams(file, maxBuffer, startPosition));
    }
}
