package com.kuldeep.problems;

/**
 * To find maximum difference between two elements such that smallest number appears before largest number.
 * @author mario
 */
public class MaxSumForAltSortedElements {

	public static void main(String[] args){
		int[] arr = {-10,0,1,2,-10,24};
		int[] sumArr = {-10,1,1,-12,14};
		
		int max_diff = -1,l = arr.length;
		int max_element = arr[l-1];
		
		for(int i = l-2; i >= 0; i--){
			if(arr[i] > max_element){
				max_element = arr[i];
			}
			else{
				int diff = max_element - arr[i];
				if(diff > max_diff){
					max_diff = diff;
				}
			}
		}
		
		System.out.println(max_diff);
	}
}
