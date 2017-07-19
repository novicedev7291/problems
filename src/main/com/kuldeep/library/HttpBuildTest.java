package com.kuldeep.library;

import java.io.IOException;
import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ws.rs.HttpMethod;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.junit.Assert;
import org.junit.Test;

public class HttpBuildTest {
	
	private class User implements Serializable{
		private String userName;
		private String password;
		
		public User(String username, String password) {
			this.userName = username;
			this.password = password;
		}
		
		public String getUserName() {
			return userName;
		}
		public String getPassword() {
			return password;
		}
	}
	
	@Test
	public void testPostCall(){
		String s = "{\"userName\":\"vishal\",\"password\":\"123456\"}";
		try {
			String response = new HttpBuilder()
							.setUrl("http://api.frood.tech/v1/login")
							.setDoOutPut(true)
							.setRequestMethod("POST")
							.build()
							.send(s)
							.extract();
			System.out.println(response);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ApiException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			 
		}
	}
	
	@Test
	public void testPostWithObject(){
		User user = new User("vishal", "123456");
		try {
			XToken token = new HttpBuilder()
							.setUrl("http://api.frood.tech/v1/login")
							.setDoOutPut(true)
							.setRequestMethod("POST")
							.build()
							.send(user)
							.extract(XToken.class);
			Assert.assertNotNull(token);
		} catch (IOException e) {
			e.printStackTrace();
			Assert.fail(e.getMessage());
		} catch (ApiException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			Assert.fail(e.getMessage()+","+e.getErrorCode());
			
		}
	}
	
	@Test
	public void testGET(){
		Map<String,String> headers = new HashMap<String,String>();
		headers.put("X-TOKEN", "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJuYW1lIjoidmlzaGFsIiwiY29tcGFueUlkIjo3LCJ1c2VySWQiOjU3LCJpYXQiOiIxNDk4NzI0MTk0IiwiZXhwIjoiMTUzMDI2MDE5NCIsImFkbWluIjp0cnVlfQ.J6ektYgzjRP8pE-XV2GiBqM-hZ9YkCUVtxSkMhIZ5mA");
		try {
			String output = new HttpBuilder()
							.setUrl("http://api.frood.tech/v1/sales")
							.setDoOutPut(true)
							.setRequestMethod(HttpMethod.GET)
							.setHeaders(headers)
							.build()
							.extract();
			Assert.assertNotNull(output);
		} catch (IOException e) {
			e.printStackTrace();
			Assert.fail(e.getMessage());
		} catch (ApiException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			Assert.fail(e.getMessage()+","+e.getErrorCode());
			
		}
	}
	
	@Test
	public void testGETWithList(){
		Map<String,String> headers = new HashMap<String,String>();
		headers.put("X-TOKEN", "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJuYW1lIjoidmlzaGFsIiwiY29tcGFueUlkIjo3LCJ1c2VySWQiOjU3LCJpYXQiOiIxNDk4NzI0MTk0IiwiZXhwIjoiMTUzMDI2MDE5NCIsImFkbWluIjp0cnVlfQ.J6ektYgzjRP8pE-XV2GiBqM-hZ9YkCUVtxSkMhIZ5mA");
		try {
			List<Values> output = new HttpBuilder()
							.setUrl("http://api.frood.tech/v1/countries/47/states")
							.setDoOutPut(true)
							.setRequestMethod(HttpMethod.GET)
							.setHeaders(headers)
							.build()
							.list(Values.class);
			Assert.assertFalse(output.isEmpty());
		} catch (IOException e) {
			e.printStackTrace();
			Assert.fail(e.getMessage());
		} catch (ApiException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			Assert.fail(e.getMessage()+","+e.getErrorCode());
			
		}
	}
	
	
}
class Values implements Serializable{
	private int id;
	private String name;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	
}
class XToken implements Serializable{
	@JsonProperty("X-TOKEN")
	private String token;
	
	public void setToken(String token){
		this.token = token;
	}
	
	public String getToken(){
		return this.token;
	}
}
