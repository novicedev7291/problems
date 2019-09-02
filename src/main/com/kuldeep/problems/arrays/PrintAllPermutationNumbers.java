package com.kuldeep.problems.arrays;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class PrintAllPermutationNumbers {
    public static void main(String[] args) {
        int[] nums = {1,1,0,1,0};
        new PrintAllPermutationNumbers().permutations(nums);
    }

    public void permutations(int[] nums){
        Map<Integer, Integer> countMap = new HashMap<>();
        int n = nums.length, i;
        for(i = 0; i < n; i++){
            countMap.compute(nums[i], (key, val) -> {
                if(val == null)
                    return 1;
                else
                    return val + 1;
            });
        }
        int uN = countMap.size();
        int[] arr = new int[uN];
        int[] count = new int[uN];
        i = 0;
        for(Map.Entry<Integer, Integer> e : countMap.entrySet()){
            arr[i] = e.getKey();
            count[i] = e.getValue();
            i++;
        }
        int[] results = new int[n];
        permuteHelper(arr, count, results, 0);
    }

    void permuteHelper(int[] arr, int[] count, int[] results, int i){
        if(i == results.length){
            print(results);
            return;
        }

        for(int k = 0; k < arr.length; k++){
            if(count[k] == 0){
                continue;
            }
            results[i] = arr[k];
            count[k]--;
            permuteHelper(arr, count, results, i + 1);
            count[k]++;
        }
    }

    void print(int[] nums){
        System.out.println(Arrays.toString(nums));
    }
}
