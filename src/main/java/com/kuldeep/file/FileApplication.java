package com.kuldeep.file;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import static com.kuldeep.file.ReadUtil.wordsCounter;

public class FileApplication {
    private static final Logger log = LoggerFactory.getLogger(FileApplication.class);
    public static void main(String[] args) throws IOException, InterruptedException, ExecutionException {
        Path path = FileSystems.getDefault().getPath(System.getProperty("user.home"), "Downloads", "large", "bible.txt");
        BasicFileAttributes attrs = Files.readAttributes(path, BasicFileAttributes.class, LinkOption.NOFOLLOW_LINKS);
        long fileSize = attrs.size(); //1024L is taken randomly, it may not give correct result

        Set<Counter> counters = new HashSet<>();

        long temp = fileSize;
        long maxBytes = 1000000L;
        long position = 0L;
        Long totalWC = 0L;

        long startTime = System.currentTimeMillis();

        while ( temp > 0 && (position + maxBytes) < fileSize ) {
            counters.add(new Counter(path, maxBytes, position));
            position += maxBytes;
            temp = temp - maxBytes;
        }
        counters.add(new Counter(path, temp, position));

        ExecutorService service = Executors.newFixedThreadPool(counters.size());
        List<Future<Long>> results = service.invokeAll(counters);


        for (Future<Long> each: results) {
            totalWC += each.get();
        }

        log.info("Final count of words : {}", totalWC);

        service.shutdown();

        log.info("Completed using threads (threads : {}) in {}ms", counters.size(),
                (System.currentTimeMillis() - startTime));

        //Doing in one thread

        startTime = System.currentTimeMillis();
        totalWC = wordsCounter().apply(new ReadParams(path, fileSize, 0L));
        log.info("Completed using single thread in {}ms", (System.currentTimeMillis() - startTime));

        log.info("Final count of words using single thread : {}", totalWC);
    }
}
