package com.kuldeep.problems;

import java.util.ArrayList;
import java.util.Scanner;

public class PrintCommaSeparated {
    static Scanner sc = new Scanner(System.in);

    void printCommaSeparated(ArrayList<Integer> a)
    {
        for(int i = 0; i < a.size(); i++){
            if(i == a.size() - 1) System.out.printf("%s", a.get(i));
            else
                System.out.printf("%s, ", a.get(i));
        }
    }

    public static void main(String[] args) {
        PrintCommaSeparated bz = new PrintCommaSeparated();
        int t = sc.nextInt();
        while(t--!=0){
            int n = sc.nextInt();
            ArrayList<Integer> al = new ArrayList<>();
            for(int i=0;i<n;i++)
                al.add(sc.nextInt());
            bz.printCommaSeparated(al);
            System.out.println();
        }
    }
}
