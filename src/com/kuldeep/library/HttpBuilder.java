package com.kuldeep.library;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.net.ssl.HttpsURLConnection;
import javax.ws.rs.HttpMethod;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.ObjectMapper;
/**
 * Builder class can be used to make http GET/POST/PUT/DELETE request.
 * Remember! this class is not thread safe so please use with caution.
 * @author Kuldeep
 */
public class HttpBuilder {
	public final Logger log = Logger.getLogger(getClass().getName());
	private URLConnection con;
	private String contentType = MediaType.APPLICATION_JSON;
	private String requestMethod = HttpMethod.GET;
	private boolean doOutPut = false;
	private Map<String, String> headers;
	private String url;
	private int responseCode;
	private int conTimeOut;
	private int readTimeOut;
	private ObjectMapper mapper;
	
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
	
	enum ContentType{
		APPLICATION_FORM_URLENCODED(MediaType.APPLICATION_FORM_URLENCODED),
		APPLICATION_JSON(MediaType.APPLICATION_JSON),
		APPLICATION_XML(MediaType.APPLICATION_XML),
		MULTIPART_FORM_DATA(MediaType.MULTIPART_FORM_DATA),
		TEXT_HTML(MediaType.TEXT_HTML),
		TEXT_PLAIN(MediaType.TEXT_PLAIN);
		
		private String contentType;
		ContentType(String contentType){
			this.contentType = contentType;
		}
		
		public String type(){
			return contentType;
		}
		
		public static ContentType get(String type){
			for(ContentType value: values()){
				if(value.type().equals(type)) return value;
			}
			throw new IllegalArgumentException();
		}
	}
	
	
	public HttpBuilder() {
		mapper = new ObjectMapper();
	}
	
	public HttpBuilder setUrl(String url){
		this.url = url;
		return this;
	}
	
	public HttpBuilder setTimeOut(int timeout){
		this.conTimeOut = timeout;
		return this;
	}
	
	public HttpBuilder setReadTimeOut(int timeout){
		this.readTimeOut = timeout;
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
		if(conTimeOut > 0){
			con.setConnectTimeout(conTimeOut);
		}
		if(readTimeOut > 0){
			con.setReadTimeout(readTimeOut);
		}
		con.setRequestProperty(HttpHeaders.CONTENT_TYPE, contentType);
		if(headers != null){
			for(Entry<String, String> entry: headers.entrySet()){
				con.setRequestProperty(entry.getKey(), entry.getValue());
			}
		}
		return this;
	}
	
	public HttpBuilder send(Map<String, Object> params) throws IOException, ApiException{
		if(params != null && (requestMethod.equals(HttpMethod.POST) || requestMethod.equals(HttpMethod.PUT))){
			ContentType key = ContentType.get(contentType);
			switch (key) {
			case APPLICATION_FORM_URLENCODED:
				StringBuilder sb = new StringBuilder();
				for(Entry<String, Object> e: params.entrySet()){
					sb.append(e.getKey()).append("=").append(URLEncoder.encode(e.getValue().toString(), "UTF-8")).append("&");
				}
				String pS = sb.toString();
				send(pS.substring(0,pS.length() - 1));
				break;
			case APPLICATION_JSON:
				send(mapper.writeValueAsString(params));
				break;
			case APPLICATION_XML:
				throw new IllegalStateException("Method not implemented yet");
			case MULTIPART_FORM_DATA:
				throw new IllegalStateException("Method not implemented yet");
			default:
				throw new ApiException("Invalid content type");
			}
		}else{
			throw new IllegalArgumentException("Arguments not matching content type or request methods");
		}
		return this;
	}
	
	public <T extends Serializable> HttpBuilder send(T o) throws IOException, ApiException{
		if(o != null && (requestMethod.equals(HttpMethod.POST) || requestMethod.equals(HttpMethod.PUT))){
			ContentType key = ContentType.get(contentType);
			switch (key) {
			case APPLICATION_JSON:
				send(mapper.writeValueAsString(o));
				break;
			case APPLICATION_XML:
				throw new IllegalStateException("Method not implemented yet");
			default:
				throw new ApiException("Invalid content type");
			}
		}else{
			throw new IllegalArgumentException("Arguments not matching content type or request methods");
		}
		return this;
	}
	
	public HttpBuilder send(String s) throws IOException{
		if(con != null){
			DataOutputStream wr = new DataOutputStream(con.getOutputStream());
			wr.writeBytes(s);
			wr.flush();
			wr.close();
		}
		return this;
	}
	
	public String extract() throws IOException, ApiException{
		if(con != null){
			responseCode = (con instanceof HttpsURLConnection) ? ((HttpsURLConnection) con).getResponseCode() : ((HttpURLConnection) con).getResponseCode();
			log.log(Level.INFO, "Response Code : " + responseCode);
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
				parseErrorResponse();
			}
		}
		return null;
	}
	
	private void parseErrorResponse() throws ApiException, IOException{
		//TODO: extract the code in error object in this case single message
		BufferedReader in = new BufferedReader(
		        new InputStreamReader((con instanceof HttpsURLConnection) ? ((HttpsURLConnection)con).getErrorStream():((HttpURLConnection)con).getErrorStream()));
		String inputLine;
		StringBuffer response = new StringBuffer();

		while ((inputLine = in.readLine()) != null) {
			response.append(inputLine);
		}
		in.close();
		throw new ApiException(responseCode, response.toString());
	}
	/**
	 * Call this method only when you are sure if url is returning json object.
	 * @param clazz
	 * @return
	 * @throws ApiException, http errorCode and message 
	 * @throws IOException
	 */
	public <T extends Serializable> T extract(Class<T> clazz) throws ApiException, IOException{
		if(con != null){
			responseCode = (con instanceof HttpsURLConnection) ? ((HttpsURLConnection) con).getResponseCode() : ((HttpURLConnection) con).getResponseCode();
			log.log(Level.INFO, "Response Code : " + responseCode);
			if(responseCode >= 200 && responseCode <= 210){
		        return mapper.readValue(new InputStreamReader(con.getInputStream(), "UTF-8"), clazz);
			}
			else{
				parseErrorResponse();
			}
		}
		return null;
	}
	
	/**
	 * Call this method only when you are sure if url is returning json array.
	 * @param clazz
	 * @return
	 * @throws ApiException, http errorCode and message 
	 * @throws IOException
	 */
	public <T extends Serializable> List<T> list(Class<T> clazz) throws ApiException, IOException{
		if(con != null){
			responseCode = (con instanceof HttpsURLConnection) ? ((HttpsURLConnection) con).getResponseCode() : ((HttpURLConnection) con).getResponseCode();
			log.log(Level.INFO, "Response Code : " + responseCode);
			if(responseCode >= 200 && responseCode <= 210){
				JsonFactory jFactory = new JsonFactory();
				JsonParser jParser = jFactory.createParser(new InputStreamReader(con.getInputStream(),"UTF-8"));
				Iterator<T> values = mapper.readValues(jParser, clazz);
				List<T> messages = new ArrayList<T>();
				while(values.hasNext()){
					messages.add(values.next());
				}
		        return messages;
			}
			else{
				parseErrorResponse();
			}
		}
		return null;
	} 
	 
	
}
