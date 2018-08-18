package com.samlic.emulator.core;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.samlic.emulator.entity.InterfaceCase;

public class InterfaceCaseOutputForwarder implements OutputForwarder {
	
	private HttpRequestResolver request;
	private HttpServletResponse response;
	private InterfaceCase interfaceCase;
	
	private static final Logger logger = LoggerFactory.getLogger(InterfaceCaseOutputForwarder.class);
	
	public InterfaceCaseOutputForwarder(HttpRequestResolver request, HttpServletResponse response, InterfaceCase interfaceCase) {
		this.request = request;
		this.response = response;
		this.interfaceCase = interfaceCase;
	}

	@Override
	public void forward() {
		JavascriptInterpreter<String> interpreter = new JavascriptInterpreter<String>(request);
		
		String output = interfaceCase.getResponse();
		char[] charArray = output.toCharArray();
		StringBuilder sb = new StringBuilder();
		ScriptStateMachine stateMachine = new ScriptStateMachine();
		
		for(int i=0; i < charArray.length; i ++ ) {
			MatchStatus status = stateMachine.next(charArray[i]);
			if(status == MatchStatus.None) {
				sb.append(charArray[i]);
			}
			
			if(status == MatchStatus.Ready) {
				sb.append(interpreter.interpret(stateMachine.getValue()));
			}
		}
		
		forward(sb.toString());
	}
	
	private void forward(String output) {
		response.setCharacterEncoding(request.getCharacterEncoding());
		response.setContentType(interfaceCase.getContentType());		
		try {
			response.getWriter().append(output);
			response.getWriter().flush();
			response.getWriter().close();
		} catch (IOException e) {
			logger.error("Failed to forward response.", e);
		}
	}
}
