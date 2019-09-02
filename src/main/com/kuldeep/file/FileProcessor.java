package com.kuldeep.file;

import java.io.*;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.Level;
import java.util.logging.Logger;

public class FileProcessor {
    public final static Logger log = Logger.getLogger(FileProcessor.class.getName());
    public void read(String path){
        ExecutorService tPool = Executors.newFixedThreadPool(10);
        try(BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(path)))){
            InputStream is;

        } catch (FileNotFoundException e) {
            log.log(Level.SEVERE, e.getMessage());
        } catch (IOException e) {
            log.log(Level.SEVERE, e.getMessage());
        }
    }
}
