package com.kuldeep.library;

import java.io.IOException;

public class HttpBuildTest {

	public static void main(String[] args){
		String s = "{\"userName\":\"vishal\",\"password\":\"123456\"}";
		try {
			String response = new HttpBuilder()
							.setUrl("http://api.frood.tech/v1/login")
							.setDoOutPut(true)
							.setRequestMethod("POST")
							.build()
							.write(s)
							.extract();
			System.out.println(response);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
