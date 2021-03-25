package com.kuldeep.file;

import java.nio.file.Path;

class ReadParams {
    private final Path path;
    private final Long maxBuffer;
    private final Long startPosition;

    ReadParams(Path path, Long maxBuffer, Long startPosition) {
        this.path = path;
        this.maxBuffer = maxBuffer;
        this.startPosition = startPosition;
    }

    public Path getPath() {
        return path;
    }

    public Long getMaxBuffer() {
        return maxBuffer;
    }

    public Long getStartPosition() {
        return startPosition;
    }
}
