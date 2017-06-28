package com.kuldeep.problems;

public class MedianIn2EqualArray {

	private int[] nums1, nums2;

	
	public int findMedian(int[] arr1, int[] arr2, int n){
		if(n <= 0){
			return 0;
		}
		if(n == 1)
			return (arr1[0] + arr2[0])/2;
		if(n == 2)
			return (Math.max(arr1[0], arr2[0]) + Math.min(arr1[1], arr2[1]))/2;
		
		int m1 = median(arr1, n);
		int m2 = median(arr2, n);
		
		if(m1 == m2) return m1;
		
		if(m1 < m2){
			if(n % 2 == 0){
				int l = n/2;
				int[] temp = new int[l];
				int[] temp1 = new int[l];
				System.arraycopy(arr1, l, temp, 0, l);
				System.arraycopy(arr2, 0, temp1, 0, l);
				return findMedian(temp, temp1, n/2);
			}
			int l = n/2;
			int[] temp = new int[l];
			int[] temp1 = new int[l];
			System.arraycopy(arr1, l+1, temp, 0, l);
			System.arraycopy(arr2, 0, temp1, 0, l);
			return findMedian(temp, temp1, n/2);
		}
		
		if(n % 2 == 0){
			int l = n/2;
			int[] temp = new int[l];
			int[] temp1 = new int[l];
			System.arraycopy(arr1, 0, temp, 0, l);
			System.arraycopy(arr2, l, temp1, 0, l);
			return findMedian(temp, temp1, n/2);
		}
		int l = n/2;
		int[] temp = new int[l];
		int[] temp1 = new int[l];
		System.arraycopy(arr1, 0, temp, 0, l);
		System.arraycopy(arr2, l+1, temp1, 0, l);
		return findMedian(temp, temp1, n/2);
	}
	
	int median(int[] arr, int n){
		if(n%2 == 0){
			return (arr[n/2] + arr[n/2-1])/2;
		}else{
			return arr[n/2];
		}
	}
	
	public static void main(String[] args){
		int[] nums1 = {1,2,3,4,5,54};
		int[] nums2 = {8,9,10,11,12,59};
		
		MedianIn2EqualArray o = new MedianIn2EqualArray();
		
		System.out.println(o.findMedian(nums1, nums2, nums1.length));
	}
}
