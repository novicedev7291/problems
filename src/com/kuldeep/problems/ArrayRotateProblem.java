package com.kuldeep.problems;

public class ArrayRotateProblem {

	public void rotateArray(int[] arr, int d, int n){
		int i,j,k,temp;
		
		int l = findGCD(d, n);
		
		for(i = 0; i < l; i++){
			temp = arr[i];
			j = i; 
			while(1 != 0){
				k = j+d;
				if(k >= n) k = k - n; //This step ensures that if k jumps over the array while increment we need to subtract number of elems n to check if all elements are covered in this pass.
				if(k == i) break; //if all elements are checked then stops further swapping and move on to next pass.
				arr[j] = arr[k];
				j = k;
			}
			arr[j] = temp;
		}
		
		for(i = 0; i < n; i++){
			System.out.print(arr[i] + "\t ");
		}
	}

	
	int findGCD(int a, int b){
		if(b == 0) return a;
		return findGCD(b, a % b);
	}
	
	public void rotateArrayByIterativeDivideApproach(int[] arr, int d, int n){
		int i,j;
		
		if(d == 0 || d == n) return;
		i = d;
		j =  n - d;
		
		while(i != j){
			if(i < j){
				swap(arr, d-i, j, i);
				j -= i;
			}else{
				swap(arr, d-i, d,j);
				i -= j;
			}
		}
		swap(arr, d-i, d, i);
	}
	
	void swap(int[] arr, int fi, int si, int d){
		int i,temp;
		for(i = 0 ; i < d; i++){
			temp = arr[si+i];
			arr[si+i] = arr[fi+i];
			arr[fi+i] = temp;
		}
	}
	
	public static void main(String[] args){
		ArrayRotateProblem o = new ArrayRotateProblem();
		int[] arr = {1,2,3,4};
		o.rotateArrayByIterativeDivideApproach(arr, 2, arr.length);
		
		System.out.println();
		for(int i = 0 ;i < arr.length; i++){
			System.out.print(arr[i] + "\t ");
		}
	}
}
