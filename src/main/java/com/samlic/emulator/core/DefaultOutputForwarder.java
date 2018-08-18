package com.samlic.emulator.core;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DefaultOutputForwarder implements OutputForwarder {

	private HttpServletResponse response;
	
	private static final Logger logger = LoggerFactory.getLogger(DefaultOutputForwarder.class);
	
	public DefaultOutputForwarder(HttpServletResponse response) {
		this.response = response;
	}
	
	@Override
	public void forward() {		
		response.setContentType("text/plain");		
		try {
			response.getWriter().append("Wellcome by simulator. There is no content had been set.");
			response.getWriter().flush();
			response.getWriter().close();
		} catch (IOException e) {
			logger.error("Failed to forward response.", e);
		}
	} 

}
