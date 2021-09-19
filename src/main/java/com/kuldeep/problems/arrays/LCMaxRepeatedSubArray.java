package com.kuldeep.problems.arrays;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/**
 * @author <a href="kuldeepyadav7291@gmail.com">Kuldeep</a>
 */
class LCMaxRepeatedSubArray {
    public static void main(String[] args) {
        LCMaxRepeatedSubArray o = new LCMaxRepeatedSubArray();
        System.out.println(o.findLen(new int[]{0,1,1,1,1}, new int[]{1,0,1,0,1}));
        System.out.println('1' + '9');

        Set<String> seen = new HashSet<>();
        seen.add("ddddddddddg");
        seen.add("dgggggggggg");


        System.out.println(seen.size());

    }

    public int findLength(int[] nums1, int[] nums2) {
        return findLen(nums1, nums2);
    }

    private int findLen(int[] nums1, int[] nums2) {

        return dfs(nums1, nums2, 0, 0);
    }

    private int dfs(int[] A,  int[] B, int i, int j) {
        if(i >= A.length || j >= B.length) return 0;

        if(A[i] == B[j]) {
            return 1 + dfs(A, B, i + 1, j + 1);
        }

        return Math.max(dfs(A, B, i + 1, j), dfs(A, B, i, j+1));
    }
}
