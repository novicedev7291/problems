package com.kuldeep.file;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileFilter;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

class IndexCrawler implements Runnable{
    private static final Logger log = LoggerFactory.getLogger(Thread.currentThread().getName());

    private final File directory;
    private final FileFilter filter;
    private final BlockingQueue<String> paths;

    IndexCrawler(File directory, FileFilter filter, BlockingQueue<String> paths) {
        this.directory = directory;
        this.filter = filter;
        this.paths = paths;
    }

    @Override
    public void run() {
        try {
            addFileName(directory);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    private void addFileName(File file) throws InterruptedException {
        if(file.isDirectory()) {
           File[] filesInDirectory = file.listFiles(filter);

           if(filesInDirectory != null) {
               for(File temp : filesInDirectory) {
                   addFileName(temp);
               }
           }

           return;
        }
        log.info("Found new file...");
        paths.offer(file.getName(), 2, TimeUnit.SECONDS);
    }
}
