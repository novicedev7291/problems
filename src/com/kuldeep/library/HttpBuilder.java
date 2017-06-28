package com.kuldeep.library;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.Map;
import java.util.Map.Entry;

import javax.net.ssl.HttpsURLConnection;
/**
 * Builder class can be used to make http GET/POST/PUT/DELETE request.
 * Remember! this class is not thread safe so please use with caution.
 * @author Kuldeep
 */
public class HttpBuilder {
	private URLConnection con;
	private String contentType = "application/json";
	private String requestMethod = "GET";
	private boolean doOutPut = false;
	private Map<String, String> headers;
	private String url;
	private int responseCode;
	
	private static class UrlConnectionFactory{
		private UrlConnectionFactory(){}
		
		public static URLConnection getInstance(final String url,final String method) throws IOException{
			URL uRL = new URL(url);
			URLConnection con = null;
			if(url.substring(0,5).equals("https")){
				con = (HttpsURLConnection) uRL.openConnection();
				((HttpsURLConnection) con).setRequestMethod(method);
			}
			else{
				con = (HttpURLConnection) uRL.openConnection();
				((HttpURLConnection) con).setRequestMethod(method);
			}
			return con;
		}
	}
	
	
	public HttpBuilder() {
		
	}
	
	public HttpBuilder setUrl(String url){
		this.url = url;
		return this;
	}
	
	public HttpBuilder setContentType(String contentType) {
		this.contentType = contentType;
		return this;
	}



	public HttpBuilder setRequestMethod(String requestMethod) {
		this.requestMethod = requestMethod;
		return this;
	}



	public HttpBuilder setDoOutPut(boolean doOutPut) {
		this.doOutPut = doOutPut;
		return this;
	}



	public HttpBuilder setHeaders(Map<String, String> headers) {
		this.headers = headers;
		return this;
	}

	public HttpBuilder build() throws IOException{
		if(url == null) throw new IllegalArgumentException("Url cannot be null");
		con = UrlConnectionFactory.getInstance(url.trim(), requestMethod);
		con.setDoOutput(doOutPut);
		con.setRequestProperty("Content-Type", contentType);
		if(headers != null){
			for(Entry<String, String> entry: headers.entrySet()){
				con.setRequestProperty(entry.getKey(), entry.getValue());
			}
		}
		return this;
	}
	
	public HttpBuilder write(Map<String, Object> params){
		if(params != null){
			StringBuilder sb = new StringBuilder("?");
		}
		return this;
	}
	
	public <T extends Serializable> HttpBuilder write(T o){
		return this;
	}
	
	public HttpBuilder write(String s) throws IOException{
		if(con != null){
			DataOutputStream wr = new DataOutputStream(con.getOutputStream());
			wr.writeBytes(s);
			wr.flush();
			wr.close();
			
			responseCode = (con instanceof HttpsURLConnection) ? ((HttpsURLConnection) con).getResponseCode() : ((HttpURLConnection) con).getResponseCode();
		}
		return this;
	}
	
	public String extract() throws IOException{
		if(con != null){
			if(responseCode >= 200 && responseCode <= 210){
				BufferedReader in = new BufferedReader(
				        new InputStreamReader(con.getInputStream()));
				String inputLine;
				StringBuffer response = new StringBuffer();

				while ((inputLine = in.readLine()) != null) {
					response.append(inputLine);
				}
				in.close();
				return response.toString();
			}else{
				//TODO: extract the code in error object in this case single message
				BufferedReader in = new BufferedReader(
				        new InputStreamReader((con instanceof HttpsURLConnection) ? 
				        		((HttpsURLConnection) con).getErrorStream() : ((HttpURLConnection) con).getErrorStream()));
				String inputLine;
				StringBuffer response = new StringBuffer();

				while ((inputLine = in.readLine()) != null) {
					response.append(inputLine);
				}
				in.close();
				return "{\"errorCode\":\""+responseCode+"\",\"errorMsg\":\""+response.toString()+"\"}";
			}
		}
		return null;
	}
	
	public <T extends Serializable> T extract(Class<T> clazz){
		return null;
	}
}
