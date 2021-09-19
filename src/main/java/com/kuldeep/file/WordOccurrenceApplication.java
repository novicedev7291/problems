package com.kuldeep.file;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Duration;
import java.time.Instant;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

/**
 * @author <a href="kuldeepyadav7291@gmail.com">Kuldeep</a>
 */
public class WordOccurrenceApplication {
    private static final Logger logger = LoggerFactory.getLogger(WordOccurrenceApplication.class);

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        String word = "String";
        String folder = "java-source";
        List<Path> files = descendants(Paths.get(System.getProperty("user.home"), folder));

        ExecutorService executor = Executors.newCachedThreadPool();
        List<Callable<Long>> counterTasks = files.stream()
                                                 .map(path -> (Callable<Long>) () -> occurrences(word, path))
                                                 .collect(toList());

        Instant startTime = Instant.now();
        List<Future<Long>> results = executor.invokeAll(counterTasks);
        long total = 0;
        for (Future<Long> result : results) total += result.get();
        Instant endTime = Instant.now();

        logger.info("Total {} occurrences {}", word, total);
        logger.info("Took total time {}ms", Duration.between(startTime, endTime).toMillis());


        List<Callable<String>> tasks = files.stream()
                                            .map(path -> searchFor(word, path))
                                            .collect(toList());

        String found = executor.invokeAny(tasks);

        logger.info("{} found in {}", word, found);

        executor.shutdown();
    }

    private static long occurrences(String word, Path path) {
        try (Scanner scanner = new Scanner(path)) {
            long occurrences = 0;
            while (scanner.hasNext()) {
                if (scanner.next().equals(word)) occurrences++;
            }
            return occurrences;
        } catch (IOException ex) {
            throw new UncheckedIOException(ex);
        }
    }

    private static Callable<String> searchFor(String word, Path path) {
        return () -> {
            try (Scanner scanner = new Scanner(path)) {
                logger.debug("Searching in path {}", path);
                while (scanner.hasNext()) {
                    if (scanner.next().equals(word)) return path.toAbsolutePath().toString();
                    if (Thread.currentThread().isInterrupted()) {
                        logger.info("Interrupted, aborting!!!");
                        return null;
                    }
                }
            }
            throw new NoSuchElementException();
        };
    }

    private static List<Path> descendants(Path path) {
        try (Stream<Path> paths = Files.walk(path)) {
            return paths.filter(Files::isRegularFile).collect(toList());
        } catch (IOException ex) {
            throw new UncheckedIOException(ex);
        }
    }
}

class UncheckedIOException extends RuntimeException {
    public UncheckedIOException(Throwable cause) {
        super(cause);
    }
}
