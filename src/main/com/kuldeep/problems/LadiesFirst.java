package com.kuldeep.problems;

public class LadiesFirst {
    public static void main(String[] args) {
        char[] arr = {'G','G','L','L','L','G'};

        int i = 0, l = arr.length, j = l - 1;

        while(true){
            while(i < l){
                if(arr[i] == 'G') {
                    break;
                }
                i++;
            }

            while(j >= 0){
                if(arr[j] == 'L') {
                    break;
                }
                j--;
            }
            if(i > j){
                break;
            }
            swap(arr, i, j);

        }

        for(int k = 0; k < l; k++){
            System.out.printf("%s", arr[k]);
        }
    }

    private static void swap(char[] arr, int i, int j){
        //System.out.printf("%s %s", arr[i], arr[j]);
        //System.out.println();
        char t = arr[i];
        arr[i] = arr[j];
        arr[j] = t;
    }
}
