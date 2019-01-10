package com.samlic.emulator.core;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.samlic.emulator.entity.InterfaceCase;

public class InterfaceCaseManager {
	private static Map<String, List<InterfaceCase>> caseMap = new HashMap<String, List<InterfaceCase>>();
	
	private static final Logger logger = LoggerFactory.getLogger(InterfaceCaseManager.class);
	
	private InterfaceCaseManager() {}
	
	public static void init(List<InterfaceCase> dataList) {
		for(InterfaceCase data : dataList) {
			add(data);
		}
	}
	
	public static void add(InterfaceCase interfaceCase) {
		List<InterfaceCase> value = caseMap.get(interfaceCase.getUrl());
		if(value == null) {
			value = new ArrayList<InterfaceCase>();
			value.add(interfaceCase);
			caseMap.put(interfaceCase.getUrl(), value);
		} else {
			value.add(interfaceCase);
		}
	}
	
	public static void delete(InterfaceCase interfaceCase) {
		List<InterfaceCase> value = caseMap.get(interfaceCase.getUrl());
		if(value != null) {
			value.remove(interfaceCase);
		}
	}
	
	public static List<InterfaceCase> queryAll() {
		List<InterfaceCase> dataList = new ArrayList<InterfaceCase>();
		for(List<InterfaceCase> item : caseMap.values()) {
			dataList.addAll(item);
		}
		
		return dataList;
	}	
	
	public static InterfaceCase findInterfaceCase(HttpRequestResolver request) {
		List<InterfaceCase> value = caseMap.get(request.getRequestURI());
		if(value != null) {
			JavascriptInterpreter<Boolean> interpreter = new JavascriptInterpreter<Boolean>(request);
			for(InterfaceCase item : value) {
				if(interpreter.interpret(item.getMatchRule())) {
					logger.info("Matched: " + item.toString());
					return item;
				}
			}
		}
		
		return null;		
	}
}
