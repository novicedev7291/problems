package com.kuldeep.file;

import java.io.File;
import java.io.FileFilter;
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

public class BlockingQueueApp {
    public static void main(String[] args) {
        FileSystem fileSystem = FileSystems.getDefault();
        File directory = fileSystem.getPath("/Users", "ni3", "Desktop", "personal", "temp")
                                   .toFile();
        Path indexFile = fileSystem.getPath("/Users", "ni3", "Desktop", "personal", "temp", "index.txt");

        FileFilter filterHidden = file -> !file.isHidden()
                || !file.getName().endsWith(".class")
                || !file.getName().startsWith(".git")
                || !file.getName().startsWith(".idea")
                || !file.getName().endsWith(".iml");

        final BlockingQueue<String> paths = new LinkedBlockingQueue<>(20);

        Thread crawler = new Thread(new IndexCrawler(directory, filterHidden, paths));
        Indexer indexer = new Indexer(indexFile, paths);


        try {
            crawler.start();

            TimeUnit.SECONDS.sleep(1);

            indexer.start();

            indexer.join(10000);

        } catch (InterruptedException e) {
            e.printStackTrace();
            throw new IllegalStateException(e);
        }

    }
}
