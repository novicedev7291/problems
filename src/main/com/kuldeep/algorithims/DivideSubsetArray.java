package com.kuldeep.algorithims;

import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.function.*;
import java.util.regex.*;
import java.util.stream.*;
import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;

class Result {

    /*
     * Complete the 'nonDivisibleSubset' function below.
     *
     * The function is expected to return an INTEGER.
     * The function accepts following parameters:
     *  1. INTEGER k
     *  2. INTEGER_ARRAY s
     */

    public static int nonDivisibleSubset(int k, List<Integer> s) {
        Map<Integer, List<Integer>> remM = new TreeMap();
        for(Integer el : s){
            int rem = el % k;
            List<Integer> l = remM.get(rem);
            if(l == null){
                l = new ArrayList();
                remM.put(rem, l);
            }
            l.add(el);
        }
        int count = 0;
        Set<Integer> rKeys = remM.keySet();
        Map<Integer, Integer> m = new HashMap();
        for(Integer el : rKeys){
            if(el == 0)
                count += 1;
            else if(el == (k-el)){
                count += remM.get(el).size();
            }
            else
                m.put(el, k-el);
        }

        for(Map.Entry<Integer, Integer> e: m.entrySet()){
            int i = e.getKey();
            int j = e.getValue();
            if(i != j){
                if(remM.containsKey(i) && remM.containsKey(j)){
                    count += Math.max(remM.get(i).size(), remM.get(j).size());
                    remM.remove(j);
                }
                else if(!remM.containsKey(j)){
                    count += remM.get(i).size();
                }
            }
        }
        return count;
    }

}

public class DivideSubsetArray {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
       // BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        String[] firstMultipleInput = bufferedReader.readLine().replaceAll("\\s+$", "").split(" ");

        int n = Integer.parseInt(firstMultipleInput[0]);

        int k = Integer.parseInt(firstMultipleInput[1]);

        List<Integer> s = Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
                .map(Integer::parseInt)
                .collect(toList());

        int result = Result.nonDivisibleSubset(k, s);
        System.out.println(result);

        //bufferedWriter.write(String.valueOf(result));
        //bufferedWriter.newLine();

        bufferedReader.close();
        //bufferedWriter.close();
    }
}

