package com.kuldeep.file;

import java.nio.file.Path;

class ReadParams {
    private final Path path;
    private final Long maxBytes;
    private final Long startPosition;

    ReadParams(Path path, Long maxBytes, Long startPosition) {
        this.path = path;
        this.maxBytes = maxBytes;
        this.startPosition = startPosition;
    }

    public Path getPath() {
        return path;
    }

    public Long getMaxBytes() {
        return maxBytes;
    }

    public Long getStartPosition() {
        return startPosition;
    }
}
