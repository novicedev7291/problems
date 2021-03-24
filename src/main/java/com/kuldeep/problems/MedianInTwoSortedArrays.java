package com.kuldeep.problems;

public class MedianInTwoSortedArrays {
    public static void main(String[] args) {
        int[] a = {1};
        int[] b = {2, 4, 6, 7, 8};
        System.out.println(new MedianInTwoSortedArrays().median(a, b));
    }

    public float median(int[] a, int[] b){
        return median(a, b, 0, a.length - 1, 0, b.length -1);
    }

    public float median(int[] a, int[] b, int l1, int h1, int l2, int h2){
        int n1 = (h1 - l1 + 1);
        int n2 = (h2 - l2 + 1);

        if(h1 < l1) {
            int mid = l2 + (h2 - l2)/2;
            return n2 % 2 == 0 ? (b[mid] + b[mid+1])/2 : b[mid];
        }
        else if(h2 < l2){
            int mid = l1 + (h1 - l1)/2;
            return n1 % 2 == 0 ? (a[mid] + a[mid+1])/2 : a[mid];
        }

        if(n1 == 1 && n2 == 1) return (a[l1] + b[l2])/2;

        int mid1 = l1 + (h1 - l1)/2;
        int mid2 = l2 + (h2 - l2)/2;

        if(mid1 > mid2){
            // We can discard elements greater then median (right side) in a and elements smaller than median (left side) in array b
            return median(a, b, l1, mid1 , mid2 + 1, h2);
        }
        else if(mid1 < mid2)
            // When m2 greater than m1
            return median(a, b, mid1 + 1 , h1, l2, mid2 );
        return mid1;

    }
}
