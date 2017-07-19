package com.kuldeep.problems;

import java.util.Scanner;

public class FactorialProblem {
	public static void main(String[] args){
		Scanner s = new Scanner(System.in);
		FactorialProblem f = new FactorialProblem();
		try{
			int n = s.nextInt();
			int c = s.nextInt();
			System.out.println(f.ncn(n, c));
		}catch (Exception e) {
			
		}
	}
	
	public int factByRecursion(int n){
		if(n == 1){
			return 1;
		}
		return n*factByRecursion(n-1);
	}
	
	public int ncn(int n, int c){
		int p = 1;
		int q = n;
		for(int i = 0; i < c; i++){
			p*=q--;
		}
		return p/(factByRecursion(n-c)*factByRecursion(c));
	}

}
