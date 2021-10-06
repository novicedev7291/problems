package com.kuldeep.algorithims;


import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Encryption {

    // Complete the encryption function below.
    static String encryption(String s) {
        StringBuffer sb = new StringBuffer();
        List<Character> l = new ArrayList<>();
        int len = s.length();
        for(int i = 0; i < len; i++){
            char c = s.charAt(i);
            if(c == ' '){
                continue;
            }
            l.add(c);
        }
        int letters = l.size();
        int m = (int) Math.floor(Math.sqrt(letters));
        int n = (int) Math.ceil(Math.sqrt(letters));
        if(m < n && m*n < letters){
            m += 1;
        }
        for(int i = 0; i < n; i++){
            for(int j = 0; j < m; j++){
                int pos = j * n + i;
                if(pos < l.size())
                    sb.append(l.get(pos));
            }
        }

        return sb.toString();
    }


    public static void main(String[] args) throws IOException {
        try(BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));
            Scanner scanner = new Scanner(System.in)) {

            String s = scanner.nextLine();

            String result = encryption(s);

            bufferedWriter.write(result);
            bufferedWriter.newLine();
        }
    }
}

