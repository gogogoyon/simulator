package com.samlic.emulator.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class GlobalExceptionHandler extends BaseHandler {
	@ExceptionHandler(value = Exception.class)
    @ResponseBody
    public WebState jsonErrorHandler(HttpServletRequest req, Exception e) throws Exception {		
		
		return wrapError(e.getMessage());
    }
}
