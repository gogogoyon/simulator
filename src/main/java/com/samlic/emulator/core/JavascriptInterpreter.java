package com.samlic.emulator.core;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class JavascriptInterpreter<T> implements ScriptInterpreter<T> {
	
	private ScriptEngine engine;
	
	private static final Logger logger = LoggerFactory.getLogger(JavascriptInterpreter.class);
	
	public JavascriptInterpreter(HttpRequestResolver request) {
		ScriptEngineManager engineManager = new ScriptEngineManager();
		engineManager.put("paramMap", request.getParameterMap());
		engineManager.put("method", request.getMethod());
		engineManager.put("contentType", request.getContentType());
		engineManager.put("contextPath", request.getContextPath());
		engineManager.put("body", request.getContextPath());
		
		engine = engineManager.getEngineByName("js");
		if(engine == null) {
			throw new IllegalStateException("Unsupported engine for javascript.");
		}
	}
	
	public JavascriptInterpreter() {
		ScriptEngineManager engineManager = new ScriptEngineManager();
		engine = engineManager.getEngineByName("js");
		if(engine == null) {
			throw new IllegalStateException("Unsupported engine for javascript.");
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public T interpret(String script) {
		T result = null;
		try {
			result = (T)engine.eval(script);
		} catch (ScriptException e) {
			logger.error("Failed to eval script.", e);
			throw new IllegalStateException("Failed to eval script.", e);
		}
		
		return result;
	}

	@Override
	public boolean equals(String scriptOne, String scriptTwo) {
		T resultOne = interpret(scriptOne);
		T resultTwo = interpret(scriptTwo);
		
		return resultOne.equals(resultTwo);
	}
}
