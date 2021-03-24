package com.kuldeep.problems;

public class MaxSumInArray {
	private int[] nums;
	private int n;
	
	public MaxSumInArray(int[] nums) {
		this.nums = nums;
		this.n = nums.length;
	}
	/*
	 * Time complexity O(n)
	 */
	public int findMaxSumOfSubArray(){
		int maxSoFar = 0, maxEndHere = 0;
		int start = 0, end = 0, s = 0;
		
		for(int i = 0; i < n; i++){
			maxEndHere += nums[i];
			if(maxEndHere < 0){
				maxEndHere = 0;
				s = i + 1;
			}
			else if(maxSoFar < maxEndHere){
				maxSoFar = maxEndHere;
				end = i;
				start = s;
			}
		}
		System.out.println(start + "," + end);
		
		return maxSoFar;
	}
	
	public static void main(String[] args){
		int[] arr = {1,-2,4,0,3,7,-1};
		MaxSumInArray obj = new MaxSumInArray(arr);
		//System.out.println(1^2^3^4);
		//System.out.println(1^2^4);
	}
}
