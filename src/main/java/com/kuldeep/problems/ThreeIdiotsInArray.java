package com.kuldeep.problems;

public class ThreeIdiotsInArray {
    public static void main(String[] args) {
        int[] arr = {1,2,4};
        int count = 1;

        for(int i = 0; i < arr.length-1; i++){
            if(count >= 3){
                break;
            }
            else if(arr[i+1] == arr[i] + 1){
                count++;
                continue;
            }
            count = 1;
        }

        System.out.println(count >= 3? true: false);
    }
}
