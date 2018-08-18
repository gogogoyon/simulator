package com.samlic.emulator.core;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HttpRequestResolver {
	private HttpServletRequest request;
	private String body;
	
	private static final Logger logger = LoggerFactory.getLogger(HttpRequestResolver.class);
	
	public HttpRequestResolver(HttpServletRequest request) {
		this.request = request;
		BufferedReader  reader = null;
		try {
			reader = request.getReader();
			StringBuilder sb = new StringBuilder();
			String line = null;
			while( (line = reader.readLine()) != null) {
				sb.append(line);
			}
			body = sb.toString();
		} catch (IOException e) {
			logger.error("Failed to read body.", e);
			throw new IllegalStateException("Failed to read body.", e);
		} finally {
			if(reader != null) {
				try {
					reader.close();
				} catch (IOException e) {
					
				}
			}
		}
	}
	
	public String getMethod() {		
		return request.getMethod();
	}
	
	public Map<String, String[]> getParameterMap() {
		return request.getParameterMap();
	}
	
	public String getContentType() {
		return request.getContentType();
	}
	
	public String body() {
		return body;
	}
	
	public String getContextPath() {
		return request.getContextPath();
	}
	
	public String getCharacterEncoding() {
		return request.getCharacterEncoding();
	}
	
	public String getRequestURI() {
		return request.getRequestURI();
	}
	
	public StringBuffer getRequestURL() {
		return request.getRequestURL();
	}
}
