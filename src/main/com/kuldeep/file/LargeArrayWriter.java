package com.kuldeep.file;

import java.io.*;
import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.Level;
import java.util.logging.Logger;

public class LargeArrayWriter {
    public final static Logger log = Logger.getLogger(LargeArrayWriter.class.getName());
    //final CopyOnWriteArrayList<String> paths = new CopyOnWriteArrayList<>();
    final List<String> paths = new ArrayList();
    public static void main(String[] args) {
        try{
            String s = "vishal";
            String t = "hal";
            System.out.println(s.indexOf(t));


            /*LargeArrayWriter o = new LargeArrayWriter();
            String filePath = o.write();
            o.read(filePath);
            o.paths.stream().forEach(path -> {
                System.out.println(path);
            });*/

        }finally {

        }

    }

    public void read(String absPath){
        int chunkSize = 10000;
        int total = 100000;
        int[] arr = new int[chunkSize];
        ExecutorService pool = Executors.newFixedThreadPool(10);
        try(InputStream is = new FileInputStream(absPath)){
            for(int i = 0 ; i < (total/chunkSize); i++){
                for(int j = 0; j < chunkSize; j++){
                    arr[j] = readInt(is);
                }
                TempFileCreator t = new TempFileCreator(arr);
                pool.submit(t);
            }
        }catch (IOException e){
            log.log(Level.SEVERE, e.getMessage());
        }
        pool.shutdown();
    }

    private int readInt(InputStream is) throws IOException {
        int num = 0;
        boolean isDigit = false;
        for(int c = 0; (c=is.read()) != -1;){
            if(c >= '0' && c <= '9'){
                isDigit = true;
                num = num * 10 + (c - '0');
            }
            else if(isDigit) break;
        }
        return num;
    }

    public String write() throws IOException {
        int size = 100000, i;
        File file = File.createTempFile("arrays-", ".tmp");
        log.info(file.getAbsolutePath());
        BufferedWriter bw = new BufferedWriter(
                new OutputStreamWriter(
                        new FileOutputStream(file)));
        List<Integer> l = new ArrayList<>(size);
        for(i = 1; i < size+1; i++){
            l.add(i);
        }

        Random rm = new Random();
        for(i = 0; i < size; i++){
            int num = l.get(rm.nextInt(size));
            bw.write(""+num);
            bw.write("\t");
        }
        return file.getAbsolutePath();
    }

    class TempFileCreator implements Runnable{
        private int[] arr;
        TempFileCreator(final int[] arr){
            this.arr = arr;
        }

        @Override
        public void run() {
            File file = null;
            try{
                file = File.createTempFile("arrays-", ".tmp");
            }catch (IOException e){
                log.log(Level.SEVERE, e.getMessage());
                return;
            }
            log.info(file.getAbsolutePath());
            try(BufferedWriter bw = new BufferedWriter(
                    new OutputStreamWriter(
                    new FileOutputStream(file)))){

                for(int i = 0; i < arr.length; i++){
                    bw.write(""+arr[i]);
                    bw.write("\t");
                }
                paths.add(file.getAbsolutePath());

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
