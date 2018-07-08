package com.kuldeep.sorting;
/*
 * Worst case - O(n2) (if array is already sorted)
 * Best case - O(nlogn)
 * Sort step would take O(n) time to sort the array and this divides the array in two
 * equal parts and it would take to solve two arrays 2*O(n/2), continuing the steps
 * would increase it would result in log n steps therefore O(nlog n).
 * For step reference - https://stackoverflow.com/questions/9152890/what-would-cause-an-algorithm-to-have-olog-n-complexity.
 * In worst case it picks one element on each iteration,
 * so it is O(n) + O(n-1) + (On-2).. O(1) which is equal to O(n^2).
 */
public class QuickSort {
	private int[] nums;
	
	public QuickSort(int[] arr) {
		if(arr == null || arr.length == 0){
			throw new IllegalArgumentException();
		}
		nums = arr;
	}
	
	private void sort(int low, int high){
		int i = low, j = high;
		
		//Pivot element is assumed to be in mid
		int pivot = low + (high - low)/2;
		
		/*
		 * Loop over the array till starting point(i) and end point(j) are collided.
		 */
		while(i <= j){
			/*
			 * move forward from low to high until number greater than pivot found.
			 */
			while(nums[i] < nums[pivot]){
				i++;
			}
			/*
			 * Move backward from high to low until number smaller than pivot found
			 */
			while(nums[j] > nums[pivot]){
				j--;
			}
			
			if(i <= j){
				swap(i,j);
				i++;
				j--;
			}
		}
		/*
		 * At a point when i and j crosses each other
		 * , we need to divide the array into two subproblems and then repeat above
		 * steps again.
		 */
		if(low < j)
			sort(low, j);
		if(i < high){
			sort(i, high);
		}
	}
	
	private void swap(int i, int j){
		int t = nums[i];
		nums[i] = nums[j];
		nums[j] = t;
	}
	
	public int[] sort(){
		sort(0, nums.length-1);
		return nums;
	}

}
