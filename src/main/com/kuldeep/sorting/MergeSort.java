package com.kuldeep.sorting;

/*
 * Worst case - O(n log n)
 * Slower than quicksort in average cases because of copying the array into 
 * new array.
 */
public class MergeSort {
	/*
	 * Recursive call to partition the array and then sort.
	 */
	public void sort(int[] arr, int l, int h){
		if(h >= l){
			return;
		}
		int mid = l + (h - l)/2;
		sort(arr, 0, mid);
		sort(arr, mid+1, h);
		merge(arr, l, mid, h);
	}
	/*
	 * Left side and right side array is virtual arrays here, there is actually left side and right 
	 * side partitioned by the mid element
	 */
	public void merge(int[] arr, int low, int mid, int high){
		int[] helper = new int[arr.length];
		for(int i = 0; i<high; i++){
			helper[i] = arr[i];
		}
		
		/*
		 * Indexes to loop over helper array
		 * i -> to track the indexes of left side array
		 * j -> to track indexes from mid onwards (right side)
		 * k -> to track the indexes of final array that would come out sorted
		 */
		int i = low, j = mid+1, k = low;
		//loop to compare elements in partition array
		while(i <= mid && j <= high){
			if(helper[i] <= helper[j]){
				arr[k] = helper[i];
				i++;
			}
			else{
				arr[k] = helper[j];
				j++;
			}
			k++;
		}
		
		//To copy left over elements in left side array
		while(i <= mid){
			arr[i] = helper[k];
			k++;
			i++;
		}
		
	}

}
