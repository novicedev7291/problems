package com.kuldeep.problems;

import java.util.Scanner;

/**
 * a^b%c problem (Modulo exponentiation) 
 * @author mario
 */
public class ModuloProblem {

	public static void main(String[] args){
		
		Scanner s = new Scanner(System.in);
		int a = s.nextInt();
		int b = s.nextInt();
		int c = s.nextInt();
		long temp = 1;
		long starttime = System.currentTimeMillis();
		for(int i = 0 ; i < b; i++){
			temp *= a;
		}
		
		hackerEarthNaiveSol(a, b, c);
		hackerEarthEfficientSol(a, b, c);
		
	}
	/**
	 * O(b) complexity
	 * @param a number to be multiplied
	 * @param b number of times a to be multiplied
	 * @param c take modulo by
	 */
	public static void hackerEarthNaiveSol(int a, int b, int c){
		long ans = 1;
		for(int i = 0; i < b; i++){
			ans *= a;
			ans %= c;
		}
		
		System.out.println(ans);
		
	}
	
	/**
	 * O(log(b)) complexity
	 * @param a
	 * @param b
	 * @param c
	 */
	public static void hackerEarthEfficientSol(int a, int b, int c){
		long ans = 1;
		
		while(b != 0){
			//check the right most digit of divisor if it is 1 then multiply ans with a and reduce if 
			//greater than c
			if(b%2 == 1){
				ans *= a;
				ans %= c;
			}
			a *= a; // At each iteration a is multiplied as many times as 2^power X significant bit in binary b
			a %= c; //reduce a if greater than c
			b /= 2; //trim the right most digit of b
		}
		System.out.println(ans);
	}
}
