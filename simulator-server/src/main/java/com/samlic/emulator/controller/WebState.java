package com.samlic.emulator.controller;

import java.io.Serializable;

public class WebState implements Serializable {
	
	private static final long serialVersionUID = -6037491160190801946L;
	
	private int code;
	private String message;
	private Object data;
	
	public WebState() {}
	
	public WebState(int code, String message, Object data) {
		super();
		this.code = code;
		this.message = message;
		this.data = data;
	}
	
	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public Object getData() {
		return data;
	}
	public void setData(Object data) {
		this.data = data;
	}
	
	public boolean isSuccess() {
		return code == 0;
	}
}
