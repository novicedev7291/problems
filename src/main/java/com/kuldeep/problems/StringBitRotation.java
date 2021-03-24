package com.kuldeep.problems;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class StringBitRotation {
	public static void main(String[] args)
	{
		Scanner sc = new Scanner(System.in);
		int t = sc.nextInt();
		List<String> input = new ArrayList<>();
		sc.nextLine();
		while(t-- > 0){
			String aSpaceB = sc.nextLine();
			input.add(aSpaceB);
		}

		for(String aSpaceB : input){
			char[] str = chars(aSpaceB);
			reverse(str, 0, str.length-1);
			reversePartOfString(str);
			System.out.println(aSpaceB);
			System.out.println(new StringBuilder().append(str).toString());
			System.out.println();
		}


	}

	private static void reversePartOfString(char[] str) {
		int start=0,i = 0, end=0;
		for(; i < str.length; i++){
			if(str[i] == ' '){
				end = i - 1;
				reverse(str, start, end);
				start = i + 1;
				continue;
			}
		}

		if(end < str.length){
			reverse(str, start, str.length-1);
		}
	}


	private static char[] chars(String s){
		int l = s.length();
		char[] str = new char[l];

		for(int i = 0 ; i < l; i++){
			str[i] = s.charAt(i);
		}
		return str;
	}

	private static void reverse(char[] str, int start, int end){
		for(int i = start, j = end; i <= start + (end-start)/2; i++, j--){
			char t = str[i];
			str[i] = str[j];
			str[j] = t;
		}
	}

}
