package com.samlic.emulator.controller;

public abstract class BaseHandler {

	protected WebState wrap() {
		
		WebState state = new WebState(0, "OK", null);
		
		return state;
	}
	
	protected WebState wrap(Object data) {
		
		WebState state = new WebState(0, "OK", data);
		
		return state;
	}
	
	protected WebState wrapError(String message) {
		
		WebState state = new WebState(1, message, null);
		
		return state;
	}
}
