package com.kuldeep.file;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

class Indexer extends Thread{
    private static final Logger log = LoggerFactory.getLogger(Thread.currentThread().getName());

    private final Path indexFile;
    private final BlockingQueue<String> paths;

    Indexer(Path indexFile, BlockingQueue<String> paths) {
        this.indexFile = indexFile;
        this.paths = paths;
    }

    @Override
    public void run() {
        File file = indexFile.toFile();
        try(FileWriter writer = new FileWriter(file, true)) {
            while (!Thread.currentThread().isInterrupted()) {
                String filename = paths.poll(5, TimeUnit.SECONDS);

                if(filename == null)
                    break;

                log.info("Adding file {} to index...", filename);
                writer.write(filename);
                writer.write(System.lineSeparator());

            }
        } catch (InterruptedException | IOException e) {
            Thread.currentThread().interrupt();
        }
    }
}
