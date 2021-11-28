package com.kuldeep.algorithims;

import java.util.ArrayList;
import java.util.List;

/**
 * @author <a href="kuldeepyadav7291@gmail.com">Kuldeep</a>
 */
class LIS {

    private int lowerBound(List<Integer> lis, int r, int key) {
        int l = -1;
        while ((r - l) > 1) {
            int mid = l + (r - l) / 2;
            if (key <= lis.get(mid)) {
                r = mid;
            }else {
                l = mid;
            }
        }

        return r;
    }

    public int maxLengthLIS(int[] arr) {

        List<Integer> lis = new ArrayList<>();
        int n = arr.length;

        for (int i = 0; i < n; i++) lis.add(0);

        lis.set(0, arr[0]);
        int length = 1;

        for (int i = 1; i < n; i++) {
            if (arr[i] < lis.get(0))
                lis.set(0, arr[i]);
            else if (arr[i] > lis.get(length - 1)) {
                lis.set(length, arr[i]);
                length++;
            }else {
                int idx = lowerBound(lis, length - 1, arr[i]);
                lis.set(idx, arr[i]);
            }
        }

        return length;
    }
}
