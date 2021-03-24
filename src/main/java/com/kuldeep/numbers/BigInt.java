package com.kuldeep.numbers;

import java.math.BigInteger;

public class BigInt {

	private int[] arr;
	
	public BigInt(String num) {
		int length = num.length();
		arr = new int[length];
		BigInteger b = new BigInteger(num);
		for(int i = length - 1 ; i >= 0; i--){
			
		}
	}
	
	public BigInt(int num){
		
	}
}
