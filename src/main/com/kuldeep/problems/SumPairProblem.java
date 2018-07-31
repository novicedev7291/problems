package com.kuldeep.problems;

import com.kuldeep.sorting.QuickSort;


public class SumPairProblem {
	private int[] arr;
	
	public SumPairProblem(int[] arr) {
		this.arr = arr;
	}

	/*
	 * Depends on the sorting algorithim used, if uses quicksort then worst 
	 * case it would be O(n2) and if merge sort then O(n log n).
	 */
	public boolean isPairOfGivenSumExist(int sum){
		boolean isExist = false;
		arr = new QuickSort(arr).sort();
		int i = arr.length - 1, j = 0;
		while( j < i){
			if(arr[i] + arr[j] == sum) return true;
			else if(arr[i]+arr[j] < sum) j++;
			else i--;
		}
		
		return isExist;
	}
	/*
	 * If range of numbers is known then O(n)
	 */
	public boolean pairUsingMap(int sum){
		boolean isExist = false;
		boolean[] binMap = new boolean[Integer.MAX_VALUE];
		
		/*
		 * In beginning mark all false
		 */
		for(int i = 0; i < Integer.MAX_VALUE; i++){
			binMap[i] = false;
		}
		
		for(int i = 0; i < arr.length; i++){
			int temp = sum - arr[i];
			
			if(binMap[temp]){
				System.out.println("Pairs are : " + arr[i] + ","+temp);
				isExist = true;
			}
			/*
			 * On each iteration mark index which is element in array true
			 * and check every time if sum - arr[currentItem] index has already
			 * been marked true, then this is our pair. 
			 */
			binMap[arr[i]] = true;
		}
		
		
		
		return isExist;
	}
}
