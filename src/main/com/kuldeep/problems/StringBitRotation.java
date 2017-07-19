package com.kuldeep.problems;

import java.util.Scanner;

public class StringBitRotation {
	public static void main(String[] args){
		int t,n;
		Scanner s = new Scanner(System.in);
		t = s.nextInt();
		
		while(t-- > 0){
			n = s.nextInt();
			s.nextLine();
			String seq = s.nextLine();
			int ans = 0;
			for(int i = 0; i < seq.length(); i++){
				if(seq.charAt(i) == '0') ans++; 
			}
			System.out.println(ans);
		}
	}

}
